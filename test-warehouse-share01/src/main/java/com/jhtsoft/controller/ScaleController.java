package com.jhtsoft.controller;

import com.jhtsoft.dto.ScaleDTO;
import com.jhtsoft.entity.Scale;
import com.jhtsoft.service.ScaleService;
import com.jhtsoft.util.Result;
import com.jhtsoft.util.VaildUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ScaleController
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/11
 **/
@RequestMapping("/scale")
@RestController
public class ScaleController {

    private static Logger logger = LoggerFactory.getLogger(ScaleController.class);

    @Autowired
    ScaleService scaleService;

    /**
     * 规模信息列表查询
     * @param scaleDTO
     * @return
     */
    @PostMapping("/getlist")
    public Result getList(@RequestBody ScaleDTO scaleDTO){
        logger.info("规模信息列表查询开始:" + scaleDTO.toString());
        try {
            //验证请求参数
            if (!VaildUtil.isEmpty(scaleDTO.getStatus())){
                if (!VaildUtil.isNumeric(scaleDTO.getStatus().toString())){
                    return Result.buildError("410", "status数据错误");
                }
            }

            //查询
            List<Scale> scaleList = scaleService.getList(scaleDTO);
            if (null == scaleList || scaleList.size() <= 0){
                return Result.buildError("400","未找到对应的规模信息");
            }
            return Result.buildSuccess("规模信息列表查询成功").addResult(scaleList);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("规模信息列表查询异常:" + e.toString());
            return Result.buildError("500","服务端异常");
        }finally {
            logger.info("规模信息列表查询结束");
        }
    }

    /**
     * 编辑规模信息
     * @param scaleDTO
     * @return
     */
    @PostMapping("/saveorupdate")
    public Result saveOrUpdate(@RequestBody ScaleDTO scaleDTO){
        logger.info("编辑规模信息开始:" + scaleDTO.toString());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(scaleDTO.getIndustrycode())){
                return Result.buildError("410", "行业不能为空");
            }
            if (VaildUtil.isEmpty(scaleDTO.getScalename())){
                return Result.buildError("410", "规模名称不能为空，且不超过30个字符");
            }
            if (scaleDTO.getScalename().length() > 30){
                return Result.buildError("410","规模名称不能为空，且不超过30个字符");
            }
            if (VaildUtil.isEmpty(scaleDTO.getStatus())){
                return Result.buildError("410", "规模状态不能为空");
            }

            //验证名称是否可用
            boolean nameCanUseBo = scaleService.nameCanUse(scaleDTO);
            if (!nameCanUseBo){
                return Result.buildError("410","规模名称不可用");
            }

            //编辑
            Map resultMap = scaleService.saveOrUpdate(scaleDTO);
            if (null != resultMap.get("erroMessage")){
                return Result.buildError("500", resultMap.get("erroMessage").toString());
            }
            return Result.buildSuccess("编辑规模信息成功").addResult(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("编辑规模信息异常:" + e.toString());
            return Result.buildError("500","服务端异常");
        }finally {
            logger.info("编辑规模信息结束");
        }
    }

    /**
     * 规模详情查询
     * @param scaleDTO
     * @return
     */
    @PostMapping("/getdetails")
    public Result getDetails(@RequestBody ScaleDTO scaleDTO){
        logger.info("规模详情查询开始:" + scaleDTO.toStringDetails());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(scaleDTO.getScalecode()) && VaildUtil.isEmpty(scaleDTO.getCmpycode())){
                return Result.buildError("410","规模和企业至少指定一个");
            }

            //查询
            Map detailsMap = scaleService.getDetails(scaleDTO);
            if (null == detailsMap){
                return Result.buildError("not find","未找到对应的行业规模信息");
            }
            return Result.buildSuccess("规模详情查询成功").addResult(detailsMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("规模详情查询异常:" + e.toString());
            return Result.buildError("500","服务端异常");
        }finally {
            logger.info("规模详情查询结束");
        }
    }

    /**
     * 规模状态操作
     * @param scaleDTO
     * @return
     */
    @PostMapping("/stateofoperation")
    public Result operationStatus(@RequestBody ScaleDTO scaleDTO){
        logger.info("规模状态操作开始:" + scaleDTO.toStringOperationStatus());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(scaleDTO.getScalecode())){
                return Result.buildError("410","规模编号不能为空");
            }
            if (VaildUtil.isEmpty(scaleDTO.getStatus())){
                return Result.buildError("410","更改状态不能为空");
            }

            //更改状态
            Map resultMap = scaleService.updateStatus(scaleDTO);
            if (null != resultMap.get("erroMessage")){
                return Result.buildError("500", resultMap.get("erroMessage").toString());
            }
            logger.info(resultMap.get("successMessage").toString());
            return Result.buildSuccess("规模状态操作成功").addResult(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("规模状态操作异常:" + e.toString());
            return Result.buildError("500","服务端异常");
        }finally {
            logger.info("规模状态操作结束");
        }
    }

    /**
     * 规模名称重复校验
     * @param scaleDTO
     * @return
     */
    @PostMapping("/repeatcheck")
    public Result repeatCheck(@RequestBody ScaleDTO scaleDTO){
        logger.info("规模名称重复校验开始:" + scaleDTO.toStringrepeatCheck());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(scaleDTO.getIndustrycode())){
                return Result.buildError("410", "行业不能为空");
            }
            if (VaildUtil.isEmpty(scaleDTO.getScalename())){
                return Result.buildError("410", "规模名称不能为空，且不超过30个字符");
            }
            if (scaleDTO.getScalename().length() > 30){
                return Result.buildError("410","规模名称不能为空，且不超过30个字符");
            }

            //验证名称是否可用
            boolean nameCanUseBo = scaleService.nameCanUse(scaleDTO);
            if (!nameCanUseBo){
                return Result.buildError("410","规模名称不可用");
            }
            return Result.buildSuccess("该规模名称可用");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("规模名称重复校验异常:" + e.toString());
            return Result.buildError("500","服务端异常");
        }finally {
            logger.info("规模名称重复校验结束");
        }
    }

}
