package com.shnarped.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;

public class ViewChangePassword implements OnClickListener {

	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	Button save;
	EditText pass,new_pass,conf_pass;
	public ViewChangePassword(MainActivity a) {
		this.mActivity = a;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.password_change, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("CHANGE PASSWORD");
		pass = (EditText)_view2.findViewById(R.id.pass);
		new_pass = (EditText)_view2.findViewById(R.id.new_pass);
		conf_pass = (EditText)_view2.findViewById(R.id.conf_pass);
		save = (Button)_view2.findViewById(R.id.save);
		save.setOnClickListener(this);
	}
	public View getView() {
		return _view;
	}
	@Override
	public void onClick(View v) {
		
		if (v.getId() == R.id.save) {
			
		}
	}
}
