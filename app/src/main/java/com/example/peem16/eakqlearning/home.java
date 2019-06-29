package com.example.peem16.eakqlearning;

/**
 * Created by May- on 8/24/2017.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;




public class home extends Fragment implements AdapterView.OnItemClickListener , View.OnClickListener  {
    private ArrayList<HashMap<String, Object>> objArrayList;
    public static final String TAG = "home";
    private ProcessCoures_all Showcoures;
    private ProgressBar progressBar;
    private  String keysv = ServerConnect.KEY_SERVER;
    private LinearLayout contentPanel;
    private String jResult,jResult2;
    private ListView listView;
    private static Context mContext;
    //ImageLoader imageLoader;
    private HashMap<String, Object> objMap;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private TextView btnConfirm,btnCancelPay;
    private Processnews news;
    private ImageButton btnchat;
    private  ProcessCouresnon Showcouresnon;
    private Dialog d;
    private String ids;
    private TextView btnReserve,btnBuy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();

             View view = inflater.inflate(R.layout.activity_home, container, false);







        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);

        MyPagerAdapter adapter2 = new MyPagerAdapter(getChildFragmentManager());

        pager.setAdapter(adapter2);


        Initial.SharePref = new PreferenceClass(getContext());
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar2);

        if(Initial.SharePref.getStringIDUserAccount() != ("")){

            Showcoures = new ProcessCoures_all();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                Showcoures.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                Showcoures.execute();
            }
        }else{

            Showcouresnon = new ProcessCouresnon();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                Showcouresnon.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                Showcouresnon.execute();
            }
        }




        listView = (ListView) view.findViewById(R.id.listview);
        View view2 = inflater.inflate(R.layout.activity_item_course, container, false);
        btnReserve = (TextView)view2.findViewById(R.id.btnReserve);
        btnReserve.setOnClickListener(this);
//

        return view;

    }

    private class Processnews extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("limit","3");


            objArrayList = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(getContext());
            jResult2 = Initial.httpConn.getDataFromServer(keysv+PHPPath.getnews, ph);



            ShowMe.log("RESULT="+jResult2);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult2);
                if(!jObject.getString("result").trim().equals("null")){
                    JSONArray jArray = new JSONArray(jObject.getString("result"));
                    int size = jArray.length();
                    for(int i=0; i<size ; i++){
                        JSONObject jsonObject = jArray.getJSONObject(i);


                        ShowMe.log("asdasdadasd"+jsonObject.getString(FieldConstants.pic).toString());
                        if(i==0){

                            Initial.SharePref.setnews1(jsonObject.getString(FieldConstants.pic).toString());

                        }else if(i==1){
                            Initial.SharePref.setnews2(jsonObject.getString(FieldConstants.pic).toString());

                        }else if(i==2){
                            Initial.SharePref.setnews3(jsonObject.getString(FieldConstants.pic));

                        }





                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
    private class ProcessCouresnon extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();


            objArrayList = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(getContext());

            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.Showlistcoures_all_non, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {
                objArrayList.clear();
                JSONObject jObject = new JSONObject(jResult);
                if(!jObject.getString("result").trim().equals("null")){
                    JSONArray jArray = new JSONArray(jObject.getString("result"));
                    int size = jArray.length();
                    for(int i=0; i<size ; i++){
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        objMap = new HashMap<String, Object>();

                        objMap.put(FieldConstants.idcourse, jsonObject.getString(FieldConstants.idcourse));
                        objMap.put(FieldConstants.name, jsonObject.getString(FieldConstants.name));
                        objMap.put(FieldConstants.course_detail, jsonObject.getString(FieldConstants.course_detail));
                        objMap.put(FieldConstants.idtype, jsonObject.getString(FieldConstants.idtype));




                        objArrayList.add(objMap);
//                        try {
//                            if(Integer.parseInt(jsonObject.getString(FieldConstants.book_count)) > 0) objArrayList.add(objMap);
//                        }
//                        catch(Exception e){
//                            objArrayList.add(objMap);
//                        }
                    }
                }
                int currentPosition = listView.getLastVisiblePosition();

                listView.setAdapter(null);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);
//                AppStatus.setPausing(false);
                mAdapter = new homeAdapter(getContext(), R.layout.activity_item_course, objArrayList);
                listView.setAdapter(mAdapter);

                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();
                listView.setSelection(currentPosition);

                Initial.SharePref.setodj(objArrayList);

            } catch (JSONException e) {
                e.printStackTrace();
            }






            news = new Processnews();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                news.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                news.execute();
            }



        }




    }



    private class ProcessCoures_all extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("IDUserAccount",Initial.SharePref.getStringIDUserAccount());


            objArrayList = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(getContext());

            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.Showlistcoures_all, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {
                objArrayList.clear();
                JSONObject jObject = new JSONObject(jResult);
                if(!jObject.getString("result").trim().equals("null")){
                    JSONArray jArray = new JSONArray(jObject.getString("result"));
                    int size = jArray.length();
                    for(int i=0; i<size ; i++){
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        objMap = new HashMap<String, Object>();

                        objMap.put(FieldConstants.idcourse, jsonObject.getString(FieldConstants.idcourse));
                        objMap.put(FieldConstants.name, jsonObject.getString(FieldConstants.name));
                        objMap.put(FieldConstants.course_detail, jsonObject.getString(FieldConstants.course_detail));
                        objMap.put(FieldConstants.idtype, jsonObject.getString(FieldConstants.idtype));


                        objMap.put(FieldConstants.IDUserAccount, jsonObject.getString(FieldConstants.IDUserAccount));
                        objMap.put(FieldConstants.idTesting, jsonObject.getString(FieldConstants.idTesting));
                        objMap.put(FieldConstants.status, jsonObject.getString(FieldConstants.status));

                        objMap.put(FieldConstants.random_key, jsonObject.getString(FieldConstants.random_key));

                        objMap.put(FieldConstants.idpay_reserve, jsonObject.getString(FieldConstants.pay));
                        objMap.put(FieldConstants.reserve_price, jsonObject.getString(FieldConstants.reserve_price));
                        objMap.put(FieldConstants.day, jsonObject.getString(FieldConstants.day));

                        objMap.put(FieldConstants.comment, jsonObject.getString(FieldConstants.comment));
                        objMap.put(FieldConstants.ask1, jsonObject.getString(FieldConstants.ask1));
                        objMap.put(FieldConstants.ask2, jsonObject.getString(FieldConstants.ask2));
                        objMap.put(FieldConstants.ask2, jsonObject.getString(FieldConstants.ask2));
                        objMap.put(FieldConstants.ask3, jsonObject.getString(FieldConstants.ask3));
                        objMap.put(FieldConstants.ask4, jsonObject.getString(FieldConstants.ask4));

                        objMap.put(FieldConstants.ask5, jsonObject.getString(FieldConstants.ask5));

                        objMap.put(FieldConstants.name, jsonObject.getString(FieldConstants.name));



                        objArrayList.add(objMap);
//                        try {
//                            if(Integer.parseInt(jsonObject.getString(FieldConstants.book_count)) > 0) objArrayList.add(objMap);
//                        }
//                        catch(Exception e){
//                            objArrayList.add(objMap);
//                        }
                    }
                }
                int currentPosition = listView.getLastVisiblePosition();

                listView.setAdapter(null);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);
//                AppStatus.setPausing(false);
                mAdapter = new homeAdapter(getContext(), R.layout.activity_item_course, objArrayList);
                listView.setAdapter(mAdapter);

                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();
                listView.setSelection(currentPosition);

                Initial.SharePref.setodj(objArrayList);

            } catch (JSONException e) {
                e.printStackTrace();
            }






            news = new Processnews();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                news.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                news.execute();
            }



        }




    }


    @Override
    public void onClick(View v) {


        if (v == btnReserve) {

            ShowMe.log("asd"+Initial.SharePref.getIDS().toString());



        }



    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        objArrayList = new ArrayList<HashMap<String,Object>>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                Toast.makeText(getContext(),"view"+position,Toast.LENGTH_SHORT).show();

            }
        });
    }

    
    @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        // TODO Auto-generated method stub
       objMap = (HashMap<String, Object>)listView.getItemAtPosition(position);
////        btnBuy = (TextView)view.findViewById(R.id.btnReserve);
//        long viewId = adapter.getId();
//
//        ShowMe.log("asdasd"+viewId);
//
//        if (viewId == R.id.btnReserve) {
//            ShowMe.log("asd"+objMap.get(FieldConstants.idcourse).toString());
//        } else if (viewId == R.id.btnBuy) {
//            ShowMe.log("asd"+objMap.get(FieldConstants.idcourse).toString());
//        }


//        Initial.SharePref.setIDS(objMap.get(FieldConstants.idcourse).toString());


//        TextView btnbuy = (TextView) view.findViewById(R.id.btnBuy);











    }





}
