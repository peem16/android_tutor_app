package com.example.peem16.eakqlearning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static com.example.peem16.eakqlearning.Initial.SharePref;
import static com.example.peem16.eakqlearning.ServerConnect.KEY_SERVER;

public class TwoFragment extends Fragment {

    String URL ;

    public  ImageView imageView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_two, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.imageView2);
        URL = KEY_SERVER+"tutor3/public/storage/News/"+SharePref.getNews2()+"";
        Glide.with(getContext()).load(URL).into(imageView);

        imageView.getLayoutParams().height = 800;
        imageView.getLayoutParams().width = 800;
        imageView.setVisibility(View.VISIBLE);


        return rootView;
    }
}