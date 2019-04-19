package com.jhtsoft.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: PageBean
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
public class PageBean {
    private int curpage = 1;
    private int pagesize = 10;
    private int totalpage;
    private int totalNum;
    private int nextpage;
    private int sorttype;
    private int isnewcomp = 1;
    private String opertype;
    private String sortfeild = "id";

    public PageBean() {
    }

    public PageBean(JSONObject pageparam) {
        if (pageparam.containsKey("pagesize")) {
            this.pagesize = pageparam.getInteger("pagesize").intValue();
        }

        if (pageparam.containsKey("currentpage")) {
            this.curpage = pageparam.getInteger("currentpage").intValue();
        }

        if (pageparam.containsKey("isnewcomp")) {
            this.isnewcomp = pageparam.getInteger("isnewcomp").intValue();
        }

        if (pageparam.containsKey("sortparam")) {
            this.sortfeild = pageparam.getString("sortparam");
        }

        if (pageparam.containsKey("sorttype")) {
            this.sorttype = pageparam.getInteger("sorttype").intValue();
        }

    }

    public void updatePage() {
        this.totalpage = this.totalNum / this.pagesize + (this.totalNum % this.pagesize == 0 ? 0 : 1);
        if (this.curpage > this.totalpage && this.isnewcomp == 1) {
            this.curpage = this.totalpage;
        }

        this.setNextpage();
        if (this.opertype != null) {
            String var1 = this.opertype;
            byte var2 = -1;
            switch(var1.hashCode()) {
                case 111267:
                    if (var1.equals("pre")) {
                        var2 = 0;
                    }
                    break;
                case 3377907:
                    if (var1.equals("next")) {
                        var2 = 1;
                    }
                    break;
                case 97436022:
                    if (var1.equals("final")) {
                        var2 = 3;
                    }
                    break;
                case 97440432:
                    if (var1.equals("first")) {
                        var2 = 2;
                    }
            }

            switch(var2) {
                case 0:
                    if (this.curpage > 1) {
                        --this.curpage;
                    }
                    break;
                case 1:
                    this.curpage = this.nextpage;
                    break;
                case 2:
                    this.curpage = 1;
                    break;
                case 3:
                    this.curpage = this.totalpage;
            }
        }

        this.setNextpage();
    }

    public int getCurpage() {
        return this.curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getPagesize() {
        return this.pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getOpertype() {
        return this.opertype;
    }

    public void setOpertype(String opertype) {
        this.opertype = opertype;
    }

    public int getTotalpage() {
        return this.totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getNextpage() {
        return this.nextpage;
    }

    public void setNextpage() {
        if (this.totalpage > this.curpage) {
            this.nextpage = this.curpage + 1;
        } else {
            this.nextpage = this.curpage;
        }

    }

    public String getSortfeild() {
        return this.sortfeild;
    }

    public void setSortfeild(String sortfeild) {
        this.sortfeild = sortfeild;
    }

    public int getSorttype() {
        return this.sorttype;
    }

    public void setSorttype(int sorttype) {
        this.sorttype = sorttype;
    }

    public int getIsnewcomp() {
        return this.isnewcomp;
    }

    public void setIsnewcomp(int isnewcomp) {
        this.isnewcomp = isnewcomp;
    }

    public String toString() {
        return "PageBean [curpage=" + this.curpage + ", pagesize=" + this.pagesize + ", totalpage=" + this.totalpage + ", totalNum=" + this.totalNum + ", nextpage=" + this.nextpage + ", sorttype=" + this.sorttype + ", isnewcomp=" + this.isnewcomp + ", opertype=" + this.opertype + ", sortfeild=" + this.sortfeild + "]";
    }
}
