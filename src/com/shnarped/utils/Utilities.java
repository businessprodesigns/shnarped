package com.shnarped.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.text.Html;
import android.text.format.Formatter;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.shnarped.activites.R;

public class Utilities {
	
	public static String ACCESS_CODE = "1234";
	public static boolean USER_LOGEDIN = false;
	
	public static String [] LoggedInList = new String [] {"User Name\nFavourite Team","Featured","Stream","Advanced Search","News Feed","Pound Received","Pound Sent","Tracking List","Account","Invite Friends","Change Password","About Shnarped","Log Out"};
	public static Integer [] LoggedInImgs = new Integer[] {R.drawable.profile,-1,R.drawable.stream,R.drawable.search,R.drawable.featured,R.drawable.pound_fist,R.drawable.pound_fist,R.drawable.plus_icon,-1,R.drawable.profile,R.drawable.profile,R.drawable.ic_dialog_info,R.drawable.profile};
	
	public static String [] LoggedOutList = new String [] {"Me","Featured","Stream","Advanced Search","News Feed","Pounds"};
	public static Integer [] LoggedOutImgs = new Integer[] {R.drawable.stream,-1,R.drawable.stream,R.drawable.search,R.drawable.search,R.drawable.search};
	
	public static boolean TWITTER_REDIRECT_TELL_A_FRND_HOME =false;
	public static boolean TWITTER_REDIRECT_TELL_A_FRND_ACC =false;
	
	public static String TWITTER_CONSUMER_KEY = "MJBPorVfflajkOVGl8wiQ"; // place your cosumer key here
	public static String TWITTER_CONSUMER_SECRET = "Y0UatA2PJB4YdvJ8QhjmAsjj4eZkfVjWEwr1Ed2UCYY"; // place your consumer secret here
	public static final String TWITTER_CALLBACK_URL = "oauth://t4jsample";
	public static final String TWITTER_CALLBACK_URL_HOME = "oauth://t4jsample1";
	
	public static final String TWITTER_PREF_KEY_OAUTH_TOKEN = "oauth_token";
	public static final String TWITTER_PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
	public static final String TWITTER_PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";
	public static String TWITTER_PREFERENCE_NAME = "twitter_oauth";

	
	
	
	
	
	// webservice link 
	public static String BASE_URL 			= "http://staging.shnarped.com/";
	public static String MOBILEVERSION_URL 	= "mobile/v1/";
	public static String EVENTLISTURL_URL 	= "events.json";
	public static String STREAMLISTURL_URL 	= "stream.json";
	
	
	
	//GET Fonts
	
	
	public static class FontsClass{
		static Context _context;
		
		public  FontsClass(Context context){
			_context = context;
		}
		
		public static Typeface getCollegeFonts(){
			return Typeface.createFromAsset(_context.getAssets(),"fonts/COLLEGE.ttf");
		}
		public static Typeface getCollegeEBFonts(){
			return Typeface.createFromAsset(_context.getAssets(),"fonts/COLLEGEB.ttf");
		}
		public static Typeface getCollegeECFonts(){
			return Typeface.createFromAsset(_context.getAssets(),"fonts/COLLEGEC.ttf");
		}
		public static Typeface getCollegeESFonts(){
			return Typeface.createFromAsset(_context.getAssets(),"fonts/COLLEGES.ttf");
		}
		public static Typeface getEurostiFonts(){
			return Typeface.createFromAsset(_context.getAssets(),"fonts/Eurosti.ttf");
		}
		public static Typeface getEurostileBoldFonts(){
			return Typeface.createFromAsset(_context.getAssets(),"fonts/EurostileBold.ttf");
		}
		public static Typeface getForqueFonts(){
			return Typeface.createFromAsset(_context.getAssets(),"fonts/Forque.ttf");
		}
		public static Typeface getFranchiseBoldhintedFonts(){
			return Typeface.createFromAsset(_context.getAssets(),"fonts/Franchise-Bold-hinted.ttf");
		}
		
	}
	
	
	
	public int calculateInSampleSize( BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	
	        // Calculate ratios of height and width to requested height and width
	        final int heightRatio = Math.round((float) height / (float) reqHeight);
	        final int widthRatio = Math.round((float) width / (float) reqWidth);
	
	        // Choose the smallest ratio as inSampleSize value, this will guarantee
	        // a final image with both dimensions larger than or equal to the
	        // requested height and width.
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	    }
	    return inSampleSize;
	}
	
	
	public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}
	
	public Bitmap decodeSampledBitmapFromUrl(Resources res, String url,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    //BitmapFactory.decodeResource(res, resId, options);
	    
	    try {
			BitmapFactory.decodeStream((InputStream)new URL(url).getContent(), null, options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    //return BitmapFactory.decodeResource(res, resId, options);
	    try {
	    	return BitmapFactory.decodeStream((InputStream)new URL(url).getContent(), null, options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Bitmap loadBitmap(String url)
	{
	    Bitmap bm = null;
	    InputStream is = null;
	    BufferedInputStream bis = null;
	    try 
	    {
	        URLConnection conn = new URL(url).openConnection();
	        conn.connect();
	        is = conn.getInputStream();
	        bis = new BufferedInputStream(is, 8192);
	        bm = BitmapFactory.decodeStream(bis);
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    finally {
	        if (bis != null) 
	        {
	            try 
	            {
	                bis.close();
	            }
	            catch (IOException e) 
	            {
	                e.printStackTrace();
	            }
	        }
	        if (is != null) 
	        {
	            try 
	            {
	                is.close();
	            }
	            catch (IOException e) 
	            {
	                e.printStackTrace();
	            }
	        }
	    }
	    return bm;
	}
	// --------------------------------check if internet is connected
	public static boolean isInternetOnline(Object _context) {
		Activity context = (Activity) _context;
		

		boolean connected = false;
		try {
			ConnectivityManager conMan = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = conMan.getActiveNetworkInfo();
			// Log.e("conMan",""+conMan);
			// Log.e("networkInfo",""+networkInfo);
			if (networkInfo != null) {
				if (networkInfo.isConnected()) {
					connected = true;
				} else {
					connected = false;
					alertMessage(context, "Network not available.");
				}
			} else {
				connected = false;
				alertMessage(context, "Network not available.");
			}
		} catch (Exception ex) {
			System.out.print(ex);
			 Log.e("Exception",""+ex);
			alertMessage(context, "Network not available");
		}
		// Log.e("connected-is",""+connected);
		return connected;

	}

	public static void alertMessage(Context context, String msg) {
		Log.e("alertMessage", "alertMessage");
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("TimeScapeLive Alert!");
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// do things.
						// Log.e("alertMessage","333333");
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
		// Log.e("alertMessage","222222");
	}
	
	public void showVirtualKeyboard(Context context) {
	    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
	}
	
	public void hideVirtualKeyboard(Activity activity) {
		 
		try {
			//InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			  //imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
			  InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);                                        
			  Log.e("inputManager",""+inputManager);
			  if (inputManager != null) { 
				  inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
				  Log.e("inputManager.hideSoftInputFromWindow",""+inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS));
			  } 
		} catch (Exception e) {
			// TODO: handle exception
		}
		   
	 }
	//get available internal storage space
	public String availableInternalStorageSpace( Context c){

        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(c, availableBlocks * blockSize);
        
	}
	public static void alartDialog(Context context,String title,String message){
		  
		  
		  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
		    context);
		 
		   // set title
		   alertDialogBuilder.setTitle(title);
		 
		   // set dialog message
		   alertDialogBuilder
		    .setMessage(message)
		    .setCancelable(false)
		    .setNeutralButton("OK",new DialogInterface.OnClickListener() {
		     public void onClick(DialogInterface dialog,int id) {
		      // if this button is clicked, just close
		      // the dialog box and do nothing
		      dialog.cancel();
		     }
		    });
		 
		    // create alert dialog
		    AlertDialog alertDialog = alertDialogBuilder.create();
		 
		    // show it
		    alertDialog.show();
		  
		 }
	public void sendMail(Activity mActivity) {
		
		String body1 = "";//getString(R.string.feedbackBody) + "<br>";
		
		/*body1 += "Version Code: " + versionCode  + "<br>";
		body1 += "Version Name: " + versionName + "<br>";
		body1 += "Android Device: " + Devicemodel;*/
	  //Toast.makeText(getApplicationContext(), "path: "+ path , Toast.LENGTH_LONG).show();
	  Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	  //  emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailSignature);
	     emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {""});
	     emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
	     emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body1)); 
	     //emailIntent.setType("image/jpeg");
	     emailIntent.setType("message/rfc822");
	     /*final PackageManager pm = mActivity.getPackageManager();
	     final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
	     ResolveInfo best = null;
	     for(final ResolveInfo info : matches)
	         if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
	             best = info;
	     if (best != null)
	         emailIntent.setClassName(best.activityInfo.packageName, best.activityInfo.name);*/
	     mActivity.startActivity(emailIntent);
	 }
	public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size=1024;
        try {
        	byte[] bytes=new byte[buffer_size];
            for(;;) {
            	int count=is.read(bytes, 0, buffer_size);
            	if(count == -1) break;
            	os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
	
	public static Date DateFormat(String date){
		SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");  
		try {  
		    Date dateformat = format.parse(date);  
		    return dateformat; 
		} catch (Exception e) {  
		    // TODO Auto-generated catch block  
		    e.printStackTrace(); 
		    return null;
		}
		
		
	}
	
	public static int DateDifference(String date){
		SimpleDateFormat dfDate  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    java.util.Date d = null;
	    java.util.Date d1 = null;
	    Calendar cal = Calendar.getInstance();
	    try {
	            d = dfDate.parse(date);
	            d1 = dfDate.parse(dfDate.format(cal.getTime()));//Returns 15/10/2012
	        } catch (java.text.ParseException e) {
	            e.printStackTrace();
	        }

	    int diffInDays = (int) ((d.getTime() - d1.getTime())/ (1000 * 60 * 60 * 24));
	    
	    return diffInDays;
	}
	
}
