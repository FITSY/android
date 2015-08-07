package com.example.syoung.fitsy.common;

/**
 * Created by HyunJoo on 2015. 7. 29..
 */
public class RowItem {
    private int eid;
    private String cid;
    private String cpw;
    private int etype;
    private int epart;
    private int ooption1;
    private int ooption2;
    private int imageId; // exercise_name으로부터 얻은 해당 이름과 매치되는 이미지의 리소스 아이디
    private String ename;
    private String ehan;
    private int corder;

    //temp_eid, CommonUtilities.ID, CommonUtilities.PASSWORD, temp_etype, temp_epart, temp_ooption1, temp_ooption2, temp_image_id, ename, ehan, temp_corder

    public RowItem(int eid, String cid, String cpw, int etype, int epart,  int ooption1, int ooption2, int imageId, String ename, String ehan, int corder) {
        this.eid = eid;
        this.cid = cid;
        this.cpw = cpw;
        this.etype = etype;
        this.epart = epart;
        this.ooption1 = ooption1;
        this.ooption2 = ooption2;
        this.imageId = imageId;
        this.ename = ename;
        this.ehan = ehan;
        this.corder = corder;
    }

    /*public RowItem(int corder, int eid, int ooption1, int ooption2, int imageId, String ename, String ehan) {
        this.corder = corder;
        this.eid = eid;
        this.ooption1 = ooption1;
        this.ooption2 = ooption2;
        this.imageId = imageId;
        this.ename = ename;
    }*/

    public RowItem(){

    }
    // 운동 순번
    public int getEid() {return this.eid;}
    public void setEid(int eid) {this.eid = eid; }

    // 사용자 아이디
    public String getCid() {return cid;}
    public void setCid(String cid) {this.cid = cid;}

    // 사용자 비밀번호
    public String getCpw() {
        return cpw;
    }
    public void setCpw(String cpw) {
        this.cpw = cpw;
    }

    // otype :  1 - 유산소, 2 - 무산소
    public int getEtype() {
        return etype;
    }
    public void setEtype(int etype) {
        this.etype = etype;
    }

    // opart : 1 - 복부, 2 - 팔, 3 - 다리
    public int getEpart() {
        return this.epart;
    }
    public void setEpart(int epart) {
        this.epart = epart;
    }

    // ooption1 : 무게(무산소) / 운동시간(유산소)
    public int getOoption1() {
        return this.ooption1;
    }
    public void setOoption1(int ooption1) {
        this.ooption1 = ooption1;
    }

    // ooption2 : 횟수(무산소) / 속도(유산소)
    public int getOoption2() {
        return this.ooption2;
    }
    public void setOoption2(int ooption2) {
        this.ooption2 = ooption2;
    }

    // 운동 이름으로부터 얻은 해당 이미지의 아이디
    public int getImageId() {return imageId;}
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    // 운동 영어 이름
    public String getEname() {return this.ename;}
    public void setEname(String ename) {this.ename = ename;}

    // 운동 한글 이름
    public String getEhan() {return this.ehan;}
    public void setEhan (String ehan) {this.ehan = ehan;}

    // 운동 순서
    public int getCorder() {return this.corder;}
    public void setCorder(int corder) {
        this.corder = corder;
    }

}
