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

public class asslist_assstuAdapter extends ArrayAdapter<HashMap<String,Object>>{
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String,Object>> mArray;
    private HashMap<String,Object> map;
    private int iView;
    private static Context context;
    public asslist_assstuAdapter(Context c, int layoutView, ArrayList<HashMap<String, Object>> objects) {
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

            holder.name = (TextView)convertView.findViewById(R.id.textView54);



            holder.as1 = (TextView)convertView.findViewById(R.id.textView69);
            holder.as2 = (TextView)convertView.findViewById(R.id.textView68);
            holder.as3 = (TextView)convertView.findViewById(R.id.textView67);
            holder.as4 = (TextView)convertView.findViewById(R.id.textView66);
            holder.as5 = (TextView)convertView.findViewById(R.id.textView65);
            holder.t1 = (TextView)convertView.findViewById(R.id.textView74);


            holder.as2_1 = (TextView)convertView.findViewById(R.id.textView2_1);
            holder.as2_2 = (TextView)convertView.findViewById(R.id.textView2_2);
            holder.as2_3 = (TextView)convertView.findViewById(R.id.textView2_3);
            holder.as2_4 = (TextView)convertView.findViewById(R.id.textView2_4);
            holder.as2_5 = (TextView)convertView.findViewById(R.id.textView2_5);

            holder.t2 = (TextView)convertView.findViewById(R.id.textView22222);









//            holder.txtCount = (TextView)convertView.findViewById(R.id.txtBookCount);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        map = mArray.get(position);
        //holder.txtTitle.setText(map.get(FieldConstants.bookcate_name).toString() + " (" +  map.get(FieldConstants.book_count) + ")");


        holder.name.setText(map.get(FieldConstants.firstname).toString()+" "+map.get(FieldConstants.lastname).toString());

        holder.as1.setText(map.get(FieldConstants.bf1).toString());
        holder.as2.setText(map.get(FieldConstants.bf2).toString());
        holder.as3.setText(map.get(FieldConstants.bf3).toString());
        holder.as4.setText(map.get(FieldConstants.bf4).toString());
        holder.as5.setText(map.get(FieldConstants.bf5).toString());





        double ss= Double.parseDouble(map.get(FieldConstants.t1).toString());

        ss = ss/5;

String sa2 = "";
        if(ss<=1){

            sa2 = "ควรปรับปรุง";

        }else if(ss<=2){
            sa2 = "พอใช้";

        }else if(ss<=3){

            sa2 = "ปานกลาง";
        }else if(ss<=4){

            sa2 = "ดี";
        }else if(ss<=5){

            sa2 = "ดีมาก";
        }
        holder.t1.setText(sa2);




if(map.get(FieldConstants.af1).toString().equals("null")){
    holder.as2_1.setText("-");

    holder.as2_2.setText("-");

    holder.as2_3.setText("-");

    holder.as2_4.setText("-");

    holder.as2_5.setText("-");
}else{

    holder.as2_1.setText(map.get(FieldConstants.af1).toString());

    holder.as2_2.setText(map.get(FieldConstants.af2).toString());

    holder.as2_3.setText(map.get(FieldConstants.af3).toString());

    holder.as2_4.setText(map.get(FieldConstants.af4).toString());

    holder.as2_5.setText(map.get(FieldConstants.af5).toString());
}

        double ss2 = 0.0;
            if(map.get(FieldConstants.t2).toString().equals("null")){


            }else{
                 ss2= Double.parseDouble(map.get(FieldConstants.t2).toString());


            }


if(ss2 == 0.0){

    holder.t2.setText("-");

}else{
    ss2 = ss2/5;

    String sa3 = "";
    if(ss2<=1){

        sa3 = "ควรปรับปรุง";

    }else if(ss2<=2){
        sa3 = "พอใช้";

    }else if(ss2<=3){

        sa3 = "ปานกลาง";
    }else if(ss2<=4){

        sa3 = "ดี";
    }else if(ss2<=5){

        sa3 = "ดีมาก";
    }
    holder.t2.setText(sa3);

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
