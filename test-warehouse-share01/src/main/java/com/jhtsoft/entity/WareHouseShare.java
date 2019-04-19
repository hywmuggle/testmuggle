package com.jhtsoft.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: WareHouseShare
 * @Describe: 仓分享
 * @Author: houyingwei
 * @Date: 2019/4/11
 **/
@Entity
@Table(
        name = "tb_warehouseshare",
        catalog = "test"
)
public class WareHouseShare {

    private Integer id;
    private String sharecode;
    private String cmpycode;
    private String qydm;
    private String license;
    private String keyword;
    private Short suppermission;
    private Short buyneedcheck;
    private String industrycode;
    private String scalecode;
    private Date estdate;
    private String regcapital;
    private String regaddress;
    private String busscope;
    private String legrepresentative;
    private String contactname;
    private String contactphone;
    private Short audstatus;
    private String feedback;
    private Date feedate;
    private Short readprotocol;
    private String showorder;
    private Date credate;
    private Date upddate;
    private String operuname;
    private String isdelete;
    private String uid;
    private Short status;

    public WareHouseShare() {
    }

    public WareHouseShare(String sharecode, String cmpycode, String qydm, String license, String keyword, Short suppermission,
                          Short buyneedcheck, String industrycode, String scalecode, Date estdate, String regcapital,
                          String regaddress, String busscope, String legrepresentative, String contactname, String contactphone,
                          Short audstatus, String feedback, Date feedate, Short readprotocol, String showorder, Date credate,
                          Date upddate, String operuname, String isdelete, String uid, Short status) {
        this.sharecode = sharecode;
        this.cmpycode = cmpycode;
        this.qydm = qydm;
        this.license = license;
        this.keyword = keyword;
        this.suppermission = suppermission;
        this.buyneedcheck = buyneedcheck;
        this.industrycode = industrycode;
        this.scalecode = scalecode;
        this.estdate = estdate;
        this.regcapital = regcapital;
        this.regaddress = regaddress;
        this.busscope = busscope;
        this.legrepresentative = legrepresentative;
        this.contactname = contactname;
        this.contactphone = contactphone;
        this.audstatus = audstatus;
        this.feedback = feedback;
        this.feedate = feedate;
        this.readprotocol = readprotocol;
        this.showorder = showorder;
        this.credate = credate;
        this.upddate = upddate;
        this.operuname = operuname;
        this.isdelete = isdelete;
        this.uid = uid;
        this.status = status;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            unique = true,
            nullable = false
    )
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(
            name = "sharecode",
            nullable = false,
            length = 36
    )
    public String getSharecode() {
        return sharecode;
    }

    public void setSharecode(String sharecode) {
        this.sharecode = sharecode;
    }

    @Column(
            name = "cmpycode",
            nullable = false,
            length = 36
    )
    public String getCmpycode() {
        return cmpycode;
    }

    public void setCmpycode(String cmpycode) {
        this.cmpycode = cmpycode;
    }

    @Column(
            name = "qydm",
            nullable = false,
            length = 20
    )
    public String getQydm() {
        return qydm;
    }

    public void setQydm(String qydm) {
        this.qydm = qydm;
    }

    @Column(
            name = "license",
            nullable = false,
            length = 100
    )
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Column(
            name = "keyword",
            nullable = false,
            length = 100
    )
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Column(
            name = "suppermission"
    )
    public Short getSuppermission() {
        return suppermission;
    }

    public void setSuppermission(Short suppermission) {
        this.suppermission = suppermission;
    }

    @Column(
            name = "buyneedcheck"
    )
    public Short getBuyneedcheck() {
        return buyneedcheck;
    }

    public void setBuyneedcheck(Short buyneedcheck) {
        this.buyneedcheck = buyneedcheck;
    }

    @Column(
            name = "industrycode",
            nullable = false,
            length = 36
    )
    public String getIndustrycode() {
        return industrycode;
    }

    public void setIndustrycode(String industrycode) {
        this.industrycode = industrycode;
    }

    @Column(
            name = "scalecode",
            nullable = false,
            length = 36
    )
    public String getScalecode() {
        return scalecode;
    }

    public void setScalecode(String scalecode) {
        this.scalecode = scalecode;
    }

    @Temporal(TemporalType.DATE)
    @Column(
            name = "estdate"
    )
    public Date getEstdate() {
        return estdate;
    }

    public void setEstdate(Date estdate) {
        this.estdate = estdate;
    }

    @Column(
            name = "regcapital",
            nullable = false,
            length = 30
    )
    public String getRegcapital() {
        return regcapital;
    }

    public void setRegcapital(String regcapital) {
        this.regcapital = regcapital;
    }

    @Column(
            name = "regaddress",
            nullable = false,
            length = 100
    )
    public String getRegaddress() {
        return regaddress;
    }

    public void setRegaddress(String regaddress) {
        this.regaddress = regaddress;
    }

    @Column(
            name = "busscope",
            nullable = false,
            length = 200
    )
    public String getBusscope() {
        return busscope;
    }

    public void setBusscope(String busscope) {
        this.busscope = busscope;
    }

    @Column(
            name = "legrepresentative",
            nullable = false,
            length = 20
    )
    public String getLegrepresentative() {
        return legrepresentative;
    }

    public void setLegrepresentative(String legrepresentative) {
        this.legrepresentative = legrepresentative;
    }

    @Column(
            name = "contactname",
            nullable = false,
            length = 20
    )
    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    @Column(
            name = "contactphone",
            nullable = false,
            length = 20
    )
    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    @Column(
            name = "audstatus"
    )
    public Short getAudstatus() {
        return audstatus;
    }

    public void setAudstatus(Short audstatus) {
        this.audstatus = audstatus;
    }

    @Column(
            name = "feedback",
            nullable = false,
            length = 100
    )
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Temporal(TemporalType.DATE)
    @Column(
            name = "feedate"
    )
    public Date getFeedate() {
        return feedate;
    }

    public void setFeedate(Date feedate) {
        this.feedate = feedate;
    }

    @Column(
            name = "readprotocol"
    )
    public Short getReadprotocol() {
        return readprotocol;
    }

    public void setReadprotocol(Short readprotocol) {
        this.readprotocol = readprotocol;
    }

    @Column(
            name = "showorder",
            nullable = false,
            length = 36
    )
    public String getShoworder() {
        return showorder;
    }

    public void setShoworder(String showorder) {
        this.showorder = showorder;
    }

    @Temporal(TemporalType.DATE)
    @Column(
            name = "credate"
    )
    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    @Temporal(TemporalType.DATE)
    @Column(
            name = "upddate"
    )
    public Date getUpddate() {
        return upddate;
    }

    public void setUpddate(Date upddate) {
        this.upddate = upddate;
    }

    @Column(
            name = "operuname",
            nullable = false,
            length = 20
    )
    public String getOperuname() {
        return operuname;
    }

    public void setOperuname(String operuname) {
        this.operuname = operuname;
    }

    @Column(
            name = "isdelete",
            nullable = false,
            length = 36
    )
    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    @Column(
            name = "uid",
            nullable = false,
            length = 36
    )
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Column(
            name = "status"
    )
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
