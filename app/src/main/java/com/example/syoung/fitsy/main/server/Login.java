package com.example.syoung.fitsy.main.server;

public class Login {
    private String _id;
    private String Test;
    private String Child_Name;
    private String Child_Age;
    private String Parent_Phone;
    private String Beacon_Number;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getChild_Name() {
        return Child_Name;
    }

    public void setChild_Name(String child_Name) {
        Child_Name = child_Name;
    }

    public String getChild_Age() {
        return Child_Age;
    }

    public void setChild_Age(String child_Age) {
        Child_Age = child_Age;
    }

    public String getParent_Phone() {
        return Parent_Phone;
    }

    public void setParent_Phone(String parent_Phone) {
        Parent_Phone = parent_Phone;
    }

    public String getBeacon_Number() {
        return Beacon_Number;
    }

    public void setBeacon_Number(String beacon_Number) {
        Beacon_Number = beacon_Number;
    }

    public String getTest() {
        return Test;
    }

    public void setTest(String test) {
        this.Test = test;
    }
}
