package com.shnarped.web;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class WebTask extends AsyncTask<ArrayList<NameValuePair>, Void, String> {
	
	Context _context;
	String _activityURL;
	private ProgressDialog _dialog;
	
	public WebTask(Context context, String activityURL) {
		_context = context;
		_activityURL = activityURL;
	}
	
	@Override
	protected void onPreExecute() {
		_dialog = ProgressDialog.show(_context, "", "Loading...", true);
	}

    @Override
    protected String doInBackground(ArrayList<NameValuePair>... uri) {
        try {
        	HttpClient client = new DefaultHttpClient();
        	HttpGet request = new HttpGet(_activityURL);
            //UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(uri[0]);
            //request.setEntity(formEntity);
            HttpResponse response = client.execute(request);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            return result;
        } catch (ClientProtocolException e) {
            Log.i("ClientProtocolException", e+"");
        } catch (IOException e) {
        	Log.i("IOException", e+"");
        }
		return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        _dialog.dismiss();
    }
}