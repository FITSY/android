package com.example.syoung.fitsy.common;

/**
 * Created by HyunJoo on 2015. 7. 29..
 */
public class RowItem {
    private int id;
    private String cid;
    private String cpw;
    private int otype;
    private int opart;
    private int ooption1;
    private int ooption2;
    private int imageId; // exercise_name으로부터 얻은 해당 이름과 매치되는 이미지의 리소스 아이디
    private String exerciseName;

    public RowItem(int id, String cid, String cpw, int otype, int opart,  int ooption1, int ooption2, int imageId, String exerciseName) {
        this.id = id;
        this.cid = cid;
        this.cpw = cpw;
        this.otype = otype;
        this.opart = opart;
        this.ooption1 = ooption1;
        this.ooption2 = ooption2;
        this.imageId = imageId;
        this.exerciseName = exerciseName;
    }

    public RowItem(int id, int ooption1, int ooption2, int imageId, String exerciseName) {
        this.id = id;
        this.ooption1 = ooption1;
        this.ooption2 = ooption2;
        this.imageId = imageId;
        this.exerciseName = exerciseName;
    }

    public RowItem(){

    }

    // 운동 순번
    public int getId() {return imageId;}
    public void setId(int id) {this.id = id; }

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
    public int getOtype() {
        return otype;
    }
    public void setOtype(int otype) {
        this.otype = otype;
    }

    // opart : 1 - 복부, 2 - 팔, 3 - 다리
    public int getOpart() {
        return opart;
    }
    public void setOpart(int opart) {
        this.opart = opart;
    }

    // ooption1 : 무게(무산소) / 운동시간(유산소)
    public int getOoption1() {
        return ooption1;
    }
    public void setOoption1(int ooption1) {
        this.ooption1 = ooption1;
    }

    // ooption2 : 횟수(무산소) / 속도(유산소)
    public int getOoption2() {
        return ooption2;
    }
    public void setOoption2(int ooption2) {
        this.ooption2 = ooption2;
    }

    // 운동 이름으로부터 얻은 해당 이미지의 아이디
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    // 운동 이름
    public String getExerciseName() {return exerciseName;}
    public void setExerciseName(String exerciseName) {this.exerciseName = exerciseName;}

}
