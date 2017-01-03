package onem2m.keti.androidpostman.ui.main_list.listview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.R;
import onem2m.keti.androidpostman.reuse.listview.AbstractViewForListViewItem;
import onem2m.keti.androidpostman.reuse.listview.IListViewItem;

/**
 * Created by Blossom on 2016-12-07.
 */

public class ViewForArrayAdapterForMainActivity extends AbstractViewForListViewItem {

    private TextView tvRequestListOp;
    private TextView tvRequestListTitle;

    private String title;
    private String operation;

    public ViewForArrayAdapterForMainActivity(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return inflate(getContext( ), R.layout.item_request_list, this);
    }

    @Override
    protected void initViews() {
        tvRequestListOp = (TextView)findViewById(R.id.tv_request_list_op);
        tvRequestListTitle = (TextView)findViewById(R.id.tv_request_list_title);
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void setData(IListViewItem aIListViewItem) {
        title = ((ViewForArrayAdapterForMainActivity.IRequestList)aIListViewItem).getTitle();
        operation = ((ViewForArrayAdapterForMainActivity.IRequestList)aIListViewItem).getOperation();

        if(title != null) {
            tvRequestListTitle.setText(title);
        }

        if(operation != null) {
            if(operation.equals("GET")) {
                tvRequestListOp.setTextColor(Color.parseColor("#FF81D328"));
            } else if(operation.equals("POST")) {
                tvRequestListOp.setTextColor(Color.parseColor("#FFE99E24"));
            } else if(operation.equals("PUT")) {
                tvRequestListOp.setTextColor(Color.parseColor("#FF498DDF"));
            } else if(operation.equals("DELETE")) {
                tvRequestListOp.setTextColor(Color.parseColor("#FFEC4B48"));
            }
            tvRequestListOp.setText(operation);
        }
    }

    public interface IRequestList extends IListViewItem {
        // 뽑아낼 데이터의 메소드를 정의
        String getTitle( );
        String getOperation( );
        LinkedHashMap<String, String> getHeaderList();
        String getXmlBody();
        String getJsonBody();
    }
}