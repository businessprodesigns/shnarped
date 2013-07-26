package com.shnarped.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;
import com.shnarped.activity.SplashScreenActivity;

public class ViewAccountOffline implements OnClickListener {

	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	int btn_possition;
	Button signupLogin_btn;
	ViewSettings mViewSettings;
	ImageView prof_pic;
	TextView msg;
	public ViewAccountOffline(MainActivity a,int btn_pos) {
		this.mActivity = a;
		this.btn_possition = btn_pos;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.not_now, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("PROFILE");
		prof_pic = (ImageView)_view2.findViewById(R.id.prof_pic);
		msg = (TextView)_view2.findViewById(R.id.msg);
		if(btn_possition == 3){
			prof_pic.setImageResource(R.drawable.pounds_pic);
			msg.setText("Send a pound to a pro \n and get a pound back!");
		}else if(btn_possition == 4){
			prof_pic.setImageResource(R.drawable.profile_pic);
			msg.setText("You haven't selected \n your favorite team yet.");
		}
		signupLogin_btn = (Button)_view2.findViewById(R.id.signupLogin_btn);
		signupLogin_btn.setOnClickListener(this);
	}
	public View getView() {
		return _view;
	}
	@Override
	public void onClick(View v) {
		
		if (v.getId() == R.id.signupLogin_btn) {
			Intent intent = new Intent(mActivity,SplashScreenActivity.class);
	        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        mActivity.startActivity(intent);
		}
	}
}
