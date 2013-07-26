package com.shnarped.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;

public class ViewAccount implements OnClickListener {

	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	Button invite_frnd,view_tracking_list,set_fav_team,settings;
	ViewSettings mViewSettings;
	public ViewAccount(MainActivity a) {
		this.mActivity = a;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_account, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("PROFILE");
		invite_frnd = (Button)_view2.findViewById(R.id.invite_frnd);
		view_tracking_list = (Button)_view2.findViewById(R.id.view_tracking_list);
		set_fav_team = (Button)_view2.findViewById(R.id.set_fav_team);
		settings = (Button)_view2.findViewById(R.id.settings);
		invite_frnd.setOnClickListener(this);
		view_tracking_list.setOnClickListener(this);
		set_fav_team.setOnClickListener(this);
		settings.setOnClickListener(this);
	}
	public View getView() {
		return _view;
	}
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.invite_frnd) {
			
		}else if (v.getId() == R.id.view_tracking_list) {
			
		}else if (v.getId() == R.id.set_fav_team) {
			
		}else if (v.getId() == R.id.settings) {
			mActivity.viewLayout.removeAllViews();
			mViewSettings = new ViewSettings(mActivity);
			mActivity.viewLayout.addView(mViewSettings.getView());
		}
		
	}
}
