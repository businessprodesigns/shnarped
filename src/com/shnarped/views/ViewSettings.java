package com.shnarped.views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;

public class ViewSettings implements OnClickListener {
	
	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	Button edit_prof,change_pass,about,signout;
	ViewEditProfile mViewEditProfile;
	ViewChangePassword mViewChangePassword;
	public ViewSettings(MainActivity a) {
		this.mActivity = a;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_settings, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("SETTINGS");
		edit_prof = (Button)_view2.findViewById(R.id.edit_prof);
		change_pass = (Button)_view2.findViewById(R.id.change_pass);
		about = (Button)_view2.findViewById(R.id.about);
		signout = (Button)_view2.findViewById(R.id.signout);
		edit_prof.setOnClickListener(this);
		change_pass.setOnClickListener(this);
		about.setOnClickListener(this);
		signout.setOnClickListener(this);
	}
	public View getView() {
		return _view;
	}
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.edit_prof) {
			mActivity.viewLayout.removeAllViews();
			mViewEditProfile = new ViewEditProfile(mActivity);
			mActivity.viewLayout.addView(mViewEditProfile.getView());
		}else if (v.getId() == R.id.change_pass) {
			mActivity.viewLayout.removeAllViews();
			mViewChangePassword = new ViewChangePassword(mActivity);
			mActivity.viewLayout.addView(mViewChangePassword.getView());
		}else if (v.getId() == R.id.about) {
			
		}else if (v.getId() == R.id.signout) {
			/*mActivity.viewLayout.removeAllViews();
			mViewSettings = new ViewSettings(mActivity);
			mActivity.viewLayout.addView(mViewSettings.getView());*/
		}
	}
	

}
