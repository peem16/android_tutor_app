package com.example.peem16.samms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by May- on 10/30/2017.
 */

public class basketAdapter extends ArrayAdapter<HashMap<String, Object>> {
    private LayoutInflater mInflater;
    private HashMap<String, Object> map ;
    private int mView;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private static Context context;
    private static Context mContext;
    public home HOME;
    private Activity activity;
    private ProcessDelBasket delBasket;
    public basketAdapter(Context context, int ViewResourceId, ArrayList<HashMap<String, Object>> objects) {
        super(context, ViewResourceId, objects);
        objArrayList = objects;
        mInflater = LayoutInflater.from(context);
        mView = ViewResourceId;
        mContext = context;

    }
    private String keysv = ServerConnect.KEY_SERVER;

    private String jResult;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Initial.SharePref = new PreferenceClass(getContext());
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(mView, null);
            holder.namesys = (TextView)convertView.findViewById(R.id.name);
            holder.price = (TextView)convertView.findViewById(R.id.price);
            holder.ea = (TextView)convertView.findViewById(R.id.ea);
            holder.sum = (TextView)convertView.findViewById(R.id.sum);
            holder.del = (TextView)convertView.findViewById(R.id.del);

//            holder.position = position;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        map = objArrayList.get(position);

        holder.namesys.setText(map.get(FieldConstants.software_name).toString());

        holder.price.setText(map.get(FieldConstants.software_price).toString()+" Bath");

        holder.ea.setText("จำนวนระบบ "+map.get(FieldConstants.amount).toString());
        holder.sum.setText("รวม "+map.get(FieldConstants.pricesum).toString()+" Bath");


        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map = objArrayList.get(position);
                delBasket = new ProcessDelBasket();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    delBasket.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getID(),map.get(FieldConstants.id).toString());
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    delBasket.execute();
                }

            }
        });


        return convertView;
    }

    private class ProcessDelBasket extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("iduser",params[0]);
            ph.add("idbasket",params[1]);

            objArrayList = new ArrayList<HashMap<String,Object>>();


            Initial.httpConn = new ServerConnect(mContext);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.Delbasket, ph);



            ShowMe.log("RESULT="+jResult);



            return null;


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){

                    Toast.makeText(mContext, "Delete Success",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(mContext,Basket.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    mContext.startActivity(intent);


                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
