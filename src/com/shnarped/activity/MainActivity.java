package com.shnarped.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shnarped.activites.R;
import com.shnarped.views.ViewAccount;
import com.shnarped.views.ViewAccountOffline;
import com.shnarped.views.ViewFeatured;
import com.shnarped.views.ViewPounds;
import com.shnarped.views.ViewSearch;
import com.shnarped.views.ViewStream;

public class MainActivity extends Activity implements OnClickListener {

	Button featuredTab,streamTab,SearchTab,PoundsTab,AccountTab;
	public TextView headertitleTxt;
	public LinearLayout viewLayout;
	ViewFeatured mViewFeatured;
	ViewStream mViewStream;
	ViewSearch mViewSearch;
	ViewPounds mViewPounds;
	ViewAccount mViewAccount;
	public Button tracking;
	ViewAccountOffline mViewAccountOffline;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		featuredTab = (Button) findViewById(R.id.featuredTab);
		streamTab = (Button) findViewById(R.id.streamTab);
		SearchTab = (Button) findViewById(R.id.searchTab);
		PoundsTab = (Button) findViewById(R.id.poundTab);
		AccountTab = (Button) findViewById(R.id.accountTab);
		tracking = (Button) findViewById(R.id.tracking);
		
        headertitleTxt = (TextView)findViewById(R.id.headerTxt);
        viewLayout = (LinearLayout) findViewById(R.id.tabcontent);
        featuredTab.setOnClickListener(this);
        streamTab.setOnClickListener(this);
        SearchTab.setOnClickListener(this);
        PoundsTab.setOnClickListener(this);
        AccountTab.setOnClickListener(this);
        
        init();
	}
	private void init() {
    	
    	load_selected_view(0);
	}
	public void load_selected_view(int btnSelectedPosition){
		if (btnSelectedPosition == 0) {
    		MainActivity.this.viewLayout.removeAllViews();
    		mViewFeatured = new ViewFeatured(MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewFeatured.getView());
    		//Utils.CurrentView = mViewFeatured.getView();
    		
    	}else if (btnSelectedPosition == 1) {
    		MainActivity.this.viewLayout.removeAllViews();
    		mViewStream = new ViewStream(MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewStream.getView());
    		//Utils.CurrentView = mViewHotCoupons.getView();
    	}else if (btnSelectedPosition == 2) {
    		MainActivity.this.viewLayout.removeAllViews();
    		mViewSearch = new ViewSearch(MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewSearch.getView());
    		//Utils.CurrentView = mViewExpiringSoonActivity.getView();
    	}else if (btnSelectedPosition == 3) {
    		MainActivity.this.viewLayout.removeAllViews();
    		/*mViewPounds = new ViewPounds(MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewPounds.getView());*/
    		mViewAccountOffline = new ViewAccountOffline(MainActivity.this,btnSelectedPosition);
    		MainActivity.this.viewLayout.addView(mViewAccountOffline.getView());
    		
    		//Utils.CurrentView = mViewHotCoupons.getView();
    	}else if (btnSelectedPosition == 4) {
    		MainActivity.this.viewLayout.removeAllViews();
    		/*mViewAccount = new ViewAccount(MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewAccount.getView());*/
    		mViewAccountOffline = new ViewAccountOffline(MainActivity.this,btnSelectedPosition);
    		MainActivity.this.viewLayout.addView(mViewAccountOffline.getView());
    		
    		//Utils.CurrentView = mViewExpiringSoonActivity.getView();
    	}
    	//setBtnBg(btnSelectedPosition);
	}
	/*private void setBtnBg(int btnSelectedPosition) {
    	featuredTab.set(R.drawable.featured);
    	streamTab.setImageResource(R.drawable.stream);
    	expiringSoonTab.setImageResource(R.drawable.expiring_soon);
		
    	Resources r = getResources();
		Drawable[] layers = new Drawable[2];
    	switch (btnSelectedPosition) {
    		
		case 0:
			layers[0] = r.getDrawable(R.drawable.featured);
			layers[1] = r.getDrawable(R.drawable.selection_tab);
			LayerDrawable layerDrawable = new LayerDrawable(layers);
			//imgView3.setImageDrawable(layerDrawable);
			featuredTab.setImageDrawable(layerDrawable);
			break;
		case 1:
			hotCouponTab.setImageResource(R.drawable.hot_coupon_hover);
			break;
		case 2:
			expiringSoonTab.setImageResource(R.drawable.expiring_soon_hover);
			break;
		}
    }*/
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.featuredTab) {
			load_selected_view(0);
		}else if (v.getId() == R.id.streamTab) {
			load_selected_view(1);
		}else if (v.getId() == R.id.searchTab) {
			load_selected_view(2);
		}else if (v.getId() == R.id.poundTab) {
			load_selected_view(3);
		}else if (v.getId() == R.id.accountTab) {
			load_selected_view(4);
		}
	}

	

}
