package com.example.peem16.eakqlearning;

import android.content.Context;
import android.content.Intent;
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

public class StudentAdapter2 extends ArrayAdapter<HashMap<String,Object>>{
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String,Object>> mArray;
    private HashMap<String,Object> map;
    private ArrayList<HashMap<String, Object>> objArrayList;

    private int iView;
    private static Context context;
    public StudentAdapter2(Context c, int layoutView, ArrayList<HashMap<String, Object>> objects) {
        super(c, layoutView, objects);
        mInflater = LayoutInflater.from(c);
        mArray = objects;
        iView = layoutView;
        context = c;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Initial.SharePref = new PreferenceClass(getContext());
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(iView, null);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.menuTitle);
            holder.txtTitle2 = (TextView)convertView.findViewById(R.id.textView18);

            holder.btnassessemnt = (TextView)convertView.findViewById(R.id.btnassessemnt);
            holder.btnassessemnt.setVisibility(View.GONE);
            holder.btnassessemnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map = mArray.get(position);




                      Intent intent = new Intent(getContext(), assessment_tutor.class);
                    intent.putExtra("Idtimetable_deteil",map.get(FieldConstants.Idtimetable_deteil).toString() );
                    intent.putExtra("type",map.get(FieldConstants.idtype).toString() );

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    }
                                             });

//            holder.txtCount = (TextView)convertView.findViewById(R.id.txtBookCount);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        map = mArray.get(position);
        //holder.txtTitle.setText(map.get(FieldConstants.bookcate_name).toString() + " (" +  map.get(FieldConstants.book_count) + ")");
        holder.txtTitle.setText(map.get(FieldConstants.firstname).toString()+" "+map.get(FieldConstants.lastname).toString());



        if(map.get(FieldConstants.idEvaluation).toString().equals("null")){

            holder.btnassessemnt.setVisibility(View.VISIBLE);

        }else{
            holder.btnassessemnt.setVisibility(View.VISIBLE);
            holder.btnassessemnt.setText("ประเมินแล้ว");
            holder.btnassessemnt.setEnabled(false);

        }









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
