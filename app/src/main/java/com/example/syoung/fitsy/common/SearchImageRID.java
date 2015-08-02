package com.example.syoung.fitsy.common;

import android.app.Activity;

/**
 * Created by HyunJoo on 2015. 8. 2..
 */
public class SearchImageRID {

    Activity activity;

    public SearchImageRID (Activity activity){

        this.activity = activity;

    }

    public int getImageID (String exercise_name){

        int temp_id = activity.getResources().getIdentifier(exercise_name, "drawable", activity.getPackageName());

        return temp_id;
    }

}
