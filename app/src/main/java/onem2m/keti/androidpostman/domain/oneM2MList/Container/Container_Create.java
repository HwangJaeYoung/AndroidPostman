package onem2m.keti.androidpostman.domain.oneM2MList.Container;

import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.ui.main_list.listview.ViewForArrayAdapterForMainActivity;

/**
 * Created by Blossom on 2016-11-03.
 */

public class Container_Create implements ViewForArrayAdapterForMainActivity.IRequestList {

    private String title;
    private String operation = "POST";
    private String xmlBody;
    private String jsonBody;

    private LinkedHashMap<String, String> containerCreateHeaderList;

    private String KEY_HEADER_ACCEPT = "Accept";
    private String KEY_HEADER_X_M2M_RI = "X-M2M-RI";
    private String KEY_HEADER_X_M2M_ORIGIN = "X-M2M-Origin";
    private String KEY_HEADER_CONTENT_TYPE = "Content-Type";


    public Container_Create() {
        this.title = "Container 리소스 생성";

        this.containerCreateHeaderList = new LinkedHashMap<String, String>();

        this.containerCreateHeaderList.put(KEY_HEADER_ACCEPT, "application/xml");
        this.containerCreateHeaderList.put(KEY_HEADER_X_M2M_RI, "12345");
        this.containerCreateHeaderList.put(KEY_HEADER_X_M2M_ORIGIN, "Origin");
        this.containerCreateHeaderList.put(KEY_HEADER_CONTENT_TYPE, "application/vnd.onem2m-res+xml; ty=3");

        xmlBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<m2m:cnt xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" rn=\"keti_container\">\n" +
                "</m2m:cnt>";

        jsonBody = "{\n" +
                "    \"m2m:cnt\": {\n" +
                "        \"rn\": \"keti_container\"\n" +
                "    }\n" +
                "}";
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getOperation() { return operation; }

    @Override
    public LinkedHashMap<String, String> getHeaderList() {
        return containerCreateHeaderList;
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
