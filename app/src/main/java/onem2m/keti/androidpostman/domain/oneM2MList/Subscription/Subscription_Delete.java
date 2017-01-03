package onem2m.keti.androidpostman.domain.oneM2MList.Subscription;

import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.ui.main_list.listview.ViewForArrayAdapterForMainActivity;

/**
 * Created by Blossom on 2016-11-03.
 */

public class Subscription_Delete implements ViewForArrayAdapterForMainActivity.IRequestList {

    private String title;
    private String operation = "DELETE";
    private LinkedHashMap<String, String> subscriptionDeleteHeaderList;

    private String KEY_HEADER_ACCEPT = "Accept";
    private String KEY_HEADER_X_M2M_RI = "X-M2M-RI";
    private String KEY_HEADER_X_M2M_ORIGIN = "X-M2M-Origin";

    public Subscription_Delete() {
        this.title = "Subscription 리소스 삭제";

        this.subscriptionDeleteHeaderList = new LinkedHashMap<String, String>();

        this.subscriptionDeleteHeaderList.put(KEY_HEADER_ACCEPT, "application/xml");
        this.subscriptionDeleteHeaderList.put(KEY_HEADER_X_M2M_RI, "12345");
        this.subscriptionDeleteHeaderList.put(KEY_HEADER_X_M2M_ORIGIN, "Origin");
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getOperation() { return operation; }

    @Override
    public LinkedHashMap<String, String> getHeaderList() {
        return subscriptionDeleteHeaderList;
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
