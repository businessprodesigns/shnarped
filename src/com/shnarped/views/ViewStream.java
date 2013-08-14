package com.shnarped.views;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.gson.Gson;
import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;
import com.shnarped.adapter.PlayerListAdapter;
import com.shnarped.database.DatabaseOperation;
import com.shnarped.pulltorefreshview.PullToRefreshListView;
import com.shnarped.pulltorefreshview.PullToRefreshListView.OnRefreshListener;
import com.shnarped.responses.EventResponse;
import com.shnarped.utils.Utilities;
import com.shnarped.web.WebTask;

public class ViewStream implements OnClickListener {
	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	Context _context;
	PullToRefreshListView streamList;
	ArrayList<EventResponse> eventresponselist;
	DatabaseOperation _databaseoperation;
	PlayerListAdapter playlistdadpter;
	public ViewStream(MainActivity a,Context context) {
		this.mActivity = a;
		_context = context;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_tracking, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("STREAM");
		streamList = (PullToRefreshListView)_view.findViewById(R.id.trackingList);
		eventresponselist = new ArrayList<EventResponse>();
		_databaseoperation = new DatabaseOperation(_context);
		
		streamTaskCall();
		streamList.setOnRefreshListener(new OnRefreshListener() {
		    @Override
		    public void onRefresh() {
		    	streamTaskCall();
		    	
		    }
		});
		
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
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			//super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			//super.onPostExecute(result);
			Log.i("Stream result: - ",""+result);
				
				try{
					EventResponse[] _eventresponse = new Gson().fromJson(result, EventResponse[].class);
					_databaseoperation.openDB();
					for (EventResponse evntrsponse : _eventresponse) {
						eventresponselist.add(evntrsponse);
						if(_databaseoperation.getToatlEventData(Integer.parseInt(evntrsponse.id))<=0){
							_databaseoperation.insertEventsData(evntrsponse);
						}
				    }
					_databaseoperation.closeDB();
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
				playlistdadpter = new PlayerListAdapter(_context,eventresponselist);
				streamList.setAdapter(playlistdadpter);
				streamList.onRefreshComplete();
		}
		
	} 
	
	public void streamTaskCall(){
		if(Utilities.isInternetOnline(_context)){ 
			 
			 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			 new StreamTask(_context, Utilities.BASE_URL + Utilities.MOBILEVERSION_URL + Utilities.STREAMLISTURL_URL+"?version=1.1").execute(postParameters);
		 }else{
			 // if internate is not available then get value from database;
			 _databaseoperation.openDB();
			 eventresponselist = _databaseoperation.setOfflineDataToList(eventresponselist,1);
			 _databaseoperation.closeDB();
			 playlistdadpter = new PlayerListAdapter(_context,eventresponselist);
			 streamList.setAdapter(playlistdadpter);
		 }
	}		
			 
		
}
