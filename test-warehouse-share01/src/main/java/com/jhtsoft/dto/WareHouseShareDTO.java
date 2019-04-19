package com.jhtsoft.dto;

import java.util.Date;

/**
 * @ClassName: WareHouseShareDTO
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/12
 **/
public class WareHouseShareDTO {


    private String cmpycode;
    private Short audstatus;
    private String keyword;
    private Short suppermission;
    private Short buyneedcheck;
    private Date beginDate;
    private Date endDate;
    private Integer pageNumber;
    private Integer pageSize;

    private String qydm;
    private String license;
    private Date estdate;
    private String regcapital;
    private String regaddress;
    private String busscope;
    private String legrepresentative;
    private String contactname;
    private String contactphone;

    private String sharecode;
    private String industrycode;
    private String scalecode;

    private String feedback;

    private Short status;


    public String getCmpycode() {
        return cmpycode;
    }

    public void setCmpycode(String cmpycode) {
        this.cmpycode = cmpycode;
    }

    public Short getAudstatus() {
        return audstatus;
    }

    public void setAudstatus(Short audstatus) {
        this.audstatus = audstatus;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Short getSuppermission() {
        return suppermission;
    }

    public void setSuppermission(Short suppermission) {
        this.suppermission = suppermission;
    }

    public Short getBuyneedcheck() {
        return buyneedcheck;
    }

    public void setBuyneedcheck(Short buyneedcheck) {
        this.buyneedcheck = buyneedcheck;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQydm() {
        return qydm;
    }

    public void setQydm(String qydm) {
        this.qydm = qydm;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Date getEstdate() {
        return estdate;
    }

    public void setEstdate(Date estdate) {
        this.estdate = estdate;
    }

    public String getRegcapital() {
        return regcapital;
    }

    public void setRegcapital(String regcapital) {
        this.regcapital = regcapital;
    }

    public String getRegaddress() {
        return regaddress;
    }

    public void setRegaddress(String regaddress) {
        this.regaddress = regaddress;
    }

    public String getBusscope() {
        return busscope;
    }

    public void setBusscope(String busscope) {
        this.busscope = busscope;
    }

    public String getLegrepresentative() {
        return legrepresentative;
    }

    public void setLegrepresentative(String legrepresentative) {
        this.legrepresentative = legrepresentative;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getSharecode() {
        return sharecode;
    }

    public void setSharecode(String sharecode) {
        this.sharecode = sharecode;
    }

    public String getIndustrycode() {
        return industrycode;
    }

    public void setIndustrycode(String industrycode) {
        this.industrycode = industrycode;
    }

    public String getScalecode() {
        return scalecode;
    }

    public void setScalecode(String scalecode) {
        this.scalecode = scalecode;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String toStringGetList() {
        return "WareHouseShareDTO{" +
                "cmpycode='" + cmpycode + '\'' +
                ", audstatus=" + audstatus +
                ", keyword='" + keyword + '\'' +
                ", suppermission=" + suppermission +
                ", buyneedcheck=" + buyneedcheck +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }

    public String toStringAdd() {
        return "WareHouseShareDTO{" +
                "cmpycode='" + cmpycode + '\'' +
                ", keyword='" + keyword + '\'' +
                ", suppermission=" + suppermission +
                ", buyneedcheck=" + buyneedcheck +
                ", qydm='" + qydm + '\'' +
                ", license='" + license + '\'' +
                ", estdate=" + estdate +
                ", regcapital='" + regcapital + '\'' +
                ", regaddress='" + regaddress + '\'' +
                ", busscope='" + busscope + '\'' +
                ", legrepresentative='" + legrepresentative + '\'' +
                ", contactname='" + contactname + '\'' +
                ", contactphone='" + contactphone + '\'' +
                '}';
    }

    public String toStringUpdate() {
        return "WareHouseShareDTO{" +
                "keyword='" + keyword + '\'' +
                ", suppermission=" + suppermission +
                ", buyneedcheck=" + buyneedcheck +
                ", license='" + license + '\'' +
                ", sharecode='" + sharecode + '\'' +
                ", industrycode='" + industrycode + '\'' +
                ", scalecode='" + scalecode + '\'' +
                '}';
    }

    public String toStringAuditApply() {
        return "WareHouseShareDTO{" +
                "audstatus=" + audstatus +
                ", sharecode='" + sharecode + '\'' +
                ", industrycode='" + industrycode + '\'' +
                ", scalecode='" + scalecode + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }

    public String toStringOperationStatus() {
        return "WareHouseShareDTO{" +
                "sharecode='" + sharecode + '\'' +
                ", status=" + status +
                '}';
    }

    public String toStringGetDetails() {
        return "WareHouseShareDTO{" +
                "sharecode='" + sharecode + '\'' +
                '}';
    }
}
