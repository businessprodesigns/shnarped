package com.shnarped.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;

public class ViewEditProfile implements OnClickListener {

	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	Button save;
	EditText editFstName,editLastNmae,editGender,editBirthday,editEmail,editTwitter;
	public ViewEditProfile(MainActivity a) {
		this.mActivity = a;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.profile_edit, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("EDIT PROFILE");
		editFstName = (EditText)_view2.findViewById(R.id.editFstName);
		editLastNmae = (EditText)_view2.findViewById(R.id.editLastNmae);
		editGender = (EditText)_view2.findViewById(R.id.editGender);
		editBirthday = (EditText)_view2.findViewById(R.id.editBirthday);
		editEmail = (EditText)_view2.findViewById(R.id.editEmail);
		editTwitter = (EditText)_view2.findViewById(R.id.editTwitter);
		save = (Button)_view2.findViewById(R.id.save);
		save.setOnClickListener(this);
		editEmail.setEnabled(false);
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
