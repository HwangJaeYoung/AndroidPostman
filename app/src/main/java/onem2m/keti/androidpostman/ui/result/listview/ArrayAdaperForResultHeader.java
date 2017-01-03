package onem2m.keti.androidpostman.ui.result.listview;

import android.content.Context;

import onem2m.keti.androidpostman.reuse.listview.AbstractArrayAdapter;
import onem2m.keti.androidpostman.reuse.listview.AbstractViewForListViewItem;

/**
 * Created by Blossom on 2016-11-10.
 */

public class ArrayAdaperForResultHeader extends AbstractArrayAdapter<ViewForArrayAdapterForResultHeader.IHeaderAdd> {

    public ArrayAdaperForResultHeader(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public AbstractViewForListViewItem getInstance() {
        return new ViewForArrayAdapterForResultHeader(getContext());
    }
}
