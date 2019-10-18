package com.example.peem16.samms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by May- on 10/30/2017.
 */

public class homeAdapter extends ArrayAdapter<HashMap<String, Object>> {
    private LayoutInflater mInflater;
    private HashMap<String, Object> map ;
    private int mView;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private static Context context;
    private static Context mContext;
    public home HOME;
    private Activity activity;
    public homeAdapter(Context context, int ViewResourceId, ArrayList<HashMap<String, Object>> objects) {
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

//            holder.position = position;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        map = objArrayList.get(position);

        holder.namesys.setText(map.get(FieldConstants.software_name).toString());

        holder.price.setText(map.get(FieldConstants.software_price).toString()+" Bath");


        return convertView;
    }



}
