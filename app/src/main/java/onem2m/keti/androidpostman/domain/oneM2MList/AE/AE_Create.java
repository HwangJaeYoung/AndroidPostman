package onem2m.keti.androidpostman.domain.oneM2MList.AE;

import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.ui.main_list.listview.ViewForArrayAdapterForMainActivity;

/**
 * Created by Blossom on 2016-11-03.
 */

public class AE_Create implements ViewForArrayAdapterForMainActivity.IRequestList {

    private String title;
    private String operation = "POST";
    private String xmlBody;
    private String jsonBody;

    private LinkedHashMap<String, String> aeCreateHeaderList;

    private String KEY_HEADER_ACCEPT = "Accept";
    private String KEY_HEADER_X_M2M_RI = "X-M2M-RI";
    private String KEY_HEADER_X_M2M_ORIGIN = "X-M2M-Origin";
    private String KEY_HEADER_CONTENT_TYPE = "Content-Type";


    public AE_Create() {
        this.title = "AE 리소스 생성";

        this.aeCreateHeaderList = new LinkedHashMap<String, String>();

        this.aeCreateHeaderList.put(KEY_HEADER_ACCEPT, "application/xml");
        this.aeCreateHeaderList.put(KEY_HEADER_X_M2M_RI, "12345");
        this.aeCreateHeaderList.put(KEY_HEADER_X_M2M_ORIGIN, "Origin");
        this.aeCreateHeaderList.put(KEY_HEADER_CONTENT_TYPE, "application/vnd.onem2m-res+xml; ty=2");

        xmlBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<m2m:ae xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" rn=\"keti_ae\">\n" +
                "    <api>0.2.481.2.0001.001.000111</api>\n" +
                "</m2m:ae>";

        jsonBody = "{\n" +
                "    \"m2m:ae\": {\n" +
                "        \"rn\": \"keti_ae\",\n" +
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
        return aeCreateHeaderList;
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
