package com.example.peem16.eakqlearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.peem16.eakqlearning.Initial.SharePref;
import static com.example.peem16.eakqlearning.ServerConnect.KEY_SERVER;

/**
 * Created by May- on 10/30/2017.
 */

public class SendAdapter extends ArrayAdapter<HashMap<String,Object>>{
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String,Object>> mArray;
    private HashMap<String,Object> map;
    private int iView;
    String URL ;
    private static Context context;
    public SendAdapter(Context c, int layoutView, ArrayList<HashMap<String, Object>> objects) {
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
            holder.bluetext = (TextView)convertView.findViewById(R.id.menuTitle);
            holder.timeblue = (TextView)convertView.findViewById(R.id.menuTitle2);
            holder.imgblue = (ImageView)convertView.findViewById(R.id.imageView5);
            holder.nameblue = (TextView)convertView.findViewById(R.id.textView49);
            holder.li1 = (LinearLayout)convertView.findViewById(R.id.l1);



            holder.redtext = (TextView)convertView.findViewById(R.id.menuTitle9);
            holder.timered = (TextView)convertView.findViewById(R.id.menuTitle10);
            holder.imgred = (ImageView)convertView.findViewById(R.id.imageView6);
            holder.namered = (TextView)convertView.findViewById(R.id.textView53);
            holder.li2 = (LinearLayout)convertView.findViewById(R.id.l2);
//            holder.txtCount = (TextView)convertView.findViewById(R.id.txtBookCount);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        map = mArray.get(position);
        //holder.txtTitle.setText(map.get(FieldConstants.bookcate_name).toString() + " (" +  map.get(FieldConstants.book_count) + ")");


if(Initial.SharePref.getStringIDUserAccount().toString().equals(map.get(FieldConstants.iduser).toString())){


    holder.bluetext.setText(map.get(FieldConstants.chat_msg).toString());
    holder.timeblue.setText(map.get(FieldConstants.chat_time2).toString());

    URL = KEY_SERVER+"tutor3/public/storage/Pic/"+SharePref.getprofile()+"";
    Glide.with(getContext()).load(URL).into(holder.imgblue);
//    holder.imgblue.getLayoutParams().height = 100;
//    holder.imgblue.getLayoutParams().width = 100;
    holder.imgblue.setVisibility(View.VISIBLE);


    holder.nameblue.setText(map.get(FieldConstants.chat_user_name).toString());


    holder.bluetext.setVisibility(View.VISIBLE);
    holder.timeblue.setVisibility(View.VISIBLE);
    holder.li1.setVisibility(View.VISIBLE);
    holder.nameblue.setVisibility(View.VISIBLE);



    holder.redtext.setVisibility(View.GONE);
    holder.timered.setVisibility(View.GONE);
    holder.imgred.setVisibility(View.GONE);
    holder.namered.setVisibility(View.GONE);
    holder.li2.setVisibility(View.GONE);
}else{
//
    holder.redtext.setText(map.get(FieldConstants.chat_msg).toString());
    holder.timered.setText(map.get(FieldConstants.chat_time2).toString());

    URL = KEY_SERVER+"tutor3/public/storage/Pic/"+map.get(FieldConstants.pic).toString()+"";
    Glide.with(getContext()).load(URL).into(holder.imgred);
//    holder.imgred.getLayoutParams().height = 100;
//    holder.imgred.getLayoutParams().width = 100;
    holder.imgred.setVisibility(View.VISIBLE);

    holder.namered.setText(map.get(FieldConstants.chat_user_name).toString());




    holder.redtext.setVisibility(View.VISIBLE);
    holder.timered.setVisibility(View.VISIBLE);
    holder.namered.setVisibility(View.VISIBLE);
    holder.li2.setVisibility(View.VISIBLE);

    holder.bluetext.setVisibility(View.GONE);
    holder.timeblue.setVisibility(View.GONE);
    holder.imgblue.setVisibility(View.GONE);
    holder.nameblue.setVisibility(View.GONE);
    holder.li1.setVisibility(View.GONE);

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
