package onem2m.keti.androidpostman.ui.main_list;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.domain.oneM2MList.AE.AE_Create;
import onem2m.keti.androidpostman.domain.oneM2MList.AE.AE_Delete;
import onem2m.keti.androidpostman.domain.oneM2MList.AE.AE_Retrieve;
import onem2m.keti.androidpostman.domain.oneM2MList.AE.AE_Update;
import onem2m.keti.androidpostman.domain.oneM2MList.CSE.CSE_Retrieve;
import onem2m.keti.androidpostman.domain.oneM2MList.Container.Container_Create;
import onem2m.keti.androidpostman.domain.oneM2MList.Container.Container_Delete;
import onem2m.keti.androidpostman.domain.oneM2MList.Container.Container_Retrieve;
import onem2m.keti.androidpostman.domain.oneM2MList.Container.Container_Update;
import onem2m.keti.androidpostman.domain.oneM2MList.ContentInstance.ContentInstance_Create;
import onem2m.keti.androidpostman.domain.oneM2MList.ContentInstance.ContentInstance_Retrieve;
import onem2m.keti.androidpostman.domain.oneM2MList.Subscription.Subscription_Create;
import onem2m.keti.androidpostman.domain.oneM2MList.Subscription.Subscription_Delete;
import onem2m.keti.androidpostman.domain.oneM2MList.Subscription.Subscription_Retrieve;
import onem2m.keti.androidpostman.domain.oneM2MList.Subscription.Subscription_Update;
import onem2m.keti.androidpostman.reuse.database.DBManager;
import onem2m.keti.androidpostman.reuse.etc.BackPressCloseHandler;
import onem2m.keti.androidpostman.ui.main_data.MainDataActivity;
import onem2m.keti.androidpostman.ui.main_list.listview.ViewForArrayAdapterForMainActivity;

/**
 * Created by Blossom on 2016-12-07.
 */

public class MainActivity extends Activity implements ViewForMainActivity.Controller {

    private ViewForMainActivity view;
    private BackPressCloseHandler backPressCloseHandler;

    // Call the DBManager instance
    public DBManager mDbManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new ViewForMainActivity(getApplicationContext(), this);
        backPressCloseHandler = new BackPressCloseHandler(this);
        setContentView(view.getRoot());

        mDbManager = DBManager.getInstance(getApplicationContext());

        /**** Adding resource list ****/

        // CSE
        view.addRequestListToArrayList(new CSE_Retrieve());

        // AE
        view.addRequestListToArrayList(new AE_Create());
        view.addRequestListToArrayList(new AE_Retrieve());
        view.addRequestListToArrayList(new AE_Update());
        view.addRequestListToArrayList(new AE_Delete());

        // Container
        view.addRequestListToArrayList(new Container_Create());
        view.addRequestListToArrayList(new Container_Retrieve());
        view.addRequestListToArrayList(new Container_Update());
        view.addRequestListToArrayList(new Container_Delete());

        // contentInstance
        view.addRequestListToArrayList(new ContentInstance_Create());
        view.addRequestListToArrayList(new ContentInstance_Retrieve());


        // Subscription
        view.addRequestListToArrayList(new Subscription_Create());
        view.addRequestListToArrayList(new Subscription_Retrieve());
        view.addRequestListToArrayList(new Subscription_Update());
        view.addRequestListToArrayList(new Subscription_Delete());
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    @Override
    public void requestItemClick(int position, ViewForArrayAdapterForMainActivity.IRequestList requestData) {

        String listKey = position + "";

        String where = "requestnumber=" + listKey;
        Cursor cursor = mDbManager.query(null, where, null, null, null, null);

        if(cursor.getCount() != 0) { // Starting MainDataActivity

            Log.i("testing", "Getting database value");

            String operation = null;
            String url = null;
            String xmlbody = null; String jsonbody = null;

            String[] tempArray = new String[9];
            LinkedHashMap<String, String> headerList = new LinkedHashMap<String, String>();

            while(cursor.moveToNext() ) {
                operation = cursor.getString(1);
                url = cursor.getString(2);
                tempArray[0] = cursor.getString(3); tempArray[1] = cursor.getString(4); tempArray[2] = cursor.getString(5);
                tempArray[3] = cursor.getString(6); tempArray[4] = cursor.getString(7); tempArray[5] = cursor.getString(8);
                tempArray[6] = cursor.getString(9); tempArray[7] = cursor.getString(10); tempArray[8] = cursor.getString(11);
                xmlbody = cursor.getString(12);
                jsonbody = cursor.getString(13);
            }

            for(int i = 0; i < tempArray.length; i++) {
                if(tempArray[i] != null) {
                    String[] header = tempArray[i].split(":");
                    headerList.put(header[0], header[1]);
                }
            }

            Intent intent = new Intent(this, MainDataActivity.class);

            intent.putExtra("listKey", listKey); // key of list
            intent.putExtra("url", url); // url
            intent.putExtra("operation", operation); // operation
            intent.putExtra("title",requestData.getTitle()); // title
            intent.putExtra("xmlBody", xmlbody); // xml Body
            intent.putExtra("jsonBody", jsonbody); // json Body

            Gson gson = new Gson();
            String headerListString = gson.toJson(headerList);
            intent.putExtra("header", headerListString); // header

            cursor.close();
            startActivity(intent);

        } else { // Initializing the MainDataActivity with specific domain value
            cursor.close();

            Log.i("testing", "Null database value");

            String url = null;

            Intent intent = new Intent(this, MainDataActivity.class);

            intent.putExtra("listKey", listKey); // key of list
            intent.putExtra("url", url); // url
            intent.putExtra("operation", requestData.getOperation()); // operation
            intent.putExtra("title",requestData.getTitle()); // title
            intent.putExtra("xmlBody", requestData.getXmlBody()); // xml Body
            intent.putExtra("jsonBody", requestData.getJsonBody()); // json Body

            Gson gson = new Gson();
            String headerListString = gson.toJson(requestData.getHeaderList());
            intent.putExtra("header", headerListString); // header

            startActivity(intent);
        }
    }
}