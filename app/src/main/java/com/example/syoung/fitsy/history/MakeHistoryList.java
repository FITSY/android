package com.example.syoung.fitsy.history;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.syoung.fitsy.common.RowItem;
import com.example.syoung.fitsy.common.SearchImageRID;
import com.example.syoung.fitsy.common.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by HyunJoo on 2015. 8. 3..
 */
public class MakeHistoryList extends AsyncTask<Void, Void, Void> {

    static final String TAG = "MakeHistoryList";

    static final String TAG_DATE = "date";
    static final String TAG_COURSE = "courses";

    static final String TAG_EID = "eid";    // 운동 고유 아이디
    static final String TAG_CID = "cid";    // 사용자 아이디
    static final String TAG_CPW = "cpw";
    static final String TAG_ODID = "odid";
    static final String TAG_OID = "oid";
    static final String TAG_OTYPE = "otype";
    static final String TAG_OPART = "opart";
    static final String TAG_OOPTION1 = "ooption1";
    static final String TAG_OOPTION2 = "ooption2";
    static final String TAG_ENAME = "ename";
    static final String TAG_EHAN = "ehan";
    static final String TAG_COURSE_ORDER = "corder";

    /**
     * "cid": "ebsud89",
     "cpw": null,
     "oid": "1",
     "odid": "04526E52863680",
     "otype": "1",
     "opart": "3",
     "ooption1": "10",
     "ooption2": "0",
     "ehan": "레그프레스",
     "eid": "1",
     "ename": "leg_press",
     "corder": "1",
     "date": "2015.07.01"
     */
    // cpw 필요, odid 잘못 보냄, oname은 무엇?

    //static String URL = "http://192.168.0.10:8080/sgen_test/history.php"; // 굿 카페 (or 하하) ip
    //static String URL = "http://192.168.0.21:8080/sgen_test/history.php"; // 정보를 가져올 페이지 정보 (연구실 ip)
    //static String URL = "http://192.168.1.41:8080/sgen_test/history.php"; // WIFI 이름 : '엔젤리너스2층'
    //static String URL = "http://192.168.0.5:8080/sgen_test/history.php";

    static String URL = "http://ebsud89.iptime.org:8022/getAllCourseHistory.php";

    Activity activity;

    SearchImageRID sRid;

    // 생성자
    public MakeHistoryList(Activity activity) {
        this.activity = activity;
        sRid = new SearchImageRID(this.activity);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        HistoryFragment.backgrd.setVisibility(View.VISIBLE);
        HistoryFragment.bar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // Service Handler Instance 생성
        ServiceHandler sh = new ServiceHandler();

        Log.e(TAG, URL);
        String jsonStr = sh.makeServiceCall(URL, ServiceHandler.POST);

        if (jsonStr != null) {
            try {
                JSONArray jsonArr = new JSONArray(jsonStr);
                JSONObject temp_JSONobj = null;

                // jsonArr 의 모든 Object 들을 loop
                for (int i = 0; i < jsonArr.length(); i++) {

                    temp_JSONobj = jsonArr.getJSONObject(i);

                    doJSONParsing(temp_JSONobj);

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

        HistoryFragment.backgrd.setVisibility(View.GONE);
        HistoryFragment.bar.setVisibility(View.GONE);

        HistoryFragment.setOnClickListener();

        return;

    }

    protected void doJSONParsing (JSONObject jsonObject){
        JSONObject temp_JSONobj = null;

        int temp_eid;
        String temp_cid;
        String temp_cpw;
        int temp_otype;
        int temp_opart;
        int temp_ooption1;
        int temp_ooption2;
        String ename;
        String ehan;
        int temp_corder;
        String temp_odid;
        String temp_date;

        int temp_image_id = 0;

        try {
            for(int i = 0; i < jsonObject.length(); i++) {
                ArrayList<RowItem> temp_list = new ArrayList<RowItem>();

                temp_date = jsonObject.getString(TAG_DATE);
                JSONArray temp_jsonArr = jsonObject.getJSONArray(TAG_COURSE);

                for (int j = 0; j < temp_jsonArr.length(); j++) {
                    temp_JSONobj = temp_jsonArr.getJSONObject(j);

                    temp_eid = temp_JSONobj.getInt(TAG_EID);
                    temp_cid = temp_JSONobj.getString(TAG_CID);
                    temp_cpw = temp_JSONobj.getString(TAG_CPW);
                    temp_opart = temp_JSONobj.getInt(TAG_OPART);
                    temp_otype = temp_JSONobj.getInt(TAG_OTYPE);
                    temp_ooption1 = temp_JSONobj.getInt(TAG_OOPTION1);
                    temp_ooption2 = temp_JSONobj.getInt(TAG_OOPTION2);
                    ename = temp_JSONobj.getString(TAG_ENAME);
                    ehan = temp_JSONobj.getString(TAG_EHAN);
                    temp_corder = temp_JSONobj.getInt(TAG_COURSE_ORDER);
                    temp_odid = temp_JSONobj.getString(TAG_ODID);

                    temp_image_id = sRid.getImageID(ename);

                    //public RowItem(int eid, String cid, String cpw, int etype, int epart,
                    // int ooption1, int ooption2, int imageId, String ename, String ehan, int corder)

                    // TODO : RowItem 형태 고쳐야 함
                     RowItem temp_row_item = new RowItem(temp_odid, temp_eid, temp_cid, temp_cpw, temp_otype,
                            temp_opart, temp_ooption1, temp_ooption2, temp_image_id, ename, ehan, temp_corder);
                    temp_list.add(temp_row_item);

                }

                HistoryRowItem temp_history_row_item = new HistoryRowItem(temp_date, temp_list);
                HistoryFragment.history_array_list.add(temp_history_row_item);

                i += 2;

            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

}
