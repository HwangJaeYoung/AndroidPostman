package onem2m.keti.androidpostman.ui.main_data;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import onem2m.keti.androidpostman.R;
import onem2m.keti.androidpostman.domain.MainHeaderItem;
import onem2m.keti.androidpostman.reuse.mvc.activity.AbstractViewForActivity;
import onem2m.keti.androidpostman.reuse.widget.SubmitButton;
import onem2m.keti.androidpostman.ui.main_data.listview.header.listview.ArrayAdapterForHeaderListView;
import onem2m.keti.androidpostman.ui.main_data.listview.header.listview.ViewForArrayAdapterForHeaderListView;

/**
 * Created by Blossom on 2016-11-03.
 */

public class ViewForMainDataActivity extends AbstractViewForActivity {

    private Context context;
    private Controller controller;

    // Operation list
    private String operation;

    // Header list
    private ArrayAdapterForHeaderListView arrayAdapterForHeaderListView;
    ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerLists;

    private ListView lvHeaderFormatList;
    private ImageView ivHeaderAdd;

    // Body
    private EditText etBodyWindow;
    private boolean selectedButton = true; // true = xml, false = json

    // Request Sending
    private SubmitButton btSendRequest;

    // Saving Data
    private SubmitButton btSavingData;

    // URL
    private EditText etUrlWindow;

    // Format
    private Button btBodyXMLFormat;
    private Button btBodyJSONFormat;
    private String xmlBody;
    private String jsonBody;

    public ViewForMainDataActivity(Context context, Controller controller) {
        super(context);
        this.context = context;
        this.controller = controller;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_main, null);
    }

    @Override
    protected void initViews() {
        // Header
        lvHeaderFormatList = (ListView)findViewById(R.id.lv_header_format_list);
        arrayAdapterForHeaderListView = new ArrayAdapterForHeaderListView(getContext( ), 0);
        lvHeaderFormatList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        lvHeaderFormatList.setAdapter(arrayAdapterForHeaderListView);
        ivHeaderAdd = (ImageView) findViewById(R.id.iv_header_add);

        // Body
        etBodyWindow = (EditText)findViewById(R.id.et_body_window);

        // Saving & Sending
        btSavingData = (SubmitButton) findViewById(R.id.bt_saving_data);
        btSendRequest = (SubmitButton) findViewById(R.id.bt_send_request);

        btSavingData.init((ProgressBar)findViewById(R.id.pg_saving_progress));
        btSavingData.addViewToHold(btSendRequest);

        btSendRequest.init((ProgressBar)findViewById(R.id.pg_send_progress));
        btSendRequest.addViewToHold(btSavingData);

        // URL
        etUrlWindow = (EditText)findViewById(R.id.et_url_window);

        // Format
        btBodyXMLFormat = (Button)findViewById(R.id.bt_body_xml_format);
        btBodyJSONFormat = (Button)findViewById(R.id.bt_body_json_format);
    }

    @Override
    protected void setEvent() {

        ivHeaderAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onClickHeaderCreation();
            }
        });

        lvHeaderFormatList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                controller.onClickHeaderUpdateDelete(position, (MainHeaderItem)headerLists.get(position));
                return false;
            }
        });

        btSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*** Collecting the all information about oneM2M ***/

                // Operation
                String operationItem = operation;

                // URL
                String URLItem = etUrlWindow.getText().toString();

                // Header List
                ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerListsItem = headerLists;

                // Body
                String bodyItem = etBodyWindow.getText().toString();

                controller.onClickSendingRequest(operationItem, URLItem, headerListsItem, bodyItem);
            }
        });

        btSavingData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*** Collecting the all information about oneM2M ***/

                Log.i("testing", "onclick saving data");

                // Operation
                String operationItem = operation;

                // URL
                String URLItem = etUrlWindow.getText().toString();

                // Header List
                ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerListsItem = headerLists;

                controller.onClickSavingData(operationItem, URLItem, headerListsItem, xmlBody, jsonBody);
            }
        });

        btBodyXMLFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedButton == false) {
                    selectedButton = true;
                    jsonBody = etBodyWindow.getText().toString();
                    etBodyWindow.setText(xmlBody);
                }
            }
        });

        btBodyJSONFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedButton == true) {
                    selectedButton = false;
                    xmlBody = etBodyWindow.getText().toString();
                    etBodyWindow.setText(jsonBody);
                }
            }
        });

        etBodyWindow.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(selectedButton == true) // xml
                    xmlBody = etBodyWindow.getText().toString();
                else if(selectedButton == false) // json
                    jsonBody = etBodyWindow.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    public void deleteHeaderList (ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerLists) {
        this.headerLists = headerLists;

        arrayAdapterForHeaderListView.clear();
        arrayAdapterForHeaderListView.addAll(headerLists);
    }

    public void updateHeaderList(ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerLists) {
        this.headerLists = headerLists;

        arrayAdapterForHeaderListView.clear();
        arrayAdapterForHeaderListView.addAll(headerLists);
    }

    public void addHeaderItemToListView(ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerLists) {
        this.headerLists = headerLists;

        arrayAdapterForHeaderListView.clear();
        arrayAdapterForHeaderListView.addAll(headerLists);
        Toast.makeText (context, "Header is created", Toast.LENGTH_SHORT).show();
    }

    public void setRequestLists(String operationInfo, String title, String url, String xmlBody, String jsonBody, ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerLists) {

        this.headerLists = headerLists;

        // operation setting
        operation = operationInfo;

        if(url != null) // URL
            etUrlWindow.setText(url);

        // body setting
        if(xmlBody == null && jsonBody == null) { // empty body
            etBodyWindow.setText("");
            etBodyWindow.setFocusable(false);
            etBodyWindow.setFocusableInTouchMode(false);
        } else {
            if(xmlBody != null) { // xml body
                etBodyWindow.setText(xmlBody);
                etBodyWindow.setFocusable(true);
                etBodyWindow.setFocusableInTouchMode(true);
            }

            if(jsonBody != null) { // json body
                // etBodyWindow.setText(jsonBody);
                etBodyWindow.setFocusable(true);
                etBodyWindow.setFocusableInTouchMode(true);
            }
        }

        // Saving the xml or json format information
        this.xmlBody = xmlBody;
        this.jsonBody = jsonBody;

        // Initialize the existing header list
        arrayAdapterForHeaderListView.clear();
        arrayAdapterForHeaderListView.addAll(headerLists);

        if(operation.equals("POST") || operation.equals("PUT")) {
            btBodyXMLFormat.setVisibility(View.VISIBLE);
            btBodyJSONFormat.setVisibility(View.VISIBLE);
        } else if(operation.equals("GET") || operation.equals("DELETE")) {
            btBodyXMLFormat.setVisibility(View.INVISIBLE);
            btBodyJSONFormat.setVisibility(View.INVISIBLE);
        }
    }

    public void viewRequestResult(Header[] header, String responseBody) {
        controller.creationResultView(header, responseBody);
    }

    public void releaseSendSubmitButton() {
        this.btSendRequest.release();
    }

    public void releaseSavingSubmitButton() {
        this.btSavingData.release();
    }

    interface Controller {
        void onClickHeaderCreation( );
        void onClickHeaderUpdateDelete(int position, MainHeaderItem mainHeaderItem);
        void onClickSendingRequest(String operationItem, String URLItem, ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerListsItem, String bodyItem);
        void onClickSavingData(String operationItem, String URLItem, ArrayList<ViewForArrayAdapterForHeaderListView.IHeaderAdd> headerListsItem, String xmlBody, String jsonBody);
        void creationResultView(Header header[], String responseBody);
    }
}