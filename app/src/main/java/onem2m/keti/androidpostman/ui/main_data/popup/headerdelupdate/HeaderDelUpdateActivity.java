package onem2m.keti.androidpostman.ui.main_data.popup.headerdelupdate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by Blossom on 2016-11-16.
 */

public class HeaderDelUpdateActivity extends Activity implements ViewForHeaderDelUpdateActivity.Controller {

    private ViewForHeaderDelUpdateActivity view;
    private int positionDelupdate;
    public static final int RESULT_DELETE = 1;

    private String headerName;
    private String headerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new ViewForHeaderDelUpdateActivity(getApplicationContext(), this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        view.setHeaderValue(getIntent().getStringExtra("HeaderName"), getIntent().getStringExtra("HeaderValue"));

        setContentView(view.getRoot());
    }

    @Override
    public void onDelete() {
        int position = getIntent().getIntExtra("Position", 0);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("Position", position);

        setResult(RESULT_DELETE, resultIntent);

        finish();
    }

    @Override
    public void onUpdate(String hName, String hValue) {
        String headerName = hName.replaceAll("\\p{Space}", "");
        String headerValue = hValue.replaceAll("\\p{Space}", "");
        int position = getIntent().getIntExtra("Position", 0);

        if(headerName.length() != 0 && headerValue.length() != 0) {
            // Header Listview Update
            Intent resultIntent = new Intent();

            resultIntent.putExtra("headerName", headerName);
            resultIntent.putExtra("headerValue", headerValue);
            resultIntent.putExtra("Position", position);

            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Please fill in the header name or header value", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancel() {
        finish();
    }
}