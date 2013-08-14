package com.shnarped.views;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

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

public class ViewFeatured {

	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	Context _context;
	PullToRefreshListView featuresList;
	ArrayList<EventResponse> eventresponselist;
	DatabaseOperation _databaseoperation;
	PlayerListAdapter playlistdadpter;
	public ViewFeatured(MainActivity a,Context context) {
		this.mActivity = a;
		_context = context;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_featured, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("FEATURED");
		featuresList = (PullToRefreshListView)_view.findViewById(R.id.featuresList);
		eventresponselist = new ArrayList<EventResponse>();
		_databaseoperation = new DatabaseOperation(_context);
		
		featureTaskCall();
		featuresList.setOnRefreshListener(new OnRefreshListener() {
		    @Override
		    public void onRefresh() {
		    	featureTaskCall();
		    	
		    }
		});
		
	}
	public View getView() {
		return _view;
	}
	


class FeaturedTask extends WebTask {

	public FeaturedTask(Context context, String activityURL) {
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
		Log.i("Featured result: - ",""+result);
			
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
			featuresList.setAdapter(playlistdadpter);
			featuresList.onRefreshComplete();
	}
	
} 
	
public void featureTaskCall(){
	if(Utilities.isInternetOnline(_context)){ 
		 
		 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		 new FeaturedTask(_context, Utilities.BASE_URL + Utilities.MOBILEVERSION_URL + Utilities.EVENTLISTURL_URL+"?version=1.1&type=news&showcase=true").execute(postParameters);
	 }else{
		 
		 // if internate is not available then get value from database;
		 _databaseoperation.openDB();
		 eventresponselist = _databaseoperation.setOfflineDataToList(eventresponselist,0);
		 _databaseoperation.closeDB();
		 playlistdadpter = new PlayerListAdapter(_context,eventresponselist);
		 featuresList.setAdapter(playlistdadpter);
		 
	 }
}

}
