package com.jhtsoft.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: Scale
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/11
 **/
@Entity
@Table(
        name = "tb_scale",
        catalog = "test"
)
public class Scale {

    private Integer id;
    private String scalecode;
    private String scalename;
    private String industrycode;
    private Short status;
    private Date credate;
    private Date upddate;
    private String operuname;
    private String isdelete;
    private String uid;

    public Scale() {
    }

    public Scale(String scalecode, String scalename, String industrycode, Short status, Date credate, Date upddate, String operuname, String isdelete, String uid) {
        this.scalecode = scalecode;
        this.scalename = scalename;
        this.industrycode = industrycode;
        this.status = status;
        this.credate = credate;
        this.upddate = upddate;
        this.operuname = operuname;
        this.isdelete = isdelete;
        this.uid = uid;
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

    @Column(
            name = "scalename",
            nullable = false,
            length = 36
    )
    public String getScalename() {
        return scalename;
    }

    public void setScalename(String scalename) {
        this.scalename = scalename;
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
            name = "status"
    )
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Temporal(TemporalType.DATE)
    @Column(
            name = "credate",
            length = 10
    )
    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    @Temporal(TemporalType.DATE)
    @Column(
            name = "upddate",
            length = 10
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
}
