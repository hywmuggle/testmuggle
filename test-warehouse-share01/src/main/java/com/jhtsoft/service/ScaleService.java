package com.jhtsoft.service;

import com.jhtsoft.dto.ScaleDTO;
import com.jhtsoft.entity.Scale;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ScaleService
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/11
 **/
public interface ScaleService {

    List<Scale> getList(ScaleDTO scaleDTO);

    Map saveOrUpdate(ScaleDTO scaleDTO);

    Map getDetails(ScaleDTO scaleDTO);

    Map updateStatus(ScaleDTO scaleDTO);

    //规模名称重复校验
    boolean nameCanUse(ScaleDTO scaleDTO);

}
