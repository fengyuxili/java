package com.fxs.designpattern.template.course;

public class NetworkCourseTest {

    public static void main(String[] args) {

        System.out.println("----JAVA架构师课程-----");
        NetworkCourse javaCourse = new JavaCourse(true);
        javaCourse.createCourse();

        System.out.println("----大数据课程-----");
        NetworkCourse bigDataCourse = new BigDataCourse(true);
        bigDataCourse.createCourse();
    }
}
