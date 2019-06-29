package com.example.peem16.eakqlearning;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class testingShow  extends AppCompatActivity implements View.OnClickListener {
    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    Context ctx;
    private String datetime,comment,ask1,ask2,ask3,name,ask4,ask5,type;
    private TextView textView16, textView11, textView12, textView13, textView17,textView14,textView15;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getParam();
        bindData();
    }
    private void bindData() {
        textView16.setText(name);

        textView11.setText(ask1+"ตะแนน");
        textView12.setText(ask2+"ตะแนน");
        textView13.setText(ask3+"ตะแนน");

        textView14.setText(ask4+"ตะแนน");
        textView15.setText(ask5+"ตะแนน");



        textView17.setText(comment);





    }
    private void getParam() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            HashMap<String, Object> map = (HashMap<String, Object>) bundle.getSerializable(testingShow.PARAM_MAP);
            String tag = bundle.getString(testingShow.PARAM_TAG);

            ctx = this;



            comment = map.get(FieldConstants.comment).toString();
            ask1 = map.get(FieldConstants.ask1).toString();
            ask2 = map.get(FieldConstants.ask2).toString();
            ask3 = map.get(FieldConstants.ask3).toString();

            ask4 = map.get(FieldConstants.ask4).toString();
            ask5 = map.get(FieldConstants.ask5).toString();
            name = map.get(FieldConstants.name).toString();

            type = map.get(FieldConstants.idtype).toString();

            if(type.equals("1")){
                setContentView(R.layout.testingshow);

            }else{
                setContentView(R.layout.testingshow2);


            }
            textView16 = (TextView) findViewById(R.id.textView16);

            textView11 = (TextView) findViewById(R.id.textView11);
            textView12 = (TextView) findViewById(R.id.textView12);
            textView13 = (TextView) findViewById(R.id.textView13);
            textView14 = (TextView) findViewById(R.id.textView14);
            textView15 = (TextView) findViewById(R.id.textView15);

            textView17 = (TextView) findViewById(R.id.textView17);




        }
    }

}
