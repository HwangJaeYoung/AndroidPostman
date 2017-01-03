package onem2m.keti.androidpostman.domain;

import onem2m.keti.androidpostman.ui.result.listview.ViewForArrayAdapterForResultHeader;

/**
 * Created by Blossom on 2016-11-10.
 */

public class ResultHeaderItem implements ViewForArrayAdapterForResultHeader.IHeaderAdd{
    private String headerName, headerValue;

    public ResultHeaderItem(String headerName, String headerValue) {
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
