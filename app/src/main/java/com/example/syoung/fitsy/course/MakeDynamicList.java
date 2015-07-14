package com.example.syoung.fitsy.course;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.SimpleAdapter;

import com.example.syoung.fitsy.MainActivity;
import com.example.syoung.fitsy.common.CommonUtilities;

/**
 * Created by HyunJoo on 15. 7. 9..
 */
public class MakeDynamicList extends AsyncTask<Void, Void, Void> {
    static final String TAG = "JsonParser";
    static final int TAG_CURRENT = 1;
    static final int TAG_ADD = 2;
    static final int TAG_RECO = 3;
    static final String TAG_EXERCISE_NAME = "exercise_name";

    static String URL = null; // 정보를 가져올 페이지 정보
    static String ChosenTag;
    static int whichTag;

    ArrayList<HashMap<String, String>> list;
    JSONArray jsonArr = null;

    // 생성자
    public MakeDynamicList(String _url, int _whichTag) {

        this.URL = _url;
        this.whichTag = _whichTag;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // Service Handler Instance 생성
        ServiceHandler sh = new ServiceHandler();

        // 파라미터 추가. 유저의 ID를 전송한다.
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", CommonUtilities.ID));

        // JSON 형태의 응답(response)을 받기 위해 HTTP request 를 해당 URL 로 보낸다.
        String jsonStr = sh.makeServiceCall(URL, ServiceHandler.GET, params);

        Log.d("Response: ", jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                switch (whichTag){
                    case TAG_CURRENT:
                        ChosenTag = "current_course";
                        break;
                    case TAG_ADD:
                        ChosenTag = "add_course";
                        break;
                    case TAG_RECO:
                        ChosenTag = "recommend_course";
                        break;
                }

                // JSON Array 노드 얻음
                jsonArr = jsonObj.getJSONArray(ChosenTag);

                // jsonArr 의 모든 Object 들을 loop
                for (int i = 0; i < jsonArr.length(); i++) {

                    JSONObject c = jsonArr.getJSONObject(i);

                    String exercise_name = c.getString(TAG_EXERCISE_NAME);

                    // 임시 hash map
                    HashMap<String, String> temp_hash_map = new HashMap<String, String>();

                    // HashMap <key, value>
                    temp_hash_map.put(TAG_EXERCISE_NAME, exercise_name);

                    // adding contact to contact list
                    list.add(temp_hash_map);
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
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        switch (whichTag){
            case TAG_CURRENT:
                CourseFragment.current_course_list = list;
                break;
            case TAG_ADD:
                CourseFragment.add_course_list = list;
                break;
            case TAG_RECO:
                CourseFragment.recommend_course_list = list;
                break;
        }

    }

}