package com.jhtsoft.dto;

/**
 * @ClassName: ScaleDTO
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/11
 **/
public class ScaleDTO {

    private String scalecode;
    private String scalename;
    private String industrycode;
    private Short status;
    private Integer pageNumber;
    private Integer pageSize;
    private String cmpycode;

    public String getScalecode() {
        return scalecode;
    }

    public void setScalecode(String scalecode) {
        this.scalecode = scalecode;
    }

    public String getScalename() {
        return scalename;
    }

    public void setScalename(String scalename) {
        this.scalename = scalename;
    }

    public String getIndustrycode() {
        return industrycode;
    }

    public void setIndustrycode(String industrycode) {
        this.industrycode = industrycode;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

    public String getCmpycode() {
        return cmpycode;
    }

    public void setCmpycode(String cmpycode) {
        this.cmpycode = cmpycode;
    }

    @Override
    public String toString() {
        return "ScaleDTO{" +
                "scalecode='" + scalecode + '\'' +
                ", scalename='" + scalename + '\'' +
                ", industrycode='" + industrycode + '\'' +
                ", status=" + status +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", cmpycode='" + cmpycode + '\'' +
                '}';
    }
    public String toStringDetails() {
        return "ScaleDTO{" +
                "scalecode='" + scalecode + '\'' +
                ", cmpycode='" + cmpycode + '\'' +
                '}';
    }
    public String toStringOperationStatus() {
        return "ScaleDTO{" +
                "scalecode='" + scalecode + '\'' +
                ", status=" + status +
                '}';
    }
    public String toStringrepeatCheck() {
        return "ScaleDTO{" +
                "scalecode='" + scalecode + '\'' +
                ", scalename='" + scalename + '\'' +
                ", industrycode='" + industrycode + '\'' +
                '}';
    }
}
