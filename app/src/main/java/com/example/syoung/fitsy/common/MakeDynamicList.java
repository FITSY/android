package com.example.syoung.fitsy.common;

import android.app.Activity;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.course.CourseFragment;

/**
 * Created by HyunJoo on 15. 7. 9..
 *
 * <설명>
 * current_list와 add_list를 만드는 클래스
 */
public class MakeDynamicList extends AsyncTask<Void, Void, Void> {

    static final String TAG = "MakeDynamicList";

    static final String TAG_ALL = "all_courses";
    static final String TAG_CURRENT = "current_course";
    static final String TAG_ADD = "add_course";
    static final String TAG_EXERCISE_NAME = "exercise_name";

    public static boolean isReady;

    static int test_id;

    //static String URL = "http://192.168.0.10:8080/sgen_test/get_all_course.php"; // 굿 카페 (or 하하) ip
    //static String URL = "http://192.168.0.21:8080/sgen_test/get_all_course.php"; // 정보를 가져올 페이지 정보 (연구실 ip)
    static String URL = "http://192.168.1.19:8080/sgen_test/get_all_course.php";

    Activity activity;

    // 생성자
    public MakeDynamicList(Activity activity) {

        isReady = false;
        this.activity = activity;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
                JSONArray jsonArr = jsonObj.getJSONArray(TAG_ALL);
                JSONObject temp_JSONobj = null;

                // jsonArr 의 모든 Object 들을 loop
                for (int i = 0; i < jsonArr.length(); i++) {

                    temp_JSONobj = jsonArr.getJSONObject(i);

                    switch (i) {
                        case 0:
                            // current_course
                            get_courses(i, temp_JSONobj);
                            break;
                        case 1:
                            // add_course
                            get_courses(i, temp_JSONobj);
                            break;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "해당 URL 로부터 아무런 정보를 얻을 수 없습니다.");
        }

        //onPostExecute(null);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        //CourseFragment.test_image_id = test_id;
        CourseFragment.setOnClickLisenter();

        //CourseFragment.current_course_view.setAdapter(new LazyAdapter(activity, R.layout.course_list_node, CourseFragment.current_array_list));
        //CourseFragment.add_course_view.setAdapter(new LazyAdapter(activity, R.layout.course_list_node, CourseFragment.add_array_list));

        return;

    }

    protected void get_courses(int index, JSONObject jsonObj) {
        JSONArray jsonArr = null;
        JSONObject temp_JSONobj = null;
        String exercise_name;
        int temp_image_id = 0;

        try {
            // JSON Array 노드 얻음
            switch (index) {
                case 0:
                    jsonArr = jsonObj.getJSONArray(TAG_CURRENT);
                    break;
                case 1:
                    jsonArr = jsonObj.getJSONArray(TAG_ADD);
                    break;
            }

            // jsonArr 의 모든 Object 들을 loop
            for (int i = 0; i < jsonArr.length(); i++) {

                temp_JSONobj = jsonArr.getJSONObject(i);

                exercise_name = temp_JSONobj.getString(TAG_EXERCISE_NAME);

                temp_image_id = activity.getResources().getIdentifier(exercise_name, "drawable", activity.getPackageName());

                // 임시 hash map
                RowItem temp_hash_map = new RowItem(temp_image_id, exercise_name);

                // adding contact to contact list
                if (index == 0)
                    CourseFragment.current_array_list.add(temp_hash_map);
                else
                    CourseFragment.add_array_list.add(temp_hash_map);
            }

            test_id = temp_image_id;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
