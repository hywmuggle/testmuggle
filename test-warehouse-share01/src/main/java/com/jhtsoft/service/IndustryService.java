package com.jhtsoft.service;

import com.jhtsoft.dto.IndustryDTO;
import com.jhtsoft.entity.Industry;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: Industry
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
public interface IndustryService {

    List<Industry> getList(IndustryDTO industryDTO);

    //行业名称重复校验
    boolean nameCanUse(IndustryDTO industryDTO);

    Map saveOrUpdate(IndustryDTO industryDTO);

    Map getDetails(String industrycode);

    Map updateStatus(IndustryDTO industryDTO);

    //行业信息是否存在
    boolean objectExist(IndustryDTO industryDTO);

    //行业信息是否被企业使用
    boolean isUse(IndustryDTO industryDTO);

    boolean delete(IndustryDTO industryDTO);

}
