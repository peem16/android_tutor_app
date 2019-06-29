package com.example.peem16.eakqlearning;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by May- on 10/30/2017.
 */

public class CategoriesAdapter extends ArrayAdapter<HashMap<String,Object>>{
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String,Object>> mArray;
    private HashMap<String,Object> map;
    private int iView;
    private static Context context;
    public CategoriesAdapter(Context c, int layoutView,ArrayList<HashMap<String, Object>> objects) {
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
            holder.txtTitle2 = (TextView)convertView.findViewById(R.id.textView21);

//            holder.txtCount = (TextView)convertView.findViewById(R.id.txtBookCount);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        map = mArray.get(position);
        //holder.txtTitle.setText(map.get(FieldConstants.bookcate_name).toString() + " (" +  map.get(FieldConstants.book_count) + ")");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(map.get(FieldConstants.Start_date).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
      String  dayOfTheWeek = (String) DateFormat.format("EEEE", date);

        if(map.get(FieldConstants.title).toString().equals("1")){

            holder.txtTitle.setText("วิชา "+map.get(FieldConstants.name).toString()+"\n วัน "+map.get(FieldConstants.Start_date).toString()+" - "+map.get(FieldConstants.End_date).toString()+"\n"+dayOfTheWeek);

        }else if(map.get(FieldConstants.title).toString().equals("2")){
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = null;
            try {
                date2 = dateFormat2.parse(map.get(FieldConstants.date).toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String  dayOfTheWeek2 = (String) DateFormat.format("EEEE", date2);
            holder.txtTitle.setText("วิชา "+map.get(FieldConstants.name).toString()+"(สอนแทน) \n วัน "+map.get(FieldConstants.date).toString()+"\n"+dayOfTheWeek2);


        }
        else{
            SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
            Date date3 = null;
            try {
                date3 = dateFormat3.parse(map.get(FieldConstants.date).toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String  dayOfTheWeek3 = (String) DateFormat.format("EEEE", date3);
            holder.txtTitle.setText("วิชา "+map.get(FieldConstants.name).toString()+"(สอนนอกตาราง) \n วัน "+map.get(FieldConstants.date).toString()+"\n"+dayOfTheWeek3);



        }









        holder.txtTitle2.setText("เวลา "+map.get(FieldConstants.Start_time).toString()+" "+map.get(FieldConstants.End_time).toString()+"\nห้อง "+map.get(FieldConstants.number).toString());
        holder.txtTitle2.setTextColor(Color.BLACK);
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
