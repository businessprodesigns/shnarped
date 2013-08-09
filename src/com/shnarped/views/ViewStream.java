package com.shnarped.views;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;
import com.shnarped.adapter.PlayerListAdapter;
import com.shnarped.responses.EventResponse;
import com.shnarped.utils.Utilities;
import com.shnarped.views.ViewFeatured.FeaturedTask;
import com.shnarped.web.WebTask;

public class ViewStream implements OnClickListener {
	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	Context _context;
	ListView streamList;
	ArrayList<EventResponse> eventresponselist;
	public ViewStream(MainActivity a,Context context) {
		this.mActivity = a;
		_context = context;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_tracking, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("STREAM");
		streamList = (ListView)_view.findViewById(R.id.trackingList);
		eventresponselist = new ArrayList<EventResponse>();
		if(Utilities.isInternetOnline(_context)){ 
			 
			 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			 /*try {
				 postParameters.add(new BasicNameValuePair("version","1.1"));
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			 new StreamTask(_context, Utilities.BASE_URL + Utilities.MOBILEVERSION_URL + Utilities.EVENTLISTURL_URL+"?version=1.1&type=news&showcase=false").execute(postParameters);
		 }
	}
	public View getView() {
		return _view;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	class StreamTask extends WebTask {

		public StreamTask(Context context, String activityURL) {
			super(context, activityURL);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Log.i("Stream result: - ",""+result);
				
				try{
					EventResponse[] _eventresponse = new Gson().fromJson(result, EventResponse[].class);
					
					for (EventResponse evntrsponse : _eventresponse) {
						eventresponselist.add(evntrsponse);
				    }
					
					PlayerListAdapter playlistdadpter = new PlayerListAdapter(_context,eventresponselist);
					streamList.setAdapter(playlistdadpter);
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
		}
		
	} 
}
