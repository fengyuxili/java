package com.fxs.designpattern.template.course;

public class BigDataCourse extends NetworkCourse {

    private  boolean needHomeword;

    public BigDataCourse(boolean needHomeword) {
        this.needHomeword = needHomeword;
    }

    @Override
    protected void checkHomework() {
        System.out.println("检查大数据课程作业");
    }

    @Override
    protected boolean needHomeword() {
        return needHomeword;
    }
}
