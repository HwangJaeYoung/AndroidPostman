package onem2m.keti.androidpostman.domain.oneM2MList.AE;

import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.ui.main_list.listview.ViewForArrayAdapterForMainActivity;

/**
 * Created by Blossom on 2016-11-03.
 */

public class AE_Update implements ViewForArrayAdapterForMainActivity.IRequestList {

    private String title;
    private String operation = "PUT";
    private String xmlBody;
    private String jsonBody;

    private LinkedHashMap<String, String> aeUpdateHeaderList;

    private String KEY_HEADER_ACCEPT = "Accept";
    private String KEY_HEADER_X_M2M_RI = "X-M2M-RI";
    private String KEY_HEADER_X_M2M_ORIGIN = "X-M2M-Origin";
    private String KEY_HEADER_CONTENT_TYPE = "Content-Type";

    public AE_Update() {
        this.title = "AE 리소스 변경";

        this.aeUpdateHeaderList = new LinkedHashMap<String, String>();

        this.aeUpdateHeaderList.put(KEY_HEADER_ACCEPT, "application/xml");
        this.aeUpdateHeaderList.put(KEY_HEADER_X_M2M_RI, "12345");
        this.aeUpdateHeaderList.put(KEY_HEADER_X_M2M_ORIGIN, "Origin");
        this.aeUpdateHeaderList.put(KEY_HEADER_CONTENT_TYPE, "application/vnd.onem2m-res+xml");

        xmlBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<m2m:ae xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <api>0.2.481.2.0001.001.000111</api>\n" +
                "</m2m:ae>";

        jsonBody = "{\n" +
                "    \"m2m:ae\": {\n" +
                "        \"api\": \"0.2.481.2.0001.001.000111\"\n" +
                "    }\n" +
                "}";
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getOperation() { return operation; }

    @Override
    public LinkedHashMap<String, String> getHeaderList() {
        return aeUpdateHeaderList;
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
