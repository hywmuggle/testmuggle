package com.jhtsoft.service;

import com.jhtsoft.dto.WareHouseShareDTO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WareHouseShareService
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/12
 **/
public interface WareHouseShareService {

    List getList(WareHouseShareDTO shareDTO);

    Map add(WareHouseShareDTO shareDTO);

    boolean update(WareHouseShareDTO shareDTO);

    boolean auditApply(WareHouseShareDTO shareDTO);

    Map updateStatus(WareHouseShareDTO shareDTO);

    Map getDetails(String sharecode);

}
