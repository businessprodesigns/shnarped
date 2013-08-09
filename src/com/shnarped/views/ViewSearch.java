package com.shnarped.views;

import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;
import com.shnarped.utils.Utilities;
public class ViewSearch implements OnClickListener {

	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	Button team,player_with_stalls,verified,most_points,most_pounds;
	Context _context;
	public ViewSearch(MainActivity a,Context context) {
		this.mActivity = a;
		_context = context;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_search, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("SEARCH");
		team = (Button)_view2.findViewById(R.id.team);
		player_with_stalls = (Button)_view2.findViewById(R.id.player_with_stalls);
		verified = (Button)_view2.findViewById(R.id.verified);
		most_points = (Button)_view2.findViewById(R.id.most_points);
		most_pounds = (Button)_view2.findViewById(R.id.most_pounds);
		team.setOnClickListener(this);
		player_with_stalls.setOnClickListener(this);
		verified.setOnClickListener(this);
		most_points.setOnClickListener(this);
		most_pounds.setOnClickListener(this);
		
		team.setTypeface(new Utilities.FontsClass(_context).getCollegeECFonts());
		player_with_stalls.setTypeface(new Utilities.FontsClass(_context).getCollegeECFonts());
		verified.setTypeface(new Utilities.FontsClass(_context).getCollegeECFonts());
		most_points.setTypeface(new Utilities.FontsClass(_context).getCollegeECFonts());
		most_pounds.setTypeface(new Utilities.FontsClass(_context).getCollegeECFonts());
	}
	public View getView() {
		return _view;
	}
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.team) {
			selectLeague(1);
			
		}else if (v.getId() == R.id.player_with_stalls) {
			selectLeague(2);
			
		}else if (v.getId() == R.id.verified) {
			selectLeague(3);
			
		}else if (v.getId() == R.id.most_points) {
			
		}else if (v.getId() == R.id.most_pounds) {
			
		}
		
	}
	public void selectLeague(int check){
		
		final Dialog dialog = new Dialog(_context, R.style.CustomTheme);
		dialog.setContentView(R.layout.select_team);
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		

		Button t1 = (Button) dialog
				.findViewById(R.id.t1);
		t1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});Button t2 = (Button) dialog
				.findViewById(R.id.t2);
		t2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		Button t3 = (Button) dialog
				.findViewById(R.id.t3);
		t3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		Button t4 = (Button) dialog
				.findViewById(R.id.t4);
		t4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		Button t5 = (Button) dialog
				.findViewById(R.id.t5);
		t5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		Button cancel = (Button) dialog
				.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}
}
