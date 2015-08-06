package com.example.syoung.fitsy.statistics;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by sub_HeeJin on 2015-08-01.
 */
public class UserData {
    String cbmi;
    String cfat;
    String cmuscle;

    public String getCbmi() {
        return cbmi;
    }

    public void setCbmi(String cbmi) {
        this.cbmi = cbmi;
    }

    public String getCfat() {
        return cfat;
    }

    public void setCfat(String cfat) {
        this.cfat = cfat;
    }

    public String getCmuscle() {
        return cmuscle;
    }

    public void setCmuscle(String cmuscle) {
        this.cmuscle = cmuscle;
    }
}


/*
interface CallData {

    @GET("http://ebsud89.iptime.org:8022/getCustomerInfo.php")
    List getcbmi(@Named("cbmi") String cbmi);

    @GET("http://ebsud89.iptime.org:8022/getCustomerInfo.php")
    List getcfat(@Named("cfat") String cfat);

    @GET("http://ebsud89.iptime.org:8022/getCustomerInfo.php")
    List getcmuscle(@Named("cmuscle") String cmuscle);

}

*/