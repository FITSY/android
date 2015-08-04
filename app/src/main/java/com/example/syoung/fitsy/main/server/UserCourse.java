package com.example.syoung.fitsy.main.server;

import java.io.Serializable;
import java.util.List;

public class UserCourse implements Serializable{

    private String id; //key
    private String cid; //?
    private String oid; //운동기구 id
    private String onmae; //운동기구 이름?
    private String odid; //nfc 태그 id
    private int otype; //1 : 유산소, 2 : 무산소
    private int opart; //1 : 복부, 2: 팔, 3. 다리
    private int ooption1; //무산소 : 무게, 운동시간 : 속도
    private int ooption2; //무산소 : 횟수, 운동시간 : 시간
    private String ename; //운동이름
    private List<String> eintro; //운동 방법
    private int corder; //코스내 있는 운동들에 대한 순서

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOnmae() {
        return onmae;
    }

    public void setOnmae(String onmae) {
        this.onmae = onmae;
    }

    public String getOdid() {
        return odid;
    }

    public void setOdid(String odid) {
        this.odid = odid;
    }

    public int getOtype() {
        return otype;
    }

    public void setOtype(int otype) {
        this.otype = otype;
    }

    public int getOpart() {
        return opart;
    }

    public void setOpart(int opart) {
        this.opart = opart;
    }

    public int getOoption1() {
        return ooption1;
    }

    public void setOoption1(int ooption1) {
        this.ooption1 = ooption1;
    }

    public int getOoption2() {
        return ooption2;
    }

    public void setOoption2(int ooption2) {
        this.ooption2 = ooption2;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public List<String> getEintro() {
        return eintro;
    }

    public void setEintro(List<String> eintro) {
        this.eintro = eintro;
    }

    public int getCorder() {
        return corder;
    }

    public void setCorder(int corder) {
        this.corder = corder;
    }
}
