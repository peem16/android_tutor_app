package com.example.peem16.eakqlearning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import android.text.format.DateFormat;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Chat_Room extends AppCompatActivity{
    private ArrayList<HashMap<String, Object>> objArrayList;

    private HashMap<String, Object> objMap;
    private Button btn_send_msg;
    private EditText input_msg;
    private ListView listView;
    private DatabaseReference root;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private FirebaseListAdapter<Chat> adapter;
    private String user_name,room_name;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_rooms = new ArrayList<>();


    private String temp_key;
private long time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.chat_room);


        btn_send_msg = (Button) findViewById(R.id.button16);
        input_msg = (EditText) findViewById(R.id.editText6);
        listView = (ListView) findViewById(R.id.listview) ;
        listView.setEnabled(false);
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.send,list_of_rooms);
        listView.setAdapter(arrayAdapter);

//        user_name = getIntent().getExtras().get("user_name").toString();
        room_name = getIntent().getExtras().get("room_name").toString();
        objArrayList = new ArrayList<HashMap<String,Object>>();


        setTitle("Room Chat ");

        root = FirebaseDatabase.getInstance().getReference("Room").child(room_name);
        btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                time =  new Date().getTime();

                Map<String,Object> map = new HashMap<String,Object>();
                    temp_key = root.push().getKey();
                    root.updateChildren(map);


                    DatabaseReference message_root =root.child(temp_key);
                    Map<String,Object> map2 = new HashMap<String,Object>();

                         map2.put("pic",Initial.SharePref.getprofile());

                      map2.put("name",Initial.SharePref.getStringFirstName()+" "+Initial.SharePref.getStringLastname());
                      map2.put("msg",input_msg.getText().toString());
                      map2.put("date",time);
                      map2.put("id",Initial.SharePref.getStringIDUserAccount());


                    message_root.updateChildren(map2);

                input_msg.setText("");

            }
        });
//        displayChatMessage();





        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                append_chat_conversation(dataSnapshot);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                append_chat_conversation(dataSnapshot);


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private  String chat_msg,chat_user_name,chat_time2,iduser,pic;
    private  long chat_time;



//    private void displayChatMessage(){
////        mAdapter = new chatroomAdapter(getApplication(), R.layout.test6,objArrayList);
////        mAdapter.notifyDataSetInvalidated();
////        listView.invalidateViews();
//
//
//
//
//        adapter = new FirebaseListAdapter<Chat>(this,Chat.class,R.layout.send,FirebaseDatabase.getInstance().getReference("Room").child(room_name)) {
//            @Override
//            protected void populateView(View v, Chat model, int position) {
//
//                TextView    messgetext ,messgeUser,messgetime;
//
//                messgetext = (TextView) v.findViewById(R.id.menuTitle);
//                messgetime = (TextView) v.findViewById(R.id.menuTitle2);
//
//                messgetext.setText(model.getMessageText());
//                messgetime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",model.getMessageTime()));
//
//            }
//        };
//
//        listView.setAdapter(adapter);
//    }
//



    private void append_chat_conversation(DataSnapshot dataSnapshot) {



        Iterator i = dataSnapshot.getChildren().iterator();


        while (i.hasNext()){
            objMap = new HashMap<String, Object>();
            chat_time =  (Long) ((DataSnapshot)i.next()).getValue();
            chat_time2 = (String) DateFormat.format("dd-MM-yyyy (HH:mm:ss)",chat_time);

            iduser = (String) ((DataSnapshot)i.next()).getValue();

            chat_msg = (String) ((DataSnapshot)i.next()).getValue();



            chat_user_name = (String) ((DataSnapshot)i.next()).getValue();

            pic = (String) ((DataSnapshot)i.next()).getValue();


            objMap.put(FieldConstants.chat_time2,chat_time2);
            objMap.put(FieldConstants.chat_msg,chat_msg);
            objMap.put(FieldConstants.iduser,iduser);

            objMap.put(FieldConstants.chat_user_name,chat_user_name);

            objMap.put(FieldConstants.pic,pic);


            objArrayList.add(objMap);


//            chat_conversation.append(chat_user_name+" : "+chat_msg+" "+chat_time2+"  \n");
        }
        listView.setAdapter(null);
        mAdapter = new SendAdapter(getApplication(), R.layout.send, objArrayList);
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetInvalidated();
        listView.invalidateViews();
    }
}
