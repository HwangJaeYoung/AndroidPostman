package onem2m.keti.androidpostman.domain.oneM2MList.ContentInstance;

import java.util.LinkedHashMap;

import onem2m.keti.androidpostman.ui.main_list.listview.ViewForArrayAdapterForMainActivity;

/**
 * Created by Blossom on 2016-11-03.
 */

public class ContentInstance_Create implements ViewForArrayAdapterForMainActivity.IRequestList {

    private String title;
    private String operation = "POST";
    private String xmlBody;
    private String jsonBody;

    private LinkedHashMap<String, String> contentInstanceCreateHeaderList;

    private String KEY_HEADER_ACCEPT = "Accept";
    private String KEY_HEADER_X_M2M_RI = "X-M2M-RI";
    private String KEY_HEADER_X_M2M_ORIGIN = "X-M2M-Origin";
    private String KEY_HEADER_CONTENT_TYPE = "Content-Type";


    public ContentInstance_Create() {
        this.title = "contentInstance 리소스 생성";

        this.contentInstanceCreateHeaderList = new LinkedHashMap<String, String>();

        this.contentInstanceCreateHeaderList.put(KEY_HEADER_ACCEPT, "application/xml");
        this.contentInstanceCreateHeaderList.put(KEY_HEADER_X_M2M_RI, "12345");
        this.contentInstanceCreateHeaderList.put(KEY_HEADER_X_M2M_ORIGIN, "Origin");
        this.contentInstanceCreateHeaderList.put(KEY_HEADER_CONTENT_TYPE, "application/vnd.onem2m-res+xml; ty=4");

        xmlBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<m2m:cin xmlns:m2m=\"http://www.onem2m.org/xml/protocols\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <con>62590</con>\n" +
                "</m2m:cin>";

        jsonBody = "{\n" +
                "    \"m2m:cin\": {\n" +
                "        \"con\": \"62590\"\n" +
                "    }\n" +
                "}";
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getOperation() { return operation; }

    @Override
    public LinkedHashMap<String, String> getHeaderList() {
        return contentInstanceCreateHeaderList;
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
