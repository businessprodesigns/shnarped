package com.shnarped.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;

public class ViewPounds implements OnClickListener {

	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	Button receive_btn,send_btn;
	TextView poundTxt;ListView received_list,send_list;
	public ViewPounds(MainActivity a) {
		this.mActivity = a;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_pounds, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("POUNDS");
		receive_btn = (Button)_view2.findViewById(R.id.receive_btn);
		send_btn = (Button)_view2.findViewById(R.id.send_btn);
		poundTxt = (TextView)_view2.findViewById(R.id.poundTxt);
		received_list = (ListView)_view2.findViewById(R.id.received_list);
		send_list = (ListView)_view2.findViewById(R.id.send_list);
		send_btn.setOnClickListener(this);
		receive_btn.setOnClickListener(this);
		poundTxt.setText("1 Pound received");
	}
	public View getView() {
		return _view;
	}
	@Override
	public void onClick(View v) {
		
		if (v.getId() == R.id.receive_btn) {
			receive_btn.setBackgroundResource(R.drawable.receive_selected);
			send_btn.setBackgroundResource(R.drawable.send_normal);
			poundTxt.setText("1 Pound received");
		}else if(v.getId() == R.id.send_btn){
			send_btn.setBackgroundResource(R.drawable.send_selected);
			receive_btn.setBackgroundResource(R.drawable.receive_normal);
			poundTxt.setText("1 Pound sent");
		}
		
	}
}
