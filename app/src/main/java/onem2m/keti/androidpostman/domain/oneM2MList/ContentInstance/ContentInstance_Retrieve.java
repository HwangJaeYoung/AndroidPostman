package onem2m.keti.androidpostman.domain.oneM2MList.ContentInstance;

import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.ui.main_list.listview.ViewForArrayAdapterForMainActivity;

/**
 * Created by Blossom on 2016-11-03.
 */

public class ContentInstance_Retrieve implements ViewForArrayAdapterForMainActivity.IRequestList {

    private String title;
    private String operation = "GET";
    private LinkedHashMap<String, String> contentInstanceRetrieveHeaderList;

    private String KEY_HEADER_ACCEPT = "Accept";
    private String KEY_HEADER_X_M2M_RI = "X-M2M-RI";
    private String KEY_HEADER_X_M2M_ORIGIN = "X-M2M-Origin";

    public ContentInstance_Retrieve() {
        this.title = "contentInstance 리소스 조회";

        this.contentInstanceRetrieveHeaderList = new LinkedHashMap<String, String>();

        this.contentInstanceRetrieveHeaderList.put(KEY_HEADER_ACCEPT, "application/xml");
        this.contentInstanceRetrieveHeaderList.put(KEY_HEADER_X_M2M_RI, "12345");
        this.contentInstanceRetrieveHeaderList.put(KEY_HEADER_X_M2M_ORIGIN, "Origin");
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getOperation() { return operation; }

    @Override
    public LinkedHashMap<String, String> getHeaderList() {
        return contentInstanceRetrieveHeaderList;
    }

    @Override
    public String getXmlBody() {
        return null;
    }

    @Override
    public String getJsonBody() {
        return null;
    }
}