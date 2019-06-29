package com.example.peem16.eakqlearning;

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

public class detailCouresAdapter extends ArrayAdapter<HashMap<String,Object>>{
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String,Object>> mArray;
    private HashMap<String,Object> map;
    private int iView;
    private static Context context;
    public detailCouresAdapter(Context c, int layoutView, ArrayList<HashMap<String, Object>> objects) {
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
            holder.textGrade = (TextView)convertView.findViewById(R.id.textGrade);
            holder.amount = (TextView)convertView.findViewById(R.id.amount);
            holder.price = (TextView)convertView.findViewById(R.id.txtdate);
            holder.per_round = (TextView)convertView.findViewById(R.id.per_round);

            holder.note = (TextView)convertView.findViewById(R.id.note);
            holder.Time_length = (TextView)convertView.findViewById(R.id.Time_length);



//            holder.txtCount = (TextView)convertView.findViewById(R.id.txtBookCount);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        map = mArray.get(position);
        //holder.txtTitle.setText(map.get(FieldConstants.bookcate_name).toString() + " (" +  map.get(FieldConstants.book_count) + ")");
        holder.textGrade.setText(map.get(FieldConstants.nameGrade).toString());
        holder.amount.setText(map.get(FieldConstants.amount_courses).toString()+" ครั้ง");
        holder.price.setText(map.get(FieldConstants.price).toString()+"บ.");
        holder.per_round.setText(map.get(FieldConstants.per_round).toString()+" ชม.");

        holder.note.setText(map.get(FieldConstants.note).toString());
        holder.note.setVisibility(View.GONE);

        holder.Time_length.setText(map.get(FieldConstants.Time_length).toString()+" ");



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
