package com.shnarped.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shnarped.activites.R;
import com.shnarped.views.ViewFeatured;
import com.shnarped.views.ViewSignup;


public class SplashScreenActivity extends Activity implements OnClickListener, OnTouchListener {
	
	Button signup_btn,login_btn,notnow_btn,back_btn,back;
	RelativeLayout viewLayout,loginLay,buttonLay,mainLay,viewForgotPassword;
	ViewSignup mViewSignup;
	TextView forgot_pass;
	EditText fst_name,last_name,email_addrss,signup_password,conf_password,forgot_emailAddress;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_splash);
	    
	    signup_btn = (Button)findViewById(R.id.signup_btn);
	    login_btn = (Button)findViewById(R.id.login_btn);
	    notnow_btn = (Button)findViewById(R.id.notnow_btn);
	    viewLayout = (RelativeLayout)findViewById(R.id.viewLay);
	    loginLay = (RelativeLayout)findViewById(R.id.loginLay);
	    buttonLay = (RelativeLayout)findViewById(R.id.buttonLay);
	    viewForgotPassword = (RelativeLayout)findViewById(R.id.viewForgotPassword);
	    mainLay = (RelativeLayout)findViewById(R.id.mainLay);
	    forgot_pass = (TextView)findViewById(R.id.forgot_pass);
	    fst_name = (EditText)findViewById(R.id.fst_name);
	    last_name = (EditText)findViewById(R.id.last_name);
	    email_addrss = (EditText)findViewById(R.id.email_addrss);
	    signup_password = (EditText)findViewById(R.id.signup_password);
	    conf_password = (EditText)findViewById(R.id.conf_password);
	    forgot_emailAddress = (EditText)findViewById(R.id.forgot_emailAddress);
	    back_btn = (Button)findViewById(R.id.back_btn);
	    back = (Button)findViewById(R.id.back);
	    signup_btn.setOnClickListener(this);
	    login_btn.setOnClickListener(this);
	    notnow_btn.setOnClickListener(this);
	    forgot_pass.setOnClickListener(this);
	    back_btn.setOnClickListener(this);
	    back.setOnClickListener(this);
	    //mainLay.setOnTouchListener(this);
	}
	@Override
	public void onClick(View v) {
		
		if (v.getId() == R.id.signup_btn) {
			viewLayout.setVisibility(View.VISIBLE);
			fst_name.requestFocus();
			/*SplashScreenActivity.this.viewLayout.removeAllViews();
    		mViewSignup = new ViewSignup(SplashScreenActivity.this);
    		SplashScreenActivity.this.viewLayout.addView(mViewSignup.getView());*/
			
		}else if (v.getId() == R.id.login_btn) {
			buttonLay.setVisibility(View.INVISIBLE);
			loginLay.setVisibility(View.VISIBLE);
			forgot_pass.setVisibility(View.VISIBLE);
			mainLay.setOnTouchListener(this);
		}else if (v.getId() == R.id.notnow_btn) {
			finish();
		}else if (v.getId() == R.id.forgot_pass) {
			viewForgotPassword.setVisibility(View.VISIBLE);
			forgot_emailAddress.requestFocus();
		}else if (v.getId() == R.id.back_btn) {
			viewLayout.setVisibility(View.INVISIBLE);
		}else if (v.getId() == R.id.back) {
			viewForgotPassword.setVisibility(View.INVISIBLE);
		}
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		buttonLay.setVisibility(View.VISIBLE);
		loginLay.setVisibility(View.INVISIBLE);
		forgot_pass.setVisibility(View.INVISIBLE);
		return false;
	}

}
