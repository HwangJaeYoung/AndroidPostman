package onem2m.keti.androidpostman.domain.oneM2MList.Subscription;

import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.ui.main_list.listview.ViewForArrayAdapterForMainActivity;

/**
 * Created by Blossom on 2016-11-03.
 */

public class Subscription_Update implements ViewForArrayAdapterForMainActivity.IRequestList {

    private String title;
    private String operation = "PUT";
    private String xmlBody;
    private String jsonBody;

    private LinkedHashMap<String, String> subscriptionUpdateHeaderList;

    private String KEY_HEADER_ACCEPT = "Accept";
    private String KEY_HEADER_X_M2M_RI = "X-M2M-RI";
    private String KEY_HEADER_X_M2M_ORIGIN = "X-M2M-Origin";
    private String KEY_HEADER_CONTENT_TYPE = "Content-Type";

    public Subscription_Update() {
        this.title = "Subscription 리소스 변경";

        this.subscriptionUpdateHeaderList = new LinkedHashMap<String, String>();

        this.subscriptionUpdateHeaderList.put(KEY_HEADER_ACCEPT, "application/xml");
        this.subscriptionUpdateHeaderList.put(KEY_HEADER_X_M2M_RI, "12345");
        this.subscriptionUpdateHeaderList.put(KEY_HEADER_X_M2M_ORIGIN, "Origin");
        this.subscriptionUpdateHeaderList.put(KEY_HEADER_CONTENT_TYPE, "application/vnd.onem2m-res+xml");

        xmlBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<m2m:sub xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <enc>\n" +
                "       <net>3</net>\n" +
                "    </enc>\n" +
                "    <nu>mqtt://localhost/0.2.481.1.9999.999.20151112084621979</nu>\n" +
                "</m2m:sub>";

        jsonBody = "{\n" +
                "    \"m2m:sub\": {\n" +
                "        \"nu\": \"mqtt://localhost/0.2.481.1.9999.999.20151112084621979\"\n" +
                "    }\n" +
                "}";
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getOperation() { return operation; }

    @Override
    public LinkedHashMap<String, String> getHeaderList() {
        return subscriptionUpdateHeaderList;
    }

    @Override
    public String getXmlBody() {
        return xmlBody;
    }

    @Override
    public String getJsonBody() {
        return jsonBody;
    }
}
