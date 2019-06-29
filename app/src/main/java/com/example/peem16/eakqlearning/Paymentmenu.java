package com.example.peem16.eakqlearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Paymentmenu extends AppCompatActivity implements View.OnClickListener {
    private Button payre,paybuy;
    @Override
    public void onClick(View v) {


                if (v == payre) {

                    Intent intent = new Intent(Paymentmenu.this, Paymentreser.class);
                    startActivity(intent);

                }else if(v == paybuy) {


                    Intent intent = new Intent(Paymentmenu.this, Paymentbuy.class);
                    startActivity(intent);





        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.paymentmenu);


        payre = (Button) findViewById(R.id.payre);
        paybuy = (Button) findViewById(R.id.paybuy);
        payre.setOnClickListener(this);
        paybuy.setOnClickListener(this);


    }
}
