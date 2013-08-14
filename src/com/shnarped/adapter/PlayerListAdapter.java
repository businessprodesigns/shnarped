package com.shnarped.adapter;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shnarped.activites.R;
import com.shnarped.helper.ImageLoader;
import com.shnarped.responses.EventResponse;
import com.shnarped.utils.Utilities;

public class PlayerListAdapter extends BaseAdapter {
		private LayoutInflater inflater = null;
		Context _context;
		ArrayList<EventResponse> eventresponselist;
		int defaultItemBackground;
	    private RelativeLayout layout_relative;
	    private ViewHolder viewholder;
	    public ImageLoader imageLoader; 
	
		public PlayerListAdapter(Context context,ArrayList<EventResponse> _eventresponselist) {
			_context = context;
			eventresponselist = _eventresponselist;
			imageLoader = new ImageLoader(_context.getApplicationContext());
		}
	
		
        public int getCount() {
        	return eventresponselist.size();
        }
        
        public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}
        
		@SuppressWarnings("deprecation")
		public View getView(final int position, View convertView, ViewGroup parent) {
			
			
			if (convertView == null) {
			 LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	         layout_relative = (RelativeLayout) inflator.inflate(R.layout.adpt_view_featured, null);
	         viewholder = new ViewHolder();
	         
	         viewholder.iv_playerimg		= (ImageView) layout_relative.findViewById(R.id.iv_playerimg);
	         viewholder.tv_playername		= (TextView)  layout_relative.findViewById(R.id.tv_playername);
	         viewholder.tv_playerhmtwn		= (TextView)  layout_relative.findViewById(R.id.tv_playerhmtwn);
	         viewholder.tv_playeradddate	= (TextView)  layout_relative.findViewById(R.id.tv_playeradddate);
	         viewholder.tv_title			= (TextView)  layout_relative.findViewById(R.id.tv_title);
	         viewholder.tv_body				= (TextView)  layout_relative.findViewById(R.id.tv_body);
	         viewholder.tv_punds			= (TextView)  layout_relative.findViewById(R.id.tv_punds);
	         viewholder.ll_playerimglinks   = (LinearLayout)  layout_relative.findViewById(R.id.ll_playerimglinks);
	         
	         viewholder.tv_playername.setTypeface(new Utilities.FontsClass(_context).getEurostiFonts());
	         
	         
	         layout_relative.setTag(viewholder);
			}else {
	        	layout_relative = (RelativeLayout) convertView;
	        	viewholder 		= (ViewHolder) layout_relative.getTag();
	        }
			
			try{
				imageLoader.DisplayImage(eventresponselist.get(position).recipients[0].avatar, viewholder.iv_playerimg);
				viewholder.tv_playername.setText(Html.fromHtml("<U>"+eventresponselist.get(position).recipients[0].first_name+" "+eventresponselist.get(position).recipients[0].last_name+"</u>"));
			}catch(Exception e){
				
			}
			
			try{
				viewholder.tv_playerhmtwn.setText(eventresponselist.get(position).recipients[0].player.hometown);
			}catch(Exception e){
				
			}
			try{
				String day = "";
				
				int datecount = Utilities.DateDifference(eventresponselist.get(position).updated_at);
				
				if(datecount==0){
					day = "Today";
				}
				if(datecount==-1){
					day = "Yesterday";
				}
				
				Date date_update = Utilities.DateFormat(eventresponselist.get(position).updated_at);
				
				if(day.length()>0){
					viewholder.tv_playeradddate.setText(day+" at "+date_update.getHours()+":"+date_update.getMinutes());
				}else{
					viewholder.tv_playeradddate.setText(new DateFormatSymbols().getMonths()[date_update.getMonth()-1]+" "+date_update.getDay()+" at "+date_update.getHours()+":"+date_update.getMinutes());
				}
				
			}catch(Exception e){
				
			}
			
			try{
				if(!eventresponselist.get(position).title.contentEquals("null")){
					viewholder.tv_title.setText(eventresponselist.get(position).title);
				}else{
					viewholder.tv_title.setVisibility(View.GONE);
				}
			}catch(Exception e){
				viewholder.tv_title.setVisibility(View.GONE);
			}
			
			
			try{
				if(!eventresponselist.get(position).body.contentEquals("null")){
					viewholder.tv_body.setText(eventresponselist.get(position).body);
				}else{
					viewholder.tv_body.setVisibility(View.GONE);
				}
			}catch(Exception e){
				viewholder.tv_body.setVisibility(View.GONE);
			}
			
			
			try{
				viewholder.tv_punds.setText(eventresponselist.get(position).recipients[0].pound_count.total+" pounds");
			}catch(Exception e){
				
			}
			
			
			viewholder.ll_playerimglinks.removeAllViews();
			
			try{
			EventResponse.LinksClass[] linkresponse = eventresponselist.get(position).links;
			Log.i("pos", linkresponse.length+"");
			for (EventResponse.LinksClass _linkresponse : linkresponse) {
				 LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		         RelativeLayout layout_relative_link = (RelativeLayout) inflator.inflate(R.layout.adpt_view_link, null);
		         ImageView iv_linkimage		= (ImageView) layout_relative_link.findViewById(R.id.iv_linkimage);
		         ImageView iv_playvideoicon		= (ImageView) layout_relative_link.findViewById(R.id.iv_playvideoicon);
		         Button ll_linkname			= (Button)    layout_relative_link.findViewById(R.id.ll_linkname);
		         WebView wv_videoembeded    = (WebView)   layout_relative_link.findViewById(R.id.wv_videoembeded);
		         wv_videoembeded.getSettings().setJavaScriptEnabled(true);
        		 wv_videoembeded.getSettings().setPluginsEnabled(true);
		         
	        	 if(!_linkresponse.image_thumb_url.contentEquals("null")){
		        	 imageLoader.DisplayImage(_linkresponse.image_url, iv_linkimage); 
		        	 if(_linkresponse.link_type.contentEquals("video")){
		        		 iv_playvideoicon.setVisibility(View.VISIBLE);
		        	 }else{
		        		 iv_playvideoicon.setVisibility(View.GONE);
		        	 }
		        	 
		         }else{
		        	 iv_linkimage.setVisibility(View.GONE);
		         }
		         
	        	 if(!_linkresponse.link_name.contentEquals("null")){
	        		 ll_linkname.setText(Html.fromHtml("   "+"<U>"+_linkresponse.link_name+"</u>"));
	        		/* if(_linkresponse.link_type.contentEquals("video")){
	        			 Drawable img = _context.getResources().getDrawable( R.drawable.videoicon);
	        			 ll_linkname.setCompoundDrawables( img, null, null, null );
		        	 }*/
	        	 }else{
	        		 ll_linkname.setVisibility(View.GONE);
	        	 }
        		 
		         if(!_linkresponse.embed.contentEquals("null")){
		        	 //iv_linkimage.setVisibility(View.GONE);
		        	 //ll_linkname.setVisibility(View.GONE);
		        	 try{
		                 try {
		                	 wv_videoembeded.loadData(
		                             "<html><body>"+_linkresponse.embed+"</body></html>",
		                             "text/html", "utf-8");
		                 } catch (Exception e) {
		                     // TODO: handle exception
		                     e.printStackTrace();
		                 }
		        		 
			         }catch(Exception e){
			        	 e.printStackTrace();
			        	 wv_videoembeded.setVisibility(View.GONE);
			         }
		        	 
		         }else{
		        	
	        		 wv_videoembeded.setVisibility(View.GONE);
		        	 
		         }
		         
		         viewholder.ll_playerimglinks.addView(layout_relative_link);
		    }
			}catch(Exception e){
				//e.printStackTrace();
				viewholder.ll_playerimglinks.setVisibility(View.GONE);
			}
			
            return layout_relative;
        }
		
		
		static class ViewHolder {
			int position;
			ImageView iv_playerimg;
			TextView tv_playername,tv_playerhmtwn,tv_playeradddate,tv_title,tv_body,tv_punds;
			LinearLayout ll_playerimglinks;
			
		}
    }




