package com.example.peem16.eakqlearning;

import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static com.example.peem16.eakqlearning.Initial.SharePref;
import static com.example.peem16.eakqlearning.ServerConnect.KEY_SERVER;
public class showqr extends AppCompatActivity {


    private ImageView qrcode ;

    String URL ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_qr);

        qrcode = (ImageView) findViewById(R.id.qr_code);
        URL = KEY_SERVER+"tutor3/public/storage/QR/"+SharePref.getStringQr_code()+"";
        Glide.with(getApplicationContext()).load(URL).into(qrcode);

        qrcode.getLayoutParams().height = 800;
        qrcode.getLayoutParams().width = 800;
        qrcode.setVisibility(View.VISIBLE);





    }




}
