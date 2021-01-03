package com.fxs.designpattern.template.course;

public class JavaCourse extends NetworkCourse {
    private  boolean needHomeword;

    public JavaCourse(boolean needHomeword) {
        this.needHomeword = needHomeword;
    }

    @Override
    protected void checkHomework() {
        System.out.println("检查JAVA架构师课程作业");
    }

    @Override
    protected boolean needHomeword() {
        return needHomeword;
    }
}
