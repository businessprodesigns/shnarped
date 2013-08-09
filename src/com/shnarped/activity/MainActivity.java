package com.shnarped.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shnarped.activites.R;
import com.shnarped.menudrawer.MenuDrawer;
import com.shnarped.utils.Utilities;
import com.shnarped.views.ViewAbout;
import com.shnarped.views.ViewAccount;
import com.shnarped.views.ViewAccountOffline;
import com.shnarped.views.ViewChangePassword;
import com.shnarped.views.ViewFeatured;
import com.shnarped.views.ViewPounds;
import com.shnarped.views.ViewSearch;
import com.shnarped.views.ViewStream;
import com.shnarped.views.ViewTracking;

public class MainActivity extends Activity implements OnClickListener {
	
	private MenuDrawer mMenuDrawer;
	List<Object> items;
    private MenuAdapter mAdapter;
    private ListView mList;
    private ArrayList<GenrtArraylist> mItems;
    private LayoutInflater inflater = null;
    View _view;EditText search;
    
	Button featuredTab,streamTab,SearchTab,PoundsTab,AccountTab;
	public TextView headertitleTxt;
	public LinearLayout viewLayout;
	ViewFeatured mViewFeatured;
	ViewStream mViewStream;
	ViewSearch mViewSearch;
	ViewPounds mViewPounds;
	ViewAccount mViewAccount;
	public Button tracking;
	ViewAccountOffline mViewAccountOffline; ViewChangePassword mViewChangePassword;ViewAbout mViewAbout;
	ViewTracking mViewTracking;ImageButton openlist;ArrayList<GenrtArraylist> stringList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main_activity);
		
		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_CONTENT);
        mMenuDrawer.setContentView(R.layout.main_activity);
        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
        
		Utilities.USER_LOGEDIN = true;
		
		/*featuredTab = (Button) findViewById(R.id.featuredTab);
		streamTab = (Button) findViewById(R.id.streamTab);
		SearchTab = (Button) findViewById(R.id.searchTab);
		PoundsTab = (Button) findViewById(R.id.poundTab);
		AccountTab = (Button) findViewById(R.id.accountTab);*/
		tracking = (Button) findViewById(R.id.tracking);
		openlist =(ImageButton)findViewById(R.id.openlist);
        headertitleTxt = (TextView)findViewById(R.id.headerTxt);
        headertitleTxt.setTypeface(new Utilities.FontsClass(this).getCollegeFonts());
        
        viewLayout = (LinearLayout) findViewById(R.id.tabcontent);
       /* featuredTab.setOnClickListener(this);
        streamTab.setOnClickListener(this);
        SearchTab.setOnClickListener(this);
        PoundsTab.setOnClickListener(this);
        AccountTab.setOnClickListener(this);*/
        openlist.setOnClickListener(this);
        init();
	}
	private void init() {
    	
    	load_selected_view(0);
    	
    	//items = new ArrayList<Object>();
    	//String [] strings = new String [] {"1", "2" };
    	//Integer [] intgr = new Integer[] {1, 2};
    	
    	
    	stringList = new ArrayList<GenrtArraylist>();
    	
    	if(Utilities.USER_LOGEDIN){
	    	for(int i=0;i<Utilities.LoggedInList.length;i++){
	    		GenrtArraylist gnrtarrylist = new GenrtArraylist();
	    		gnrtarrylist.name = Utilities.LoggedInList[i];
	    		gnrtarrylist.pic  = Utilities.LoggedInImgs[i];
	    		
	    		if(Utilities.LoggedInImgs[i]==-1){
	    			gnrtarrylist.isHeader  = true;
	    		}
	    		stringList.add(gnrtarrylist);
	    		
	    		gnrtarrylist = null;
	    	}
    	}else{
    		for(int i=0;i<Utilities.LoggedOutList.length;i++){
	    		GenrtArraylist gnrtarrylist = new GenrtArraylist();
	    		gnrtarrylist.name = Utilities.LoggedOutList[i];
	    		gnrtarrylist.pic  = Utilities.LoggedOutImgs[i];
	    		
	    		if(Utilities.LoggedOutImgs[i]==-1){
	    			gnrtarrylist.isHeader  = true;
	    		}
	    		stringList.add(gnrtarrylist);
	    		
	    		gnrtarrylist = null;
	    	}
    	}
    	inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.menu_list, null);
		
    	//mList = new ListView(this);
    	mList = (ListView)_view.findViewById(R.id.menu_list);
    	search = (EditText)_view.findViewById(R.id.search);
        mAdapter = new MenuAdapter(stringList);
        
        mList.setAdapter(mAdapter);
        
        //mList.setOnItemClickListener(mItemClickListener);

        //mMenuDrawer.setMenuView(mList);
        mMenuDrawer.setMenuView(_view);
	}
	
	public class GenrtArraylist{
		public Boolean isHeader = false;
		public String name="";
		public int pic;
	}
	public void load_selected_view(int btnSelectedPosition){
		tracking.setVisibility(View.GONE);
		if (btnSelectedPosition == 0) {
    		MainActivity.this.viewLayout.removeAllViews();
    		mViewFeatured = new ViewFeatured(MainActivity.this,MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewFeatured.getView());
    		//Utils.CurrentView = mViewFeatured.getView();
    		
    	}else if (btnSelectedPosition == 1) {
    		MainActivity.this.viewLayout.removeAllViews();
    		mViewStream = new ViewStream(MainActivity.this,MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewStream.getView());
    		
    		tracking.setVisibility(View.VISIBLE);
    		tracking.setOnClickListener(this);
    		//Utils.CurrentView = mViewHotCoupons.getView();
    	}else if (btnSelectedPosition == 2) {
    		MainActivity.this.viewLayout.removeAllViews();
    		mViewSearch = new ViewSearch(MainActivity.this,MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewSearch.getView());
    		
    		//Utils.CurrentView = mViewExpiringSoonActivity.getView();
    	}else if (btnSelectedPosition == 3) {
    		MainActivity.this.viewLayout.removeAllViews();
    		if(Utilities.USER_LOGEDIN){
	    		mViewPounds = new ViewPounds(MainActivity.this);
	    		MainActivity.this.viewLayout.addView(mViewPounds.getView());
    		}else{
	    		mViewAccountOffline = new ViewAccountOffline(MainActivity.this,btnSelectedPosition);
	    		MainActivity.this.viewLayout.addView(mViewAccountOffline.getView());
    		}
    		
    		//Utils.CurrentView = mViewHotCoupons.getView();
    	}else if (btnSelectedPosition == 4) {
    		MainActivity.this.viewLayout.removeAllViews();
    		if(Utilities.USER_LOGEDIN){
	    		mViewAccount = new ViewAccount(MainActivity.this);
	    		MainActivity.this.viewLayout.addView(mViewAccount.getView());
    		}else{
	    		mViewAccountOffline = new ViewAccountOffline(MainActivity.this,btnSelectedPosition);
	    		MainActivity.this.viewLayout.addView(mViewAccountOffline.getView());
    		}
    		
    		//Utils.CurrentView = mViewExpiringSoonActivity.getView();
    	}else if (btnSelectedPosition == 5) {
    		tracking.setVisibility(View.INVISIBLE);
			MainActivity.this.viewLayout.removeAllViews();
    		mViewTracking = new ViewTracking(MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewTracking.getView());
    		
    		//Utils.CurrentView = mViewExpiringSoonActivity.getView();
    	}else if (btnSelectedPosition == 6) {
    		MainActivity.this.viewLayout.removeAllViews();
			mViewChangePassword = new ViewChangePassword(MainActivity.this);
			MainActivity.this.viewLayout.addView(mViewChangePassword.getView());
    		
    		//Utils.CurrentView = mViewExpiringSoonActivity.getView();
    	}else if (btnSelectedPosition == 7) {
    		MainActivity.this.viewLayout.removeAllViews();
    		mViewAbout = new ViewAbout(MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewAbout.getView());
    		
    		//Utils.CurrentView = mViewExpiringSoonActivity.getView();
    	}/*else if (btnSelectedPosition == 8) {
    		MainActivity.this.viewLayout.removeAllViews();
    		mViewSearch = new ViewSearch(MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewSearch.getView());
    		
    		//Utils.CurrentView = mViewExpiringSoonActivity.getView();
    	}else if (btnSelectedPosition == 9) {
    		MainActivity.this.viewLayout.removeAllViews();
    		mViewSearch = new ViewSearch(MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewSearch.getView());
    		
    		//Utils.CurrentView = mViewExpiringSoonActivity.getView();
    	}*/
    	
	}
	/*private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //mActivePosition = position;
            mMenuDrawer.setActiveView(view, position);
            
            Log.i("entry", "entry"+mItems.get(position).isHeader);
            mAdapter.setSelectedPosition(position);
            
            if(!stringList.get(position).isHeader){
            
            switch (position) {
			case 0: // account
				load_selected_view(4); 
				break;
			case 1: // header feature
				load_selected_view(1);
				break;	
			case 2: //stream
				load_selected_view(1);
				break;	
			case 3: //adv search
				load_selected_view(2);
				break;	
			case 4: //news feed
				load_selected_view(0);
				break;
			case 5: //pound received
				load_selected_view(3);
				break;	
			case 6: //pound sent
				load_selected_view(3);
				break;	
			case 7: // tracking
				load_selected_view(5);
				break;	
			case 8: // header account
				load_selected_view(1);
				break;	
			case 9: // invite friends
					InviteFriend();
					//load_selected_view(2);
					break;	
			case 10: //change pass
					load_selected_view(6);
					break;	
			case 11: // about shnarped
				load_selected_view(7);
				break;	
			case 12: // log out
				Utilities.USER_LOGEDIN = false;
				load_selected_view(4);
				mAdapter.notifyDataSetChanged();
					break;	
			
			default:
				break;
			}
            mMenuDrawer.closeMenu();
        }
        }
    };
	*/
	private class MenuAdapter extends BaseAdapter {

        
    	private int selectedPos = 0;
    	private RelativeLayout layout_relative;
    	private ViewHolder viewholder;
        MenuAdapter(ArrayList<GenrtArraylist> stringList) {
            mItems = stringList;
        }
        
        public void setSelectedPosition(int pos){
			selectedPos = pos;
			// inform the view of this change
			notifyDataSetChanged();
		}
        
        
        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        /*@Override
        public int getItemViewType(int position) {
            return getItem(position) instanceof Item ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public boolean isEnabled(int position) {
            return getItem(position) instanceof Item;
        }*/

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = convertView;
            Object item = getItem(position);

            if (v == null) {
            	layout_relative = (RelativeLayout)getLayoutInflater().inflate(R.layout.menu_row_item, parent, false);
            	viewholder = new ViewHolder();
            	viewholder.menu_item_name = (TextView) layout_relative.findViewById(R.id.menu_item_name);
            	viewholder.menu_item_img  = (ImageButton) layout_relative.findViewById(R.id.menu_item_img);
            	layout_relative.setTag(viewholder);
            }else{
            	layout_relative = (RelativeLayout) convertView;
	        	viewholder 		= (ViewHolder) layout_relative.getTag();
            }

            
            viewholder.menu_item_name.setText(mItems.get(position).name);
            viewholder.menu_item_img.setImageResource(mItems.get(position).pic);
            
            /*LayoutParams params = tv.getLayoutParams();
            params.height = 70;
            tv.setLayoutParams(params);*/
            
            if(mItems.get(position).isHeader){
            	viewholder.menu_item_name.setBackgroundColor(Color.DKGRAY);
            	viewholder.menu_item_img.setVisibility(View.GONE);
            	//tv.setHeight(30);
            	
            }else{
            	viewholder.menu_item_name.setBackgroundColor(Color.BLACK);
            }
            //iv.setImageDrawable(mItems.get(position).pic);
            //tv.setCompoundDrawablesWithIntrinsicBounds(((Item) item).mIconRes, 0, 0, 0);

         // change the row color based on selected state
	        /*if(selectedPos == position){
	        	tv.setBackgroundColor(Color.parseColor("#282828"));
	        	tv.setTextColor(Color.parseColor("#ffffff"));
	        }else{
	        	tv.setBackgroundColor(Color.parseColor("#F7F7F7"));
	        	tv.setTextColor(Color.parseColor("#000000"));
	        }*/
            
            
            
            layout_relative.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
			            
			            if(!stringList.get(position).isHeader){
			            
			            switch (position) {
						case 0: // account
							load_selected_view(4); 
							break;
						case 1: // header feature
							load_selected_view(1);
							break;	
						case 2: //stream
							load_selected_view(1);
							break;	
						case 3: //adv search
							load_selected_view(2);
							break;	
						case 4: //news feed
							load_selected_view(0);
							break;
						case 5: //pound received
							load_selected_view(3);
							break;	
						case 6: //pound sent
							load_selected_view(3);
							break;	
						case 7: // tracking
							load_selected_view(5);
							break;	
						case 8: // header account
							load_selected_view(1);
							break;	
						case 9: // invite friends
								InviteFriend();
								//load_selected_view(2);
								break;	
						case 10: //change pass
								load_selected_view(6);
								break;	
						case 11: // about shnarped
							load_selected_view(7);
							break;	
						case 12: // log out
							Utilities.USER_LOGEDIN = false;
							load_selected_view(4);
							mAdapter.notifyDataSetChanged();
								break;	
						
						default:
							break;
						}
			            mMenuDrawer.closeMenu();
			        }
				}
			});

            /*if (position == mActivePosition) {
                mMenuDrawer.setActiveView(v, position);
            }*/

            return layout_relative;
        }
    }
	
	static class ViewHolder {
		int position;
		ImageButton menu_item_img;
		TextView menu_item_name;
		
	}
	public void InviteFriend(){
		
		final Dialog dialog = new Dialog(MainActivity.this, R.style.CustomTheme);
		dialog.setContentView(R.layout.invite_friend);
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

	@Override
	public void onClick(View v) {
		/*if (v.getId() == R.id.featuredTab) {
			tracking.setVisibility(View.INVISIBLE);
			load_selected_view(0);
		}else if (v.getId() == R.id.streamTab) {
			load_selected_view(1);
		}else if (v.getId() == R.id.searchTab) {
			tracking.setVisibility(View.INVISIBLE);
			load_selected_view(2);
		}else if (v.getId() == R.id.poundTab) {
			tracking.setVisibility(View.INVISIBLE);
			load_selected_view(3);
		}else if (v.getId() == R.id.accountTab) {
			tracking.setVisibility(View.INVISIBLE);
			load_selected_view(4);
		}else */if (v.getId() == R.id.tracking) {
			tracking.setVisibility(View.INVISIBLE);
			MainActivity.this.viewLayout.removeAllViews();
    		mViewTracking = new ViewTracking(MainActivity.this);
    		MainActivity.this.viewLayout.addView(mViewTracking.getView());
		}else if(v.getId()==R.id.openlist){
			mMenuDrawer.openMenu();
		}
	}

	

}
