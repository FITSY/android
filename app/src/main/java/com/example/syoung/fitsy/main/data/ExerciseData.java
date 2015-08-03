package com.example.syoung.fitsy.main.data;

import java.io.Serializable;
import java.util.List;

public class ExerciseData implements Serializable{
    private List<NowCourse> nowCourseList;
    private String tagId;

    public List<NowCourse> getNowCourseList() {
        return nowCourseList;
    }

    public void setNowCourseList(List<NowCourse> nowCourseList) {
        this.nowCourseList = nowCourseList;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
