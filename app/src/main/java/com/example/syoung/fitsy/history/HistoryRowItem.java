package com.example.syoung.fitsy.history;

import com.example.syoung.fitsy.common.RowItem;

import java.util.ArrayList;

/**
 * Created by HyunJoo on 2015. 8. 5..
 */
public class HistoryRowItem {

    private String date;
    private ArrayList<RowItem> items;

    public HistoryRowItem (){

    }

    public HistoryRowItem (String date, ArrayList<RowItem> items){
        this.date = date;
        this.items = items;
    }

    public void setDate(String date) {this.date = date;}
    public String getDate() {return this.date;}

    public void setItems(ArrayList<RowItem> items) {this.items = items;}
    public ArrayList<RowItem> getItems() {return this.items;}

}
