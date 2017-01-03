package onem2m.keti.androidpostman.ui.result;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import onem2m.keti.androidpostman.R;
import onem2m.keti.androidpostman.reuse.mvc.activity.AbstractViewForActivity;
import onem2m.keti.androidpostman.ui.result.listview.ArrayAdaperForResultHeader;
import onem2m.keti.androidpostman.ui.result.listview.ViewForArrayAdapterForResultHeader;

/**
 * Created by Blossom on 2016-11-09.
 */

public class ViewForResultActivity extends AbstractViewForActivity {

    private Controller controller;

    // Header list
    private ListView lvResultHeaderFormatList;
    private ArrayAdaperForResultHeader arrayAdaperForResultHeader;


    // Body
    private EditText etResultBodyWindow;

    public ViewForResultActivity(Context context, Controller controller) {
        super(context);
        this.controller = controller;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_result, null);
    }

    @Override
    protected void initViews() {

        arrayAdaperForResultHeader = new ArrayAdaperForResultHeader(getContext( ), 0);
        lvResultHeaderFormatList = (ListView)findViewById(R.id.lv_result_header_format_list);
        lvResultHeaderFormatList.setAdapter(arrayAdaperForResultHeader);

        etResultBodyWindow = (EditText) findViewById(R.id.et_result_body_window);
    }

    @Override
    protected void setEvent() {

    }

    public void setResultView(ArrayList<ViewForArrayAdapterForResultHeader.IHeaderAdd> headerList, String responseBody) {
        arrayAdaperForResultHeader.clear();
        arrayAdaperForResultHeader.addAll(headerList);
        etResultBodyWindow.setText(responseBody);
    }

    interface  Controller {

    }
}
