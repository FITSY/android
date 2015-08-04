package com.example.syoung.fitsy.main.server;

import java.io.Serializable;

public class UserCourse implements Serializable{

    private String id; //key
    private String cid; //
    private String oid; //
    private String onmae; //
    private String odid;
    private int otype;
    private int opart;
    private int ooption1; //무산소 :
    private int ooption2;
    private String ename;
    private String eintro;
    private int corder;

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

    public String getEintro() {
        return eintro;
    }

    public void setEintro(String eintro) {
        this.eintro = eintro;
    }

    public int getCorder() {
        return corder;
    }

    public void setCorder(int corder) {
        this.corder = corder;
    }
}
