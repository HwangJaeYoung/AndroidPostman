package onem2m.keti.androidpostman.ui.main_list.listview;

import android.content.Context;

import onem2m.keti.androidpostman.reuse.listview.AbstractArrayAdapter;
import onem2m.keti.androidpostman.reuse.listview.AbstractViewForListViewItem;

/**
 * Created by Blossom on 2016-12-07.
 */

public class ArrayAdapterForMainActivity extends AbstractArrayAdapter <ViewForArrayAdapterForMainActivity.IRequestList> {

    public ArrayAdapterForMainActivity(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForArrayAdapterForMainActivity(getContext());
    }
}
