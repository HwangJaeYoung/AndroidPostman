package onem2m.keti.androidpostman.ui.main_data.listview.header.listview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import onem2m.keti.androidpostman.R;
import onem2m.keti.androidpostman.reuse.listview.AbstractViewForListViewItem;
import onem2m.keti.androidpostman.reuse.listview.IListViewItem;

/**
 * Created by Blossom on 2016-10-27.
 */

public class ViewForArrayAdapterForHeaderListView extends AbstractViewForListViewItem {

    private TextView tvHeaderNameItem;
    private TextView tvHeaderNameValue;

    public ViewForArrayAdapterForHeaderListView(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return inflate(getContext( ), R.layout.item_request_new_header, this);
    }

    @Override
    protected void initViews() {
        tvHeaderNameItem = (TextView)findViewById(R.id.tv_header_name_item);
        tvHeaderNameValue = (TextView)findViewById(R.id.tv_header_value_item);
    }

    @Override
    protected void setEvents() { }

    @Override
    protected void setData(IListViewItem aIListViewItem) {
        String headerName = ((IHeaderAdd)aIListViewItem).getHeaderName();
        String headerValue = ((IHeaderAdd)aIListViewItem).getHeaderValue();

        tvHeaderNameItem.setText(headerName);
        tvHeaderNameValue.setText(headerValue);
    }

    public interface IHeaderAdd extends IListViewItem {
        // 뽑아낼 데이터의 메소드를 정의
        String getHeaderName();
        String getHeaderValue();
    }
}