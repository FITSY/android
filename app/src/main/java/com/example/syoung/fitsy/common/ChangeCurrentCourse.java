package com.example.syoung.fitsy.common;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.syoung.fitsy.course.CourseFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by HyunJoo on 2015. 8. 2..
 */


public class ChangeCurrentCourse extends AsyncTask<Void, Void, Void> {


    static final String TAG = "ChangeCurrentCourse";

    static final String TAG_SUCCESS = "success";

    static final String TAG_ID = "id";
    static final String TAG_CID = "cid";
    static final String TAG_CPW = "cpw";
    static final String TAG_OOPTION1 = "ooption1";
    static final String TAG_OOPTION2 = "ooption2";
    static final String TAG_EXERCISE_NAME = "exercise_name";

    String URL;

    Activity activity;

    // 생성자
    public ChangeCurrentCourse(Activity activity, ArrayList<RowItem> data, String url) {
        this.activity = activity;
        URL = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // TODO : recommend_course를 전송할 때랑 history에서 전송할 때랑 아래 것이 달라야 한다.
        CourseFragment.backgrd.setVisibility(View.VISIBLE);
        CourseFragment.bar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // Service Handler Instance 생성
        ServiceHandler sh = new ServiceHandler();

        Log.e("MakeDynamicList", URL);
        String jsonStr = sh.makeServiceCall(URL, ServiceHandler.POST);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // JSON Array 노드 얻음
                JSONArray jsonArr = jsonObj.getJSONArray(TAG_SUCCESS);



            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "해당 URL 로부터 아무런 정보를 얻을 수 없습니다.");
        }

        return null;
    }

    @Override
    protected void onPostExecute (Void result) {
        super.onPostExecute(result);

        // TODO : current_course를 다시 받아야 한다.
        // TODO : recommend_course를 전송할 때랑 history에서 전송할 때랑 아래 것이 달라야 한다.
        CourseFragment.backgrd.setVisibility(View.GONE);
        CourseFragment.bar.setVisibility(View.GONE);

        CourseFragment.setOnClickLisenter();

        return;

    }

}