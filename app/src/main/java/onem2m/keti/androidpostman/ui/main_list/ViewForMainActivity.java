package onem2m.keti.androidpostman.ui.main_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import onem2m.keti.androidpostman.R;
import onem2m.keti.androidpostman.reuse.listview.IListViewItem;
import onem2m.keti.androidpostman.reuse.mvc.activity.AbstractViewForActivity;
import onem2m.keti.androidpostman.ui.main_list.listview.ArrayAdapterForMainActivity;
import onem2m.keti.androidpostman.ui.main_list.listview.ViewForArrayAdapterForMainActivity;

/**
 * Created by Blossom on 2016-12-07.
 */

public class ViewForMainActivity extends AbstractViewForActivity {

    private Controller controller;
    private ListView lvMainListTab;
    private ArrayAdapterForMainActivity arrayAdapterForMainActivity;
    private ArrayList<ViewForArrayAdapterForMainActivity.IRequestList> requestLists;

    public ViewForMainActivity(Context context, Controller controller) {
        super(context);
        this.controller = controller;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_main_list, null);
    }

    @Override
    protected void initViews() {
        arrayAdapterForMainActivity = new ArrayAdapterForMainActivity(getContext( ), 0);
        lvMainListTab = (ListView)findViewById(R.id.lv_main_list_tab);
        lvMainListTab.setAdapter(arrayAdapterForMainActivity);

        requestLists = new ArrayList<ViewForArrayAdapterForMainActivity.IRequestList>();
    }

    @Override
    protected void setEvent() {
        lvMainListTab.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controller.requestItemClick(position, requestLists.get(position));
            }
        });
    }

    public void addRequestListToArrayList(ViewForArrayAdapterForMainActivity.IRequestList requestItems) {
        requestLists.add(requestItems);
        arrayAdapterForMainActivity.clear();
        arrayAdapterForMainActivity.addAll(requestLists);
    }

    interface Controller {
        void requestItemClick(int position, ViewForArrayAdapterForMainActivity.IRequestList requestData);
    }

    public interface IHeaderAdd extends IListViewItem {
        // 뽑아낼 데이터의 메소드를 정의
        String getHeaderName();
        String getHeaderValue();
    }
}