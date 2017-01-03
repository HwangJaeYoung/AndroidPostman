package onem2m.keti.androidpostman.ui.main_data.listview.header.listview;

import android.content.Context;

import onem2m.keti.androidpostman.reuse.listview.AbstractArrayAdapter;
import onem2m.keti.androidpostman.reuse.listview.AbstractViewForListViewItem;

/**
 * Created by Blossom on 2016-10-27.
 */

public class ArrayAdapterForHeaderListView extends AbstractArrayAdapter<ViewForArrayAdapterForHeaderListView.IHeaderAdd> {

    public ArrayAdapterForHeaderListView(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForArrayAdapterForHeaderListView(getContext());
    }
}