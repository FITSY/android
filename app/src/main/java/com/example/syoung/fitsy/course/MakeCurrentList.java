package com.example.syoung.fitsy.course;

import android.app.Activity;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.view.View;

import com.example.syoung.fitsy.common.CommonUtilities;
import com.example.syoung.fitsy.common.RowItem;
import com.example.syoung.fitsy.common.SearchImageRID;
import com.example.syoung.fitsy.common.ServiceHandler;
/**
 * Created by HyunJoo on 15. 7. 9..
 *
 * <설명>
 * current_list와 add_list를 만드는 클래스
 */

// TODO : 준형오빠가 주는 JSON형태에 맞춰서 다시 JSON 파싱하기

public class MakeCurrentList extends AsyncTask<Void, Void, Void> {

    static final String TAG = "MakeCurrentList";

    static final String TAG_ALL = "all";
    static final String TAG_CURRENT = "current";

    static final String TAG_EID = "eid";    // 운동 고유 아이디
    static final String TAG_CID = "cid";    // 사용자 아이디
    static final String TAG_CPW = "cpw";
    static final String TAG_OTYPE = "otype";
    static final String TAG_OPART = "opart";
    static final String TAG_ETYPE = "etype";
    static final String TAG_EPART = "epart";
    static final String TAG_OOPTION1 = "ooption1";
    static final String TAG_OOPTION2 = "ooption2";
    static final String TAG_EOPTION1 = "eoption1";
    static final String TAG_EOPTION2 = "eoption2";
    static final String TAG_ENAME = "ename";
    static final String TAG_EHAN = "ehan";
    static final String TAG_COURSE_ORDER = "corder";

    //static String URL = "http://192.168.0.10:8080/sgen_test/get_all_course.php"; // 굿 카페 (or 하하) ip
    //static String URL = "http://192.168.0.21:8080/sgen_test/get_all_course.php"; // 정보를 가져올 페이지 정보 (연구실 ip)
    //static String URL = "http://192.168.1.41:8080/sgen_test/get_all_course.php"; // WIFI 이름 : '엔젤리너스2층'
    //static String URL = "http://192.168.0.5:8080/sgen_test/get_all_course.php";

    static String URL = "http://ebsud89.iptime.org:8022/getAllCourse.php";

    Activity activity;

    SearchImageRID sRid;

    // 생성자
    public MakeCurrentList(Activity activity) {
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

        Log.e(TAG, URL);
        String jsonStr = sh.makeServiceCall(URL, ServiceHandler.POST);

        if (jsonStr != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray temp_JSONArr = null;

                // jsonArr 의 모든 Object 들을 loop
                for (int i = 0; i < jsonObject.length(); i++) {

                    switch (i) {
                        case 0:
                            // current_course
                            temp_JSONArr = jsonObject.getJSONArray(TAG_CURRENT);
                            get_courses(i, temp_JSONArr);
                            break;
                        case 1:
                            // add_course
                            temp_JSONArr = jsonObject.getJSONArray(TAG_ALL);
                            get_courses(i, temp_JSONArr);
                            break;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //Log.e(TAG, "해당 URL 로부터 아무런 정보를 얻을 수 없습니다.");
        }

        return null;
    }

    @Override
    protected void onPostExecute (Void result) {
        super.onPostExecute(result);

        CourseFragment.backgrd.setVisibility(View.GONE);
        CourseFragment.bar.setVisibility(View.GONE);

        CourseFragment.setOnClickListener();
        CourseFragment.addSetOnclickLister();
        CourseFragment.setRecommendList();

        return;

    }

    protected void get_courses(int index, JSONArray jsonArray) {
        JSONArray jsonArr = null;
        JSONObject temp_JSONobj = null;

        int temp_eid;
        String temp_cid;
        String temp_cpw;
        int temp_etype;
        int temp_epart;
        int temp_ooption1;
        int temp_ooption2;
        String ename;
        String ehan;
        int temp_corder;

        /**
         *
         static final String TAG_ENAME = "ename";
         static final String TAG_EHAN = "ehan";
         static final String TAG_COURSE_ORDER = "corder";
         */

        int temp_image_id = 0;

        try {

            Log.e(TAG, "jsonARR : " + jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                temp_JSONobj = jsonArray.getJSONObject(i);

                temp_eid = temp_JSONobj.getInt(TAG_EID);
                ename = temp_JSONobj.getString(TAG_ENAME);
                ehan = temp_JSONobj.getString(TAG_EHAN);

                if (index == 0) {
                    //current_array
                    temp_epart = temp_JSONobj.getInt(TAG_OPART);
                    temp_etype = temp_JSONobj.getInt(TAG_OTYPE);
                    temp_ooption1 = temp_JSONobj.getInt(TAG_OOPTION1);
                    temp_ooption2 = temp_JSONobj.getInt(TAG_OOPTION2);
                    CommonUtilities.ID = temp_JSONobj.getString(TAG_CID);
                    CommonUtilities.PASSWORD = temp_JSONobj.getString(TAG_CPW);
                    temp_corder = temp_JSONobj.getInt(TAG_COURSE_ORDER);
                    temp_image_id = sRid.getImageID(ename);
                    RowItem temp_row_item = new RowItem(temp_eid, CommonUtilities.ID, CommonUtilities.PASSWORD, temp_etype, temp_epart, temp_ooption1,
                            temp_ooption2, temp_image_id, ename, ehan, temp_corder);
                    CourseFragment.current_array_list.add(temp_row_item);
                }
                else {
                    //add_array
                    temp_corder = 0;
                    temp_epart = temp_JSONobj.getInt(TAG_EPART);
                    temp_etype = temp_JSONobj.getInt(TAG_ETYPE);
                    temp_ooption1 = temp_JSONobj.getInt(TAG_EOPTION1);
                    temp_ooption2 = temp_JSONobj.getInt(TAG_EOPTION2);
                    temp_image_id = sRid.getImageID(ename + "2");
                    RowItem temp_row_item = new RowItem(temp_eid, CommonUtilities.ID, CommonUtilities.PASSWORD, temp_etype, temp_epart, temp_ooption1,
                            temp_ooption2, temp_image_id, ename, ehan, temp_corder);
                    CourseFragment.add_array_list.add(temp_row_item);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}