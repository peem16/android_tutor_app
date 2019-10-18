package com.example.peem16.samms;

/**
 * Created by May- on 8/24/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class home extends Fragment   implements AdapterView.OnItemClickListener {
    private ArrayList<HashMap<String, Object>> objArrayList;
    public static final String TAG = "home";
    private ProgressBar progressBar;
    private String keysv = ServerConnect.KEY_SERVER;
    private LinearLayout contentPanel;
    private String jResult,jResult2;
    private ListView listView;
    private static Context mContext;
    //ImageLoader imageLoader;
    private HashMap<String, Object> objMap;
    private ArrayAdapter<HashMap<String, Object>> mAdapter;
    private ProcessgetProduct getProduct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();

             View view = inflater.inflate(R.layout.activity_home, container, false);




        listView = (ListView) view.findViewById(R.id.listview);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        getProduct = new ProcessgetProduct();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            getProduct.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            getProduct.execute();
        }

        return view;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        objArrayList = new ArrayList<HashMap<String, Object>>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                objMap = (HashMap<String, Object>)listView.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putString(BuyDetail.PARAM_TAG,"BuyDetail");
                bundle.putSerializable(BuyDetail.PARAM_MAP,objMap);

                Intent intent = new Intent(mContext, BuyDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bundle);
                startActivity(intent);

//                Toast.makeText(getContext(),"view"+position,Toast.LENGTH_SHORT).show();

            }
        });
    }


    private class ProcessgetProduct extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();


            objArrayList = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(getContext());

            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.GetProduct, ph);



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

                        objMap.put(FieldConstants.id, jsonObject.getString(FieldConstants.id));
                        objMap.put(FieldConstants.software_name, jsonObject.getString(FieldConstants.software_name));
                        objMap.put(FieldConstants.software_detail, jsonObject.getString(FieldConstants.software_detail));
                        objMap.put(FieldConstants.is_hardware_set, jsonObject.getString(FieldConstants.is_hardware_set));
                        objMap.put(FieldConstants.software_price, jsonObject.getString(FieldConstants.software_price));


                        objArrayList.add(objMap);

                    }
                }
                int currentPosition = listView.getLastVisiblePosition();

                listView.setAdapter(null);
                progressBar.setVisibility(View.INVISIBLE);
////                AppStatus.setPausing(false);
                mAdapter = new homeAdapter(getContext(), R.layout.listbuy, objArrayList);
                listView.setAdapter(mAdapter);
//
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();
                listView.setSelection(currentPosition);
//
//                Initial.SharePref.setodj(objArrayList);

            } catch (JSONException e) {
                e.printStackTrace();
            }





        }




    }


    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        // TODO Auto-generated method stub
//        objMap = (HashMap<String, Object>)listView.getItemAtPosition(position);
//
//        Bundle bundle = new Bundle();
//        bundle.putString(BuyDetail.PARAM_TAG,"BuyDetail");
//        bundle.putSerializable(BuyDetail.PARAM_MAP,objMap);
//
//        Intent intent = new Intent(mContext, BuyDetail.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtras(bundle);
//        startActivity(intent);


    }

}
