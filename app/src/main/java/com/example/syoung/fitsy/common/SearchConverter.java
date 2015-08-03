package com.example.syoung.fitsy.common;

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

    ArrayList<RowItem> data;

    public SearchConverter (){

    }

    public void setArrayList (ArrayList<RowItem> data){
        this.data = data;
    }

    public ArrayList<RowItem> getSearchResult(String searchKeyWord){
        ArrayList<RowItem> temp = new ArrayList<RowItem>();

        // TODO : 스트링이 들어오면 그거를 매치시켜서 결과값 도출
        switch (searchKeyWord){
            case "팔":
                temp = 
                break;
        }

        return temp;
    }

    private ArrayList<RowItem> doSearchPart(int part){
        ArrayList<RowItem> temp = new ArrayList<RowItem>();

        return temp;
    }

}
