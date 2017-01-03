package onem2m.keti.androidpostman.ui.main_data.popup.headercreation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by Blossom on 2016-11-03.
 */

public class HeaderCreationActivity extends Activity implements ViewForHeaderCreationActivity.Controller {

    private ViewForHeaderCreationActivity view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new ViewForHeaderCreationActivity(getApplicationContext(), this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view.getRoot());
    }

    @Override
    public void onConfirm(String hName, String hValue) {

        String headerName = hName.replaceAll("\\p{Space}", "");
        String headerValue = hValue.replaceAll("\\p{Space}", "");

        if(headerName.length() != 0 && headerValue.length() != 0) {
            // Header Listview Update
            Intent resultIntent = new Intent();

            resultIntent.putExtra("headerName", headerName);
            resultIntent.putExtra("headerValue", headerValue);

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