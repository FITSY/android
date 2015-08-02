package com.example.syoung.fitsy.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.course.CourseFragment;

import java.util.ArrayList;

/**
 * Created by HyunJoo on 15. 7. 9..
 *
 * <설명>
 * current_list와 add_list를 만드는 클래스
 */

// TODO : 준형오빠가 주는 JSON형태에 맞춰서 다시 JSON 파싱하기

public class MakeDynamicList extends AsyncTask<Void, Void, Void> {

    static final String TAG = "MakeDynamicList";

    static final String TAG_ALL = "all_courses";
    static final String TAG_CURRENT = "current_course";
    static final String TAG_ADD = "add_course";

    static final String TAG_ID = "id";
    static final String TAG_CID = "cid";
    static final String TAG_CPW = "cpw";
    static final String TAG_OTYPE = "otype";
    static final String TAG_OPART = "opart";
    static final String TAG_OOPTION1 = "ooption1";
    static final String TAG_OOPTION2 = "ooption2";
    static final String TAG_EXERCISE_NAME = "exercise_name";

    //static String URL = "http://192.168.0.10:8080/sgen_test/get_all_course.php"; // 굿 카페 (or 하하) ip
    //static String URL = "http://192.168.0.21:8080/sgen_test/get_all_course.php"; // 정보를 가져올 페이지 정보 (연구실 ip)
    static String URL = "http://192.168.1.41:8080/sgen_test/get_all_course.php"; // WIFI 이름 : '엔젤리너스2층'
    //static String URL = "http://192.168.0.5:8080/sgen_test/get_all_course.php";

    //static String URL = "http://ebsud89.iptime.org:8022/getAllCourse.php";

    Activity activity;

    SearchImageRID sRid;

    // 생성자
    public MakeDynamicList(Activity activity) {
        this.activity = activity;
        sRid = new SearchImageRID(this.activity);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
                JSONArray jsonArr = new JSONArray(jsonStr);
                JSONArray temp_JSONArr = null;

                // jsonArr 의 모든 Object 들을 loop
                for (int i = 0; i < jsonArr.length(); i++) {

                    temp_JSONArr = jsonArr.getJSONArray(i);

                    switch (i) {
                        case 0:
                            // current_course
                            get_courses(i, temp_JSONArr);
                            break;
                        case 1:
                            // add_course
                            get_courses(i, temp_JSONArr);
                            break;
                    }
                }

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

        CourseFragment.backgrd.setVisibility(View.GONE);
        CourseFragment.bar.setVisibility(View.GONE);

        CourseFragment.setOnClickLisenter();

        return;

    }

    protected void get_courses(int index, JSONArray jsonArray) {
        JSONArray jsonArr = null;
        JSONObject temp_JSONobj = null;

        int temp_id;
        String temp_cid;
        String temp_cpw;
        int temp_otype;
        int temp_opart;
        int temp_ooption1;
        int temp_ooption2;
        String exercise_name;



        int temp_image_id = 0;

        try {

            Log.e(TAG, "jsonARR : " + jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                temp_JSONobj = jsonArray.getJSONObject(i);

                temp_id = temp_JSONobj.getInt(TAG_ID);
                temp_cid = temp_JSONobj.getString(TAG_CID);
                temp_cpw = temp_JSONobj.getString(TAG_CPW);
                temp_opart = temp_JSONobj.getInt(TAG_OPART);
                temp_otype = temp_JSONobj.getInt(TAG_OTYPE);
                temp_ooption1 = temp_JSONobj.getInt(TAG_OOPTION1);
                temp_ooption2 = temp_JSONobj.getInt(TAG_OOPTION2);
                exercise_name = temp_JSONobj.getString(TAG_EXERCISE_NAME);

                if (index == 0) {
                    //current_array
                    temp_image_id = sRid.getImageID(exercise_name);
                    RowItem temp_row_item = new RowItem(temp_id, temp_cid, temp_cpw, temp_otype, temp_opart, temp_ooption1, temp_ooption2, temp_image_id);
                    CourseFragment.current_array_list.add(temp_row_item);
                }
                else {
                    //add_array
                    temp_image_id = sRid.getImageID(exercise_name + "2");
                    RowItem temp_row_item = new RowItem(temp_id, temp_cid, temp_cpw, temp_otype, temp_opart, temp_ooption1, temp_ooption2, temp_image_id);
                    CourseFragment.add_array_list.add(temp_row_item);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}