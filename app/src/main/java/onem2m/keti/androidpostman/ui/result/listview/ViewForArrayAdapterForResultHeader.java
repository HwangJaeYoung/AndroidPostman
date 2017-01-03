package onem2m.keti.androidpostman.ui.result.listview;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import onem2m.keti.androidpostman.R;
import onem2m.keti.androidpostman.reuse.listview.AbstractViewForListViewItem;
import onem2m.keti.androidpostman.reuse.listview.IListViewItem;

/**
 * Created by Blossom on 2016-11-10.
 */

public class ViewForArrayAdapterForResultHeader extends AbstractViewForListViewItem {

    private EditText etResultHeaderNameItem;
    private EditText etResultHeaderValueItem;

    public ViewForArrayAdapterForResultHeader(Context context) {
        super(context);
    }

    @Override
    protected View inflate() {
        return inflate(getContext( ), R.layout.item_result_header, this);
    }

    @Override
    protected void initViews() {
        etResultHeaderNameItem = (EditText) findViewById(R.id.et_result_header_name_item);
        etResultHeaderValueItem = (EditText)findViewById(R.id.et_result_header_value_item);
    }

    @Override
    protected void setEvents() {

    }

    @Override
    protected void setData(IListViewItem aIListViewItem) {
        String headerName = ((IHeaderAdd)aIListViewItem).getHeaderName();
        String headerValue = ((IHeaderAdd)aIListViewItem).getHeaderValue();

        etResultHeaderNameItem.setText(headerName);
        etResultHeaderValueItem.setText(headerValue);
    }

    public interface IHeaderAdd extends IListViewItem {
        // 뽑아낼 데이터의 메소드를 정의
        String getHeaderName();
        String getHeaderValue();
    }
}
