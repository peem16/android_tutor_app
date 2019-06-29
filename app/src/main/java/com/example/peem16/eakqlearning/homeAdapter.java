package com.example.peem16.eakqlearning;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.peem16.eakqlearning.MainActivity_Login.close;

/**
 * Created by May- on 10/30/2017.
 */

public class homeAdapter extends ArrayAdapter<HashMap<String,Object>>{
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String,Object>> mArray;
    private HashMap<String,Object> map , map2;
    private int iView;
    private Dialog d;
    private  Processcreser reser;
    private TextView btnConfirm,btnCancelPay;
    public TextView btnreser;
    private HashMap<String, Object> objMap;
    public Processcdetail cd;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private ListView ListView3;
    private String id;
    private int mView;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private ArrayList<HashMap<String, Object>> objArrayList2;
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
    private  String keysv = ServerConnect.KEY_SERVER;

    private String jResult;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Initial.SharePref = new PreferenceClass(getContext());
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(mView, null);
            holder.txtNameCourse = (TextView)convertView.findViewById(R.id.txtNameCourse);
            holder.txtdetailCourse = (TextView)convertView.findViewById(R.id.txtCourseDetail);
            holder.img_course = (ImageView)convertView.findViewById(R.id.imgCourse);


            holder.textView19 = (TextView)convertView.findViewById(R.id.textView19);

            holder.textView20 = (TextView)convertView.findViewById(R.id.textView20);


            holder.btnass = (TextView)convertView.findViewById(R.id.btnass);

            holder.li1 = (LinearLayout)convertView.findViewById(R.id.li1);
            holder.li2 = (LinearLayout)convertView.findViewById(R.id.li2);



            holder.btnpay = (TextView)convertView.findViewById(R.id.btnpay);
            holder.btnpay.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    objArrayList = PreferenceClass.objArrayLists;

                    map = objArrayList.get(position);

                    Bundle bundle = new Bundle();
                    bundle.putString(confirmpayreser.PARAM_TAG,"Coures_detail");
                    bundle.putSerializable(confirmpayreser.PARAM_MAP,map);

                    Intent intent = new Intent(getContext(), confirmpayreser.class);

                    intent.putExtras(bundle);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);



                    mContext.startActivity(intent);



                }
            });

//            holder.txtCount = (TextView)convertView.findViewById(R.id.txtBookCount);


            holder.btnass.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

//                    objArrayList = PreferenceClass.objArrayLists;

                    map = objArrayList.get(position);


                    Initial.SharePref.setposition(""+position);

                    Bundle bundle = new Bundle();
                    bundle.putString(testingShow.PARAM_TAG,"testingShow");
                    bundle.putSerializable(testingShow.PARAM_MAP,map);

                    Intent intent = new Intent(getContext(), testingShow.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bundle);


                    mContext.startActivity(intent);




                }
            });


            holder.btnBuy = (TextView)convertView.findViewById(R.id.btnBuy);
            holder.btnBuy.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    objArrayList = PreferenceClass.objArrayLists;

                    map = objArrayList.get(position);


                    Initial.SharePref.setposition(""+position);

                    Bundle bundle = new Bundle();
                    bundle.putString(listbuydetail.PARAM_TAG,"listbuydetail");
                    bundle.putSerializable(listbuydetail.PARAM_MAP,objMap);

                    Intent intent = new Intent(getContext(), listbuydetail.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bundle);


                    mContext.startActivity(intent);




                }
            });
            holder.btnreser = (TextView)convertView.findViewById(R.id.btnReserve);
            holder.btnreser.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {


                    showDialogfogetPass(position);
                }
            });

            holder.position = position;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        map = objArrayList.get(position);

        //holder.txtTitle.setText(map.get(FieldConstants.bookcate_name).toString() + " (" +  map.get(FieldConstants.book_count) + ")");
        holder.txtNameCourse.setText(map.get(FieldConstants.name).toString());

        holder.txtdetailCourse.setText(map.get(FieldConstants.course_detail).toString());
        holder.btnass.setVisibility(View.GONE);
        holder.btnBuy.setVisibility(View.GONE);
        holder.btnreser.setVisibility(View.GONE);
        holder.textView19.setVisibility(View.GONE);
        holder.btnpay.setVisibility(View.GONE);
        if(Initial.SharePref.getStringIDUserAccount().equals("") ) {


        }else{






            if(map.get(FieldConstants.IDUserAccount).toString().equals("null")){


                String sss = Initial.SharePref.getStringtutor().toString();

                ShowMe.log("ooooo"+sss);

                if(!sss.equals("null")){


                }else{

                    holder.btnreser.setVisibility(View.VISIBLE);


                }


            }else{

                if(map.get(FieldConstants.status).toString().equals("ยังไม่ได้ชำระเงิน")){
                    holder.li1.setVisibility(View.GONE);
                    holder.li2.setVisibility(View.VISIBLE);
                    holder.btnpay.setVisibility(View.VISIBLE);
                }else if(map.get(FieldConstants.status).toString().equals("รอการตรวจสอบการชำระ")){
                    holder.li1.setVisibility(View.GONE);
                    holder.li2.setVisibility(View.GONE);
                    holder.textView19.setVisibility(View.VISIBLE);


                }else if(map.get(FieldConstants.status).toString().equals("ผ่านการตรวจสอบ")){
                    holder.li1.setVisibility(View.GONE);
                    holder.li2.setVisibility(View.GONE);

                    holder.textView20.setVisibility(View.VISIBLE);
                    holder.textView20.setText("รหัสทดสอบ: "+map.get(FieldConstants.random_key).toString());

                }
                holder.btnreser.setVisibility(View.GONE);


            }


            if(map.get(FieldConstants.idTesting).toString().equals("null")){
                holder.btnBuy.setVisibility(View.GONE);


            }else{
                holder.li1.setVisibility(View.VISIBLE);
                holder.btnBuy.setVisibility(View.VISIBLE);
                holder.btnass.setVisibility(View.VISIBLE);
                holder.textView19.setVisibility(View.GONE);
                holder.textView20.setVisibility(View.GONE);

            }






        }







        if(map.get(FieldConstants.idtype).toString().equals("1")){


            holder.img_course.setImageResource(R.drawable.music2);
            holder.img_course.setVisibility(View.VISIBLE);

        }else{

            holder.img_course.setImageResource(R.drawable.book);
            holder.img_course.setVisibility(View.VISIBLE);

        }


//




//        btnreser = (TextView) convertView.findViewById(R.id.btnReserve);
//





//        holder.txtCount.setText(map.get(FieldConstants.book_count).toString());
		/*if(position%2 == 0){
			holder.txtTitle.setBackgroundResource(R.drawable.item_list_select_green_even);
		}
		else{
			holder.txtTitle.setBackgroundResource(R.drawable.item_list_select_green_odd);
		}*/

        return convertView;
    }
    private View.OnClickListener cancel = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            d.dismiss();
        }
    };

    private void showDialogfogetPass(final int str){


        d = new Dialog(getContext());
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.reser);
        btnConfirm = (TextView) d.findViewById(R.id.btnkey);

       TextView textView37 = (TextView) d.findViewById(R.id.textView37);

        textView37.setVisibility(View.VISIBLE);
        objArrayList = PreferenceClass.objArrayLists;

        map = objArrayList.get(str);


        btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                objArrayList = PreferenceClass.objArrayLists;

                map = objArrayList.get(str);


                btnConfirm.setEnabled(false);
                reser = new Processcreser();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    reser.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,map.get(FieldConstants.idcourse).toString(),Initial.SharePref.getStringIDUserAccount(),"100");
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    reser.execute();
                }

            }
        });








        btnCancelPay = (TextView) d.findViewById(R.id.btnCancel);

        btnCancelPay.setOnClickListener(cancel);


        d.show();

        ListView3 = (ListView) d.findViewById(R.id.Listview3);

        ListView3.setEnabled(false);

//
        cd = new Processcdetail();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            cd.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,map.get(FieldConstants.idcourse).toString());
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            cd.execute();
        }



    }

    private class Processcdetail extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idc",params[0]);


            objArrayList2 = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(getContext());
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getcd, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(!jObject.getString("result").trim().equals("null")){
                    JSONArray jArray = new JSONArray(jObject.getString("result"));
                    int size = jArray.length();
                    for(int i=0; i<size ; i++){
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        objMap = new HashMap<String, Object>();
                        objMap.put(FieldConstants.nameGrade, jsonObject.getString(FieldConstants.nameGrade));
                        objMap.put(FieldConstants.Idcourse_detail, jsonObject.getString(FieldConstants.Idcourse_detail));
                        objMap.put(FieldConstants.amount_courses, jsonObject.getString(FieldConstants.amount_courses));

                        objMap.put(FieldConstants.price, jsonObject.getString(FieldConstants.price));




                        objMap.put(FieldConstants.per_round, jsonObject.getString(FieldConstants.per_round));
                        objMap.put(FieldConstants.note, jsonObject.getString(FieldConstants.note));
                        objMap.put(FieldConstants.Time_length, jsonObject.getString(FieldConstants.Time_length));



                        objArrayList2.add(objMap);




                    }
                    ListView3.setAdapter(null);


                    mAdapter = new detailCouresAdapter(getContext(), R.layout.test2, objArrayList2);
                    ListView3.setAdapter(mAdapter);

                    mAdapter.notifyDataSetInvalidated();
                    ListView3.invalidateViews();

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }

    private class Processcreser extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idcourse",params[0]);
            ph.add("idUserAccount",params[1]);
            ph.add("reserve_price",params[2]);



            objArrayList = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(getContext());
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.reser, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                Initial.jObject = new JSONObject(jResult);

                btnConfirm.setEnabled(true);

                if (Initial.jObject.getString("status").trim().equals("true")) {

                d.dismiss();
                    Toast.makeText(getContext(), "สำเร็จ",
                            Toast.LENGTH_SHORT).show();




                    Intent intent = new Intent(getContext(), MainActivity_Login.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    close();

                    mContext.startActivity(intent);

                    //Toast.makeText(getApplicationContext(), strResult,Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getContext(), "ล้มเหลว",
                            Toast.LENGTH_SHORT).show();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }








        }




    }

}
