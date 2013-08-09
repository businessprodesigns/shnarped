package com.shnarped.views;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.shnarped.activites.R;
import com.shnarped.activity.MainActivity;
import com.shnarped.adapter.PlayerListAdapter;
import com.shnarped.responses.EventResponse;
import com.shnarped.utils.Utilities;
import com.shnarped.web.WebTask;

public class ViewFeatured {

	MainActivity mActivity;
	private LayoutInflater inflater = null;
	View _view;
	Context _context;
	ListView featuresList;
	ArrayList<EventResponse> eventresponselist;
	public ViewFeatured(MainActivity a,Context context) {
		this.mActivity = a;
		_context = context;
		inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_view = inflater.inflate(R.layout.view_featured, null);
		init(_view);
	}
	private void init(View _view2) {
		mActivity.headertitleTxt.setText("FEATURED");
		featuresList = (ListView)_view.findViewById(R.id.featuresList);
		eventresponselist = new ArrayList<EventResponse>();
		if(Utilities.isInternetOnline(_context)){ 
			 
			 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			 /*try {
				 postParameters.add(new BasicNameValuePair("version","1.1"));
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			 new FeaturedTask(_context, Utilities.BASE_URL + Utilities.MOBILEVERSION_URL + Utilities.EVENTLISTURL_URL+"?version=1.1&type=news&showcase=true").execute(postParameters);
		 }
		
	}
	public View getView() {
		return _view;
	}
	


class FeaturedTask extends WebTask {

	public FeaturedTask(Context context, String activityURL) {
		super(context, activityURL);
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.i("Featured result: - ",""+result);
			
			try{
				EventResponse[] _eventresponse = new Gson().fromJson(result, EventResponse[].class);
				
				for (EventResponse evntrsponse : _eventresponse) {
					eventresponselist.add(evntrsponse);
			    }
				
				PlayerListAdapter playlistdadpter = new PlayerListAdapter(_context,eventresponselist);
				featuresList.setAdapter(playlistdadpter);
				
			}catch(Exception e1){
				e1.printStackTrace();
			}
	}
	
} 
	
}
