package com.jhtsoft.dto;

/**
 * @ClassName: IndustryDTO
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
public class IndustryDTO {

    private String industryname;
    private Integer status;
    private String industrycode;
    private Integer pageNumber;
    private Integer pageSize;


    public String getIndustryname() {
        return industryname;
    }

    public void setIndustryname(String industryname) {
        this.industryname = industryname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIndustrycode() {
        return industrycode;
    }

    public void setIndustrycode(String industrycode) {
        this.industrycode = industrycode;
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

    @Override
    public String toString() {
        return "IndustryDTO{" +
                "industryname='" + industryname + '\'' +
                ", status=" + status +
                ", industrycode='" + industrycode + '\'' +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
