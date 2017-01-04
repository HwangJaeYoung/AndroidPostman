package onem2m.keti.androidpostman.ui.main_data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import cz.msebera.android.httpclient.Header;
import onem2m.keti.androidpostman.domain.MainHeaderItem;
import onem2m.keti.androidpostman.domain.RequestPrimitive;
import onem2m.keti.androidpostman.reuse.database.DBManager;
import onem2m.keti.androidpostman.reuse.etc.PrettyFormatter;
import onem2m.keti.androidpostman.reuse.network.HttpRequester;
import onem2m.keti.androidpostman.reuse.network.oneM2MRequest;
import onem2m.keti.androidpostman.ui.main_data.listview.header.listview.ViewForArrayAdapterForHeaderListView;
import onem2m.keti.androidpostman.ui.main_data.popup.headercreation.HeaderCreationActivity;
import onem2m.keti.androidpostman.ui.main_data.popup.headerdelupdate.HeaderDelUpdateActivity;
import onem2m.keti.androidpostman.ui.result.ResultActivity;

public class MainDataActivity extends Activity implements ViewForMainDataActivity.Controller{

    private ViewForMainDataActivity view;

    private ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerLists;

    public static final int REQUEST_HEADER_ADD = 1;
    public static final int REQUEST_HEADER_MODIFICIATON_DELETE = 2;

    public static String HEADER_LIST = "headerList";
    public static String RESPONSE_BODY = "responseBody";

    public DBManager mDbManager = null;
    private String requestKeyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new ViewForMainDataActivity(getApplicationContext(), this);

        // Getting the request list data form MainActivity
        Intent intent = getIntent();

        /**** list Key for saving the information ****/
        requestKeyValue = intent.getStringExtra("listKey");

        String operation = intent.getStringExtra("operation");
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        String xmlBody = intent.getStringExtra("xmlBody");
        String jsonBody = intent.getStringExtra("jsonBody");

        // Using the Gson for LinkedHashMap type
        String header = intent.getStringExtra("header");
        Gson gson = new Gson();
        Type entityType = new TypeToken< LinkedHashMap<String, String>>(){}.getType();
        LinkedHashMap<String, String> tempHeaderItems = gson.fromJson(header, entityType);

        Iterator<String> keys = tempHeaderItems.keySet().iterator();

        headerLists = new ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd>();

        while( keys.hasNext() ){
            String key = keys.next();
            headerLists.add(new MainHeaderItem(key, tempHeaderItems.get(key)));
        }

        view.setRequestLists(operation, title, url, xmlBody, jsonBody, headerLists);

        setContentView(view.getRoot());

        mDbManager = DBManager.getInstance(getApplicationContext());
    }

    @Override
    public void onClickSavingData(String operationItem, String URLItem, ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerListsItem, String xmlBody, String jsonBody) {

        String where = "requestnumber=" + requestKeyValue;
        Cursor cursor = mDbManager.query(null, where, null, null, null, null);

        if(cursor.getCount() != 0) { // Updating the database value
            Log.i("testing", "Updating the database value");

            String[] headerArray = new String[9];
            int count = 0;

            // Initializing the header list
            for(int i = 0; i < 9; i++)
                headerArray[i] = null;

            Iterator<ViewForArrayAdapterForHeaderListView.IHeaderAdd> iterator = headerListsItem.iterator();
            while (iterator.hasNext()) {
                MainHeaderItem header = (MainHeaderItem) iterator.next();
                headerArray[count++] = header.getHeaderName() + ":" + header.getHeaderValue();
            }

            ContentValues updateRowValue = new ContentValues();

            updateRowValue.put("operation", operationItem);
            updateRowValue.put("url", URLItem);
            updateRowValue.put("header1", headerArray[0]); updateRowValue.put("header2", headerArray[1]); updateRowValue.put("header3", headerArray[2]);
            updateRowValue.put("header4", headerArray[3]); updateRowValue.put("header5", headerArray[4]); updateRowValue.put("header6", headerArray[5]);
            updateRowValue.put("header7", headerArray[6]); updateRowValue.put("header8", headerArray[7]); updateRowValue.put("header9", headerArray[8]);
            updateRowValue.put("xmlbody", xmlBody);
            updateRowValue.put("jsonbody", jsonBody);

            mDbManager.update(updateRowValue, "requestnumber=" + requestKeyValue, null);

            Toast.makeText(getApplicationContext(), "Request resource is updated", Toast.LENGTH_SHORT).show();
            cursor.close();

        } else { // Insert

            Log.i("testing", "Inserting the database value");

            String[] headerArray = new String[9];
            int count = 0;

            // Initializing the header list
            for(int i = 0; i < 9; i++)
                headerArray[i] = null;

            Iterator<ViewForArrayAdapterForHeaderListView.IHeaderAdd> iterator = headerListsItem.iterator();
            while (iterator.hasNext()) {
                MainHeaderItem header = (MainHeaderItem) iterator.next();
                headerArray[count++] = header.getHeaderName() + ":" + header.getHeaderValue();
            }

            ContentValues addRowValue = new ContentValues();

            addRowValue.put("requestnumber", requestKeyValue);
            addRowValue.put("operation", operationItem);
            addRowValue.put("url", URLItem);
            addRowValue.put("header1", headerArray[0]); addRowValue.put("header2", headerArray[1]); addRowValue.put("header3", headerArray[2]);
            addRowValue.put("header4", headerArray[3]); addRowValue.put("header5", headerArray[4]); addRowValue.put("header6", headerArray[5]);
            addRowValue.put("header7", headerArray[6]); addRowValue.put("header8", headerArray[7]); addRowValue.put("header9", headerArray[8]);
            addRowValue.put("xmlbody", xmlBody);
            addRowValue.put("jsonbody", jsonBody);

            mDbManager.insert( addRowValue );

            Toast.makeText(getApplicationContext(), "Request resource is inserted", Toast.LENGTH_SHORT).show();
            cursor.close();
        }
    }

    @Override
    public void onClickHeaderCreation( ) {

        if(headerLists.size() == 9) {
            Toast.makeText(getApplicationContext(), "You can't create header more than 9", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, HeaderCreationActivity.class);
            startActivityForResult(intent, REQUEST_HEADER_ADD);
        }
    }

    @Override
    public void onClickHeaderUpdateDelete(int position, MainHeaderItem mainHeaderItem) {
        Intent intent = new Intent(this, HeaderDelUpdateActivity.class);

        intent.putExtra("HeaderName", mainHeaderItem.getHeaderName());
        intent.putExtra("HeaderValue", mainHeaderItem.getHeaderValue());
        intent.putExtra("Position", position);

        Log.i("testing", mainHeaderItem.getHeaderName() + ":" + mainHeaderItem.getHeaderValue());

        startActivityForResult(intent, REQUEST_HEADER_MODIFICIATON_DELETE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check which request we're responding to
        if (requestCode == REQUEST_HEADER_ADD) {

            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                // Header Listview Update
                headerLists.add(new MainHeaderItem(data.getStringExtra("headerName"), data.getStringExtra("headerValue")));
                view.addHeaderItemToListView(headerLists);
            }
        } else if(requestCode == REQUEST_HEADER_MODIFICIATON_DELETE) {

            if(resultCode == RESULT_OK) {
                headerLists.set(data.getIntExtra("Position", 0), new MainHeaderItem(data.getStringExtra("headerName"), data.getStringExtra("headerValue")));
                view.updateHeaderList(headerLists);
            } else if(resultCode == HeaderDelUpdateActivity.RESULT_DELETE) {
                headerLists.remove(data.getIntExtra("Position", 0));
                view.deleteHeaderList(headerLists);
            }
        }
    }

    @Override
    public void onClickSendingRequest(String operationItem, String URLItem, ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerListsItem, String bodyItem) {
        oneM2MRequest oneM2MRequestor = new oneM2MRequest();

        RequestPrimitive requestPrimitive = new RequestPrimitive(operationItem, URLItem, headerListsItem, bodyItem);

        String accept = requestPrimitive.getACCEPT();
        String operation = requestPrimitive.getOperation();

        if(accept != null) {
            if(accept.equals("application/xml")) {
                oneM2MRequestor.XML(getApplicationContext(), XMLResponseListener, operation, requestPrimitive);
            } else if(accept.equals("application/json")) {
                try {
                    oneM2MRequestor.JSON(getApplicationContext(), JSONResponseListener, operation, requestPrimitive);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please modify the Accept header as follows\n ex) application/xml, application/json" , Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please fill in the Accept header is mandatory field\n ex) application/xml, application/json" , Toast.LENGTH_SHORT).show();
        }
    }

    HttpRequester.NetworkResponseListenerXML XMLResponseListener = new HttpRequester.NetworkResponseListenerXML() {

        @Override
        public void onSuccess(int statusCode, Header[] headers, HttpRequester.NetworkResponseListenerXML networkResponseListenerXML, String responseBody) {
            Log.i("testing", "XML onSuccess");
            Log.i("testing", ""+ statusCode);
            Log.i("testing", ""+ PrettyFormatter.getPrettyXML(responseBody));

            view.viewRequestResult(headers, PrettyFormatter.getPrettyXML(responseBody));
            view.releaseSendSubmitButton();
            /* Map<String, String> map = networkResponseListenerXML.getXmlResponse();

            Iterator<String> keys = map.keySet().iterator();

            while (keys.hasNext()) {
                String key = keys.next();
                Log.i("testing", "Key : " + key + ", " + "Value : " + map.get(key));
            } */
        }

        @Override
        public void onFail(int statusCode, Header[] headers, HttpRequester.NetworkResponseListenerXML networkResponseListenerXML, String responseBody) {
            Log.i("testing", "XML onFail");
            Log.i("testing", ""+ statusCode);
            Log.i("testing", ""+ PrettyFormatter.getPrettyXML(responseBody));

            view.viewRequestResult(headers, PrettyFormatter.getPrettyXML(responseBody));
            view.releaseSendSubmitButton();
        }
    };

    HttpRequester.NetworkResponseListenerJSON JSONResponseListener = new HttpRequester.NetworkResponseListenerJSON() {

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
            Log.i("testing", "JSON onSuccess");

            if(jsonObject != null) {
                Log.i("testing", jsonObject.toString());
                view.viewRequestResult(headers, PrettyFormatter.getPrettyJSON(jsonObject));
            }

            view.viewRequestResult(headers, PrettyFormatter.getPrettyJSON(jsonObject));
            view.releaseSendSubmitButton();
        }

        @Override
        public void onFail(int statusCode, Header[] headers, JSONObject jsonObject) {
            Log.i("testing", "JSON onFail1");

            if(jsonObject != null) {
                Log.i("testing", ""+ statusCode);
                Log.i("testing", PrettyFormatter.getPrettyJSON(jsonObject));
                view.viewRequestResult(headers, PrettyFormatter.getPrettyJSON(jsonObject));
            }
            view.releaseSendSubmitButton();
        }

        @Override
        public void onFail(int statusCode, Header[] headers, String responseString) {
            Log.i("testing", "JSON onFail2");

            if(responseString != null) {
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(responseString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(jsonObject != null) {
                    Log.i("testing", ""+ statusCode);
                    Log.i("testing", PrettyFormatter.getPrettyJSON(jsonObject));
                    view.viewRequestResult(headers, PrettyFormatter.getPrettyJSON(jsonObject));
                } else {
                    Log.i("testing", ""+ statusCode);
                    Log.i("testing", PrettyFormatter.getPrettyXML(responseString));
                    view.viewRequestResult(headers, PrettyFormatter.getPrettyXML(responseString));
                }
            }
            view.releaseSendSubmitButton();
        }
    };

    @Override
    public void creationResultView(Header[] headers, String responseBody) {
        Intent intent = new Intent(this, ResultActivity.class);

        LinkedHashMap<String, String> headerValueList = new LinkedHashMap<String, String>();

        for (Header header : headers)
            headerValueList.put(header.getName(), header.getValue());

        Gson gson = new Gson();
        String headerListString = gson.toJson(headerValueList);
        intent.putExtra(HEADER_LIST, headerListString); // header
        intent.putExtra(RESPONSE_BODY, responseBody);

        startActivity(intent);
    }
}