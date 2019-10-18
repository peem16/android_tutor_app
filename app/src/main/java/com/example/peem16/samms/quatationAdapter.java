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

public class quatationAdapter extends ArrayAdapter<HashMap<String, Object>> {
    private LayoutInflater mInflater;
    private HashMap<String, Object> map ;
    private int mView;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private static Context context;
    private static Context mContext;
    public home HOME;
    private Activity activity;
    public quatationAdapter(Context context, int ViewResourceId, ArrayList<HashMap<String, Object>> objects) {
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
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.date = (TextView)convertView.findViewById(R.id.date);
            holder.status = (TextView)convertView.findViewById(R.id.status);


//            holder.position = position;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        map = objArrayList.get(position);

        holder.name.setText(map.get(FieldConstants.quotation_no).toString());
        holder.date.setText("เมื่อวันที่ "+map.get(FieldConstants.quotation_date_start).toString());
        holder.status.setText("สถานะ  "+map.get(FieldConstants.lookup_name).toString());


        return convertView;
    }



}
