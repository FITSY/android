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

    /**
     * 3. 코스 내용 업데이트
     updateCourse.php

     POST [cid, cpw, otype, opart, ooption1, ooption2, eid, ename, corder]
     => [사용자 아이디, 사용자 비번, 운동 타입, 운동 파트, 옵션1, 옵션2, 운동 고유 아이디, 운동 영어이름, 운동 한글 이름, 운동 코스 순서]

     리턴값 {"success":"1"} or {"success":"0"}
     */

    static final String TAG_ID = "id";
    static final String TAG_CID = "cid";
    static final String TAG_CPW = "cpw";
    static final String TAG_OOPTION1 = "ooption1";
    static final String TAG_OOPTION2 = "ooption2";
    static final String TAG_EXERCISE_NAME = "exercise_name";

    static final int SUCCESS = 1;

    private int response;

    static final int RECOMMEND = 1;
    static final int HISTORY = 2;

    //String URL = "http://192.168.0.21:8080/sgen_test/change_course.php";
    String URL = "http://ebsud89.iptime.org:8022/updateCourse.php";

    Activity activity;

    private int whichClass;

    // 생성자
    public ChangeCurrentCourse(Activity activity, ArrayList<RowItem> data, int whichClass) {
        this.activity = activity;
        this.whichClass = whichClass;
        response = 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(whichClass == RECOMMEND) {
            // recommend_course로 현재 코스를 바꿀 경우
            CourseFragment.backgrd.setVisibility(View.VISIBLE);
            CourseFragment.bar.setVisibility(View.VISIBLE);
        }else{
            // history_course로 현재 코스를 바꿀 경우

        }
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // Service Handler Instance 생성
        ServiceHandler sh = new ServiceHandler();

        Log.e(TAG, URL);
        String jsonStr = sh.makeServiceCall(URL, ServiceHandler.POST);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                response = jsonObj.getInt("success");

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

        if(response == SUCCESS){
            if(whichClass == RECOMMEND) {
                CourseFragment.startConnection();
            }else{

            }
        }else{
            Log.e(TAG, "서버쪽에서 동작을 정상적으로 처리하지 못했습니다");
        }

        return;
    }

}