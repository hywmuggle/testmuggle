package com.jhtsoft.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: Industry
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
@Entity
@Table(
        name = "tb_industry",
        catalog = "test"
)
public class Industry {

    private Integer id;
    private String industrycode;
    private String industryname;
    private Integer status;
    private Date credate;
    private Date upddate;
    private String operuname;
    private String isdelete;
    private String uid;

    public Industry() {
    }

    public Industry(String industrycode, String industryname, Integer status, Date credate, Date upddate, String operuname, String isdelete, String uid) {
        this.industrycode = industrycode;
        this.industryname = industryname;
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
            name = "industryname",
            nullable = false,
            length = 30
    )
    public String getIndustryname() {
        return industryname;
    }

    public void setIndustryname(String industryname) {
        this.industryname = industryname;
    }

    @Column(
            name = "status"
    )
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
