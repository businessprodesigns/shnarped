package com.shnarped.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;

public class ViewStream implements OnClickListener {
	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	public ViewStream(MainActivity a) {
		this.mActivity = a;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_tracking, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("STREAM");
		
	}
	public View getView() {
		return _view;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
