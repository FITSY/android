package com.example.syoung.fitsy.main.data;

import com.example.syoung.fitsy.main.server.UserCourse;

import java.io.Serializable;
import java.util.List;

public class NowCourse implements Serializable{
    private UserCourse userCourse;
    private int ImageId;
    private boolean check; //했는지 안 했는지
    private int result; //무산소는 횟수, 유산소는 시간

    public UserCourse getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(UserCourse userCourse) {
        this.userCourse = userCourse;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
