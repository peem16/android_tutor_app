package com.example.peem16.eakqlearning;

/**
 * Created by May- on 9/12/2017.
 */

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


@SuppressWarnings("serial")
public class ParamHolder extends ArrayList<NameValuePair> {
    public void add(String paramKey, String paramValue){
        this.add(new BasicNameValuePair(paramKey, paramValue));
    }

}