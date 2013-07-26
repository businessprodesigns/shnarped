package com.shnarped.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;

public class ViewFeatured {

	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	public ViewFeatured(MainActivity a) {
		this.mActivity = a;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_featured, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("FEATURED");
		
	}
	public View getView() {
		return _view;
	}
	
}
