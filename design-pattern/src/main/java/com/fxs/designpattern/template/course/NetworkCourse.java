package com.fxs.designpattern.template.course;

public abstract class NetworkCourse {

    public void createCourse() {

        //1.发布预习资料
        this.postPreResource();

        //2.制作PPT
        this.createPPT();

        //3.直播视频
        this.liveVideo();

        //4.提交课件笔记
        this.postNote();

        //5.提交源码
        this.postSourceCode();

        //是否需要布置作业
        if (needHomeword()) {
            checkHomework();
        }
    }

    final  void createPPT() {
        System.out.println("制作PPT");
    }

    final void postPreResource() {
        System.out.println("提交预习资料");
    }

    protected abstract void checkHomework();

    protected boolean needHomeword() {
        return false;
    }

    final void postSourceCode() {
        System.out.println("提交源码");
    }
    final void postNote() {
        System.out.println("提交课件笔记");
    }
    final void liveVideo() {
        System.out.println("视频直播");
    }
}
