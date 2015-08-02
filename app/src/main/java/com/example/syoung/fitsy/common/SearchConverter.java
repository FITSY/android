package com.example.syoung.fitsy.common;

import java.util.ArrayList;

/**
 * Created by HyunJoo on 2015. 8. 2..
 */
// 사용자가 입력한 검색어들을 적젏히 변환해 주는 클래스
public class SearchConverter {

    ArrayList<RowItem> data;

    public SearchConverter (){

    }

    public void setArrayList (ArrayList<RowItem> data){
        this.data = data;
    }

    public ArrayList<RowItem> getSearchResult(String searchKeyWord){
        ArrayList<RowItem> temp = new ArrayList<RowItem>();

        // TODO : 스트링이 들어오면 그거를 매치시켜서 결과값 도출

        return temp;
    }

}
