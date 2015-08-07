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
    static final String TAG_COURSE = "course";

    static final String TAG_ID = "id";
    static final String TAG_CID = "cid";
    static final String TAG_CPW = "cpw";
    static final String TAG_OTYPE = "otype";
    static final String TAG_OPART = "opart";
    static final String TAG_OOPTION1 = "ooption1";
    static final String TAG_OOPTION2 = "ooption2";
    static final String TAG_EXERCISE_NAME = "exercise_name";

    //static String URL = "http://192.168.0.10:8080/sgen_test/history.php"; // 굿 카페 (or 하하) ip
    //static String URL = "http://192.168.0.21:8080/sgen_test/history.php"; // 정보를 가져올 페이지 정보 (연구실 ip)
    static String URL = "http://192.168.1.41:8080/sgen_test/history.php"; // WIFI 이름 : '엔젤리너스2층'
    //static String URL = "http://192.168.0.5:8080/sgen_test/history.php";

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
                JSONArray temp_JSONArr = null;

                // jsonArr 의 모든 Object 들을 loop
                for (int i = 0; i < jsonArr.length(); i++) {

                    temp_JSONArr = jsonArr.getJSONArray(i);

                    doJSONParsing(temp_JSONArr);

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

    protected void doJSONParsing (JSONArray jsonArray){
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
            for(int i = 0; i < jsonArray.length(); i++) {
                ArrayList<RowItem> temp_list = new ArrayList<RowItem>();
                String temp_date;

                JSONObject temp1 = jsonArray.getJSONObject(0);

                temp_date = temp1.getString(TAG_DATE);

                JSONObject temp2 = jsonArray.getJSONObject(1);
                JSONArray jsonArr = temp2.getJSONArray(TAG_COURSE);

                for (int j = 0; j < jsonArr.length(); j++) {
                    temp_JSONobj = jsonArr.getJSONObject(j);

                    temp_id = temp_JSONobj.getInt(TAG_ID);
                    temp_cid = temp_JSONobj.getString(TAG_CID);
                    temp_cpw = temp_JSONobj.getString(TAG_CPW);
                    temp_opart = temp_JSONobj.getInt(TAG_OPART);
                    temp_otype = temp_JSONobj.getInt(TAG_OTYPE);
                    temp_ooption1 = temp_JSONobj.getInt(TAG_OOPTION1);
                    temp_ooption2 = temp_JSONobj.getInt(TAG_OOPTION2);
                    exercise_name = temp_JSONobj.getString(TAG_EXERCISE_NAME);

                    temp_image_id = sRid.getImageID(exercise_name);

                    //public RowItem(int eid, String cid, String cpw, int etype, int epart,  int ooption1, int ooption2, int imageId, String ename, String ehan, int corder)

                    // TODO : RowItem 형태 고쳐야 함
                    /*    RowItem temp_row_item = new RowItem(temp_id, temp_cid, temp_cpw, temp_otype,
                            temp_opart, temp_ooption1, temp_ooption2, temp_image_id, exercise_name);
                    temp_list.add(temp_row_item);*/


                }

                HistoryRowItem temp_history_row_item = new HistoryRowItem(temp_date, temp_list);
                HistoryFragment.history_array_list.add(temp_history_row_item);

                i++;

            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

}
