package onem2m.keti.androidpostman.domain;

import onem2m.keti.androidpostman.ui.main_data.listview.header.listview.ViewForArrayAdapterForHeaderListView;

/**
 * Created by Blossom on 2016-10-27.
 */

public class MainHeaderItem implements ViewForArrayAdapterForHeaderListView.IHeaderAdd {

    private String headerName, headerValue;

    public MainHeaderItem(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    @Override
    public String getHeaderName() {
        return headerName;
    }

    @Override
    public String getHeaderValue() {
        return headerValue;
    }
}