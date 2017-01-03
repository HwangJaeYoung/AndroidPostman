package onem2m.keti.androidpostman.ui.main_data.popup.headercreation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import onem2m.keti.androidpostman.R;
import onem2m.keti.androidpostman.reuse.mvc.activity.AbstractViewForActivity;

/**
 * Created by Blossom on 2016-11-03.
 */

public class ViewForHeaderCreationActivity  extends AbstractViewForActivity {

    private Controller controller;

    private Button btAddHeaderConfirm;
    private Button btAddHeaderCancel;
    private EditText etAddHeaderNameItem;
    private EditText etAddHeaderNameValue;

    public ViewForHeaderCreationActivity(Context context, Controller controller) {
        super(context);
        this.controller = controller;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_header_creation, null);
    }

    @Override
    protected void initViews() {
        btAddHeaderConfirm = (Button)findViewById(R.id.bt_add_header_confirm);
        btAddHeaderCancel = (Button)findViewById(R.id.bt_add_header_cancel);
        etAddHeaderNameItem = (EditText)findViewById(R.id.et_add_header_name_item);
        etAddHeaderNameValue = (EditText)findViewById(R.id.et_add_header_value_item);
    }

    @Override
    protected void setEvent() {

        btAddHeaderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onConfirm(etAddHeaderNameItem.getText().toString(), etAddHeaderNameValue.getText().toString());
            }
        });

        btAddHeaderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onCancel();
            }
        });
    }

    interface Controller {
        void onConfirm(String headerName, String headerLength);
        void onCancel();
    }
}