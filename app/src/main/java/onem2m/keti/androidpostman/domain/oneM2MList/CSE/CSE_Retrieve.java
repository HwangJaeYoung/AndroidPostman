package onem2m.keti.androidpostman.domain.oneM2MList.CSE;

import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.ui.main_list.listview.ViewForArrayAdapterForMainActivity;

/**
 * Created by Blossom on 2016-10-27.
 */

public class CSE_Retrieve implements ViewForArrayAdapterForMainActivity.IRequestList {

    private String title;
    private String operation = "GET";
    private LinkedHashMap<String, String> cseRetrieveHeaderList;

    private String KEY_HEADER_ACCEPT = "Accept";
    private String KEY_HEADER_X_M2M_RI = "X-M2M-RI";
    private String KEY_HEADER_X_M2M_ORIGIN = "X-M2M-Origin";

    public CSE_Retrieve() {
        this.title = "CSE Base 리소스 조회";

        this.cseRetrieveHeaderList = new LinkedHashMap<String, String>();

        this.cseRetrieveHeaderList.put(KEY_HEADER_ACCEPT, "application/xml");
        this.cseRetrieveHeaderList.put(KEY_HEADER_X_M2M_RI, "12345");
        this.cseRetrieveHeaderList.put(KEY_HEADER_X_M2M_ORIGIN, "Origin");
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getOperation() { return operation; }

    @Override
    public LinkedHashMap<String, String> getHeaderList() {
        return cseRetrieveHeaderList;
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