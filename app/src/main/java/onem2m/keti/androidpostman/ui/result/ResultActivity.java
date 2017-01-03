package onem2m.keti.androidpostman.ui.result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.domain.ResultHeaderItem;
import onem2m.keti.androidpostman.ui.result.listview.ViewForArrayAdapterForResultHeader;


/**
 * Created by Blossom on 2016-11-09.
 */

public class ResultActivity extends Activity implements ViewForResultActivity.Controller {

    private ViewForResultActivity view;

    private ArrayList<ViewForArrayAdapterForResultHeader.IHeaderAdd> headerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new ViewForResultActivity(getApplicationContext(), this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();

        // Using the Gson for LinkedHashMap type
        String header = intent.getStringExtra("headerList");
        Gson gson = new Gson();
        Type entityType = new TypeToken< LinkedHashMap<String, String>>(){}.getType();
        LinkedHashMap<String, String> tempHeaderItems = gson.fromJson(header, entityType);

        Iterator<String> keys = tempHeaderItems.keySet().iterator();

        headerList = new ArrayList<ViewForArrayAdapterForResultHeader.IHeaderAdd>();

        while( keys.hasNext() ){
            String key = keys.next();
            headerList.add(new ResultHeaderItem(key, tempHeaderItems.get(key)));
        }

        view.setResultView(headerList, intent.getStringExtra("responseBody"));

        setContentView(view.getRoot());
    }
}