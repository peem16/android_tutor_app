package com.example.peem16.eakqlearning;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static com.example.peem16.eakqlearning.Initial.SharePref;
import static com.example.peem16.eakqlearning.ServerConnect.KEY_SERVER;

public class OneFragment extends Fragment {

    String URL ;

    public  ImageView imageView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_one, container, false);

//

        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        URL = KEY_SERVER+"tutor3/public/storage/News/"+SharePref.getNews1()+"";
        Glide.with(getContext()).load(URL).into(imageView);

        imageView.getLayoutParams().height = 800;
        imageView.getLayoutParams().width = 800;
        imageView.setVisibility(View.VISIBLE);




        return rootView;
    }


}