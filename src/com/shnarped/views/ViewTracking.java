package com.shnarped.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;

public class ViewTracking {

	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	ListView trackingList;
	LinearLayout tracklistFooterLay;
	public ViewTracking(MainActivity a) {
		this.mActivity = a;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_tracking, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("Tracking");
		trackingList = (ListView)_view2.findViewById(R.id.trackingList);
		View view = inflater.inflate(R.layout.footer_add_player, null);

		tracklistFooterLay = (LinearLayout) view.findViewById(R.id.tracklistFooterLay);
		
		trackingList.addFooterView(tracklistFooterLay);
	}
	public View getView() {
		return _view;
	}
}
