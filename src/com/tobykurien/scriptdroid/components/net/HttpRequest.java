package com.tobykurien.scriptdroid.components.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.tobykurien.scriptdroid.components.BaseComponent;

public class HttpRequest extends BaseComponent {
   public static final String TAG = "HttpRequest";
   
   public interface OnHttpLoaded {
      public void onHttpLoaded(String data);
   }

   OnHttpLoaded callback = null;
   
   AsyncTask<Uri, Void, String> fetchTask = new AsyncTask<Uri, Void, String>() {
      @Override
      protected String doInBackground(Uri... params) {
         // getTweetsTest()
         String data = "";
         try {
            data = getData(params[0].toString());
         } catch (Exception e) {
            // what do?
            Log.e(TAG, "error", e);
         }

         return data;
      }

      @Override
      protected void onPostExecute(String data) {
         // execute javascript code here
         Log.d(TAG, "Got back: " + data);
         if (callback != null) callback.onHttpLoaded(data);
      }
   };

   public HttpRequest(Context context) {
      super(context);
   }
   
   public void fetch(String uriString, OnHttpLoaded callback) {
      Log.d(TAG, "callback: " + callback.toString());
      this.callback = callback;
      fetchTask.execute(Uri.parse(uriString));
   }
   
   /**
    * Get data from the internet
    * 
    * @param url
    * @return
    * @throws IOException
    */
   private String getData(String url) throws IOException {
      URL u = new URL(url);
      HttpURLConnection c = (HttpURLConnection) u.openConnection();
      c.connect();
      if (c.getResponseCode() == HttpURLConnection.HTTP_OK) {
         InputStream is = c.getInputStream();
         byte[] buf = new byte[1024];
         int len;
         ByteArrayOutputStream os = new ByteArrayOutputStream();
         while ((len = is.read(buf)) > 0) {
            os.write(buf, 0, len);
         }
         is.close();
         return os.toString();
      }

      return null;
   }

}
