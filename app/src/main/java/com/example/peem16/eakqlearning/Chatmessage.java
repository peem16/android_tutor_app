package com.example.peem16.eakqlearning;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static android.view.View.VISIBLE;


public class Chatmessage extends AppCompatActivity {


    private String jResult;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private HashMap<String, Object> objMap;
    private  String keysv = ServerConnect.KEY_SERVER;

    private ListView listView;

    private ProgressBar progressBar;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private ProcessSelectChat SelectChat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_chatroom);


        listView = (ListView) findViewById(R.id.listview);


        objArrayList = new ArrayList<HashMap<String,Object>>();

        progressBar = (ProgressBar)findViewById(R.id.progressBar6);

        SelectChat = new ProcessSelectChat();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            SelectChat.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            SelectChat.execute();
        }




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                objMap = (HashMap<String, Object>)listView.getItemAtPosition(position);


                Intent Intent = new Intent(getApplicationContext(),Chat_Room.class);

                Intent.putExtra("room_name",objMap.get(FieldConstants.id_timetable).toString() );

                startActivity(Intent);


            }
        });



     }


    private class ProcessSelectChat extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("IDUserAccount",Initial.SharePref.getStringIDUserAccount());



            Initial.httpConn = new ServerConnect(Chatmessage.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.selectchat, ph);
            Initial.SharePref = new PreferenceClass(getApplication());


            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {
                objArrayList.clear();
                JSONObject jObject = new JSONObject(jResult);
                if(!jObject.getString("result").trim().equals("null")){
                    JSONArray jArray = new JSONArray(jObject.getString("result"));
                    int size = jArray.length();
                    for(int i=0; i<size ; i++){
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        objMap = new HashMap<String, Object>();



                      objMap.put(FieldConstants.id_timetable, jsonObject.getString(FieldConstants.id_timetable));
                        objMap.put(FieldConstants.name, jsonObject.getString(FieldConstants.name));
                        objMap.put(FieldConstants.number, jsonObject.getString(FieldConstants.number));
                        objMap.put(FieldConstants.Start_time, jsonObject.getString(FieldConstants.Start_time));
                        objMap.put(FieldConstants.End_time, jsonObject.getString(FieldConstants.End_time));



                        objArrayList.add(objMap);
//                        try {
//                            if(Integer.parseInt(jsonObject.getString(FieldConstants.book_count)) > 0) objArrayList.add(objMap);
//                        }
//                        catch(Exception e){
//                            objArrayList.add(objMap);
//                        }
                    }
                }else{

                    TextView empty  =(TextView) findViewById(R.id.emptyList6);
                    empty.setVisibility(VISIBLE);


                }
                listView.setAdapter(null);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);
//                AppStatus.setPausing(false);
                mAdapter = new chatroomAdapter(getApplication(), R.layout.test6,objArrayList);
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();

            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }

}

