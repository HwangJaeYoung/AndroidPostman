package onem2m.keti.androidpostman.reuse.listview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

// <T extends IListViewItem> : T가 IListViewItem을 상속, 구현하는 클래스가 되어야 한다.
public abstract class AbstractArrayAdapter<T extends IListViewItem> extends ArrayAdapter<T> {
	
	public AbstractArrayAdapter(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		AbstractViewForListViewItem abstractViewForListViewItem = (AbstractViewForListViewItem) convertView;

		if (abstractViewForListViewItem == null) {
			abstractViewForListViewItem = getInstance();
		}

		// 새로운 뷰이던 기존 뷰이던 간에 뷰에 데이터를 바꾸거나 채워야 하기 때문에 구현 해 놓음.
		abstractViewForListViewItem.setData(getItem(position));
	
		return abstractViewForListViewItem; // 새로 구성된 뷰를 리턴한다.
	}
	public abstract AbstractViewForListViewItem getInstance();
}