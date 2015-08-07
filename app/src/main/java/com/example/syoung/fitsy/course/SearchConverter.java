package com.example.syoung.fitsy.course;

import android.app.Activity;

import com.example.syoung.fitsy.common.RowItem;
import com.example.syoung.fitsy.common.SearchImageRID;

import java.util.ArrayList;

/**
 * Created by HyunJoo on 2015. 8. 2..
 */
// 사용자가 입력한 검색어들을 적젏히 변환해 주는 클래스
public class SearchConverter {
    /** <참고>
     * opart : 1 - 복부, 2 - 팔, 3 - 다리
     * otype : 1 - 유산소, 2 - 무산소
     * 날짜는 2015.01.22 형식의 String으로 들온다
     */

    private final int BELLY = 1;
    private final int ARM = 2;
    private final int LEG = 3;

    private final int AEROBIC = 1;
    private final int ANAEROBIC = 2;

    SearchImageRID searchImageRID;
    ArrayList<RowItem> data;

    public SearchConverter (Activity activity){
        searchImageRID = new SearchImageRID(activity);
    }

    public void setArrayList (ArrayList<RowItem> data){
        this.data = data;
    }

    public ArrayList<RowItem> getSearchResult(String searchKeyWord){
        ArrayList<RowItem> temp = new ArrayList<RowItem>();

        switch (searchKeyWord){
            case "전체":
                return data;
            case "복부":
                temp = doSearchPart(BELLY);
                return temp;
            case "팔":
                temp = doSearchPart(ARM);
                return temp;
            case "다리":
                temp = doSearchPart(LEG);
                return temp;
            case "레그익스텐션":
                temp = doSearchExercise("leg_extension");
                return temp;
            case "런닝머신":
                temp = doSearchExercise("running");
                return temp;
            case "레그 컬":
                temp = doSearchExercise("leg_curl");
                return temp;
            case "펙 덱 플라이":
                temp = doSearchExercise("pec_deck_flyes");
                return temp;
            case "레그프레스":
                temp = doSearchExercise("leg_press");
                return temp;
            case "사이클":
                temp = doSearchExercise("cycle");
                return temp;
            case "렛 풀 다운":
                temp = doSearchExercise("let_pull_down");
                return temp;
            case "숄더 프레스":
                temp = doSearchExercise("shoulder_press");
                return temp;
        }

        return null;
    }

    private ArrayList<RowItem> doSearchPart(int part){
        ArrayList<RowItem> temp_array = new ArrayList<RowItem>();

        for(int i = 0; i < data.size(); i++){
            if(data.get(i).getEpart() == part){
                temp_array.add(data.get(i));
            }
        }

        return temp_array;
    }

    private ArrayList<RowItem> doSearchExercise(String exercise_name){
        ArrayList<RowItem> temp_array = new ArrayList<RowItem>();

        for(int i = 0; i < data.size(); i++){
            if(data.get(i).getEname().endsWith(exercise_name)){
                temp_array.add(data.get(i));
            }
        }

        return temp_array;
    }

}