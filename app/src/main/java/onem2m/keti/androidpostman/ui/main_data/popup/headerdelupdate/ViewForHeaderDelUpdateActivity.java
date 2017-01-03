package onem2m.keti.androidpostman.ui.main_data.popup.headerdelupdate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import onem2m.keti.androidpostman.R;
import onem2m.keti.androidpostman.reuse.mvc.activity.AbstractViewForActivity;

/**
 * Created by Blossom on 2016-11-16.
 */

public class ViewForHeaderDelUpdateActivity extends AbstractViewForActivity {

    private Controller controller;

    private Button btHeaderDelete;
    private Button btHeaderUpdate;
    private Button btHeaderCancel;

    private EditText etDelUpHeaderNameItem;
    private EditText etDelUpHeaderValueItem;

    public ViewForHeaderDelUpdateActivity(Context context, Controller controller) {
        super(context);
        this.controller = controller;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_header_update_del, null);
    }

    @Override
    protected void initViews() {
        btHeaderDelete = (Button)findViewById(R.id.bt_header_delete);
        btHeaderUpdate = (Button)findViewById(R.id.bt_header_update);
        btHeaderCancel = (Button)findViewById(R.id.bt_header_cancel);

        etDelUpHeaderNameItem = (EditText)findViewById(R.id.et_del_up_header_name_item);
        etDelUpHeaderValueItem = (EditText)findViewById(R.id.et_del_up_header_value_item);
    }

    @Override
    protected void setEvent() {
        btHeaderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               controller.onDelete();
            }
        });

        btHeaderUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onUpdate(etDelUpHeaderNameItem.getText().toString(), etDelUpHeaderValueItem.getText().toString());
            }
        });

        btHeaderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onCancel();
            }
        });
    }

    public void setHeaderValue(String hName, String hValue) {
        etDelUpHeaderNameItem.setText(hName);
        etDelUpHeaderValueItem.setText(hValue);

    }

    interface Controller {
        void onDelete();
        void onUpdate(String headerName, String headerLength);
        void onCancel();
    }
}
