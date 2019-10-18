package com.example.peem16.samms;

/**
 * Created by May- on 9/12/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class ServerConnect {

    public static String KEY_SERVER = "http://192.168.1.34/";
    public static Context context;
    public static String getServerKey(){


        return "http://192.168.1.34/";

    }


    public ServerConnect(Context context_){
        context = context_;
        KEY_SERVER = getServerKey();

    }

    private String doHttp(String url, ArrayList<NameValuePair> nameValuePairs_, boolean reCheck){
        String result = null;
        InputStream is = null;
        ShowMe.log("url="+url);
        if(!url.contains("/")){
            if(!KEY_SERVER.substring(KEY_SERVER.length()-1).equals("/")){//lastchar
                KEY_SERVER+="/";
            }
            url = KEY_SERVER+url;

        }
        try{
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
            HttpConnectionParams.setSoTimeout(httpParameters, 20000);
            HttpClient httpclient = new DefaultHttpClient(httpParameters);
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs_,"UTF-8"));
            String responseBody = EntityUtils.toString(httppost.getEntity());
            Log.i("peem", "httppost :"+httppost.getURI()+"?"+responseBody);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch(Exception e){
            e.printStackTrace();
            ShowMe.log("Error converting result [1]" + e.toString());
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-11"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            ShowMe.log("Error converting result [2]" + e.toString());
        }
        //nameValuePairs.clear();

        ShowMe.log("http:result="+result);
        ShowMe.log("http:url="+url);
        if( !reCheck && (result == null || result.equals(""))){
            return getDataFromServer(url,nameValuePairs_,true);
        }else{
            return result;
        }
    }

    public String getDataFromServer(String url, ArrayList<NameValuePair> nameValuePairs_, boolean reCheck){
        //Log.i(AppStatus.DEBUGTAG, "start get data..."+url);
        return doHttp(url,nameValuePairs_,reCheck);
    }
    public String getDataFromServer(String url, ArrayList<NameValuePair> nameValuePairs_){
        //Log.i(AppStatus.DEBUGTAG, "start get data..."+url);
        return doHttp(url,nameValuePairs_,false);
    }

    public Bitmap fetchImage(String imageUrl )
    {
        Bitmap bitmap;
        try
        {
            URL url = new URL( imageUrl.trim() );
            InputStream input = null;
            URLConnection conn = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection)conn;
            httpConn.setRequestMethod("GET");
            httpConn.setReadTimeout(40000);
            httpConn.connect();

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                input = httpConn.getInputStream();
            }

	        /*BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
	        bitmap = BitmapFactory.decodeStream(input,null,options);*/
            bitmap = BitmapFactory.decodeStream(input);
            input.close();
            httpConn.disconnect();
            return bitmap;
        }
        catch ( MalformedURLException e ){
            Log.d("fetchImage",
                    "MalformedURLException invalid URL: " + imageUrl );
        }catch ( IOException e ){
            Log.d("fetchImage","IO exception: " + e);
        }catch(Exception e){
            Log.d("fetchImage","Exception: " + e);
        }

        return null;
    }
}

