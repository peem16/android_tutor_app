package com.example.peem16.eakqlearning;

import android.content.Context;
import android.view.Gravity;
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

public class MessgeAdapter extends ArrayAdapter<HashMap<String,Object>>{
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String,Object>> mArray;
    private HashMap<String,Object> map;
    private int iView;
    private static Context context;
    public MessgeAdapter(Context c, int layoutView, ArrayList<HashMap<String, Object>> objects) {
        super(c, layoutView, objects);
        mInflater = LayoutInflater.from(c);
        mArray = objects;
        iView = layoutView;
        context = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(iView, null);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.menuTitle);
            holder.txtTitle2 = (TextView)convertView.findViewById(R.id.menuTitle2);
            holder.txtTitle3 = (TextView)convertView.findViewById(R.id.menuTitle3);


//            holder.txtCount = (TextView)convertView.findViewById(R.id.txtBookCount);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        map = mArray.get(position);
        //holder.txtTitle.setText(map.get(FieldConstants.bookcate_name).toString() + " (" +  map.get(FieldConstants.book_count) + ")");
        holder.txtTitle.setText("วิชา "+map.get(FieldConstants.name).toString()+" \n"+map.get(FieldConstants.nameGrade).toString()+"\nห้อง "+map.get(FieldConstants.number).toString());

        holder.txtTitle2.setText("ผู้ลา \n"+map.get(FieldConstants.firstname).toString()+" "+map.get(FieldConstants.lastname).toString());



        holder.txtTitle3.setText("ลาเรียน");
        holder.txtTitle3.setPadding(0,20,20,20);

//        holder.txtCount.setText(map.get(FieldConstants.book_count).toString());
		/*if(position%2 == 0){
			holder.txtTitle.setBackgroundResource(R.drawable.item_list_select_green_even);
		}
		else{
			holder.txtTitle.setBackgroundResource(R.drawable.item_list_select_green_odd);
		}*/

        return convertView;
    }

}
