package com.jhtsoft.controller;

import com.jhtsoft.dto.IndustryDTO;
import com.jhtsoft.entity.Industry;
import com.jhtsoft.service.IndustryService;
import com.jhtsoft.util.Result;
import com.jhtsoft.util.VaildUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndustryConctroller
 * @Describe: 行业信息
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
@RequestMapping("/industry")
@RestController
public class IndustryConctroller {

    private static Logger logger = LoggerFactory.getLogger(IndustryConctroller.class);

    @Autowired
    IndustryService industryService;

    /**
     *行业列表查询
     * @param industryDTO
     * @return
     */
    @PostMapping("/getlist")
    public Result getIndustry(@RequestBody IndustryDTO industryDTO){
        logger.info("行业列表查询开始:" + industryDTO.toString());
        try {
            //验证请求参数
            if (!VaildUtil.isEmpty(industryDTO.getStatus())){
                if (!VaildUtil.isNumeric(industryDTO.getStatus().toString())){
                    return Result.buildError("410", "status数据错误");
                }
            }

            //查询
            List<Industry> industryList = industryService.getList(industryDTO);
            if (null == industryList || industryList.size() <= 0){
                return Result.buildError("400","未找到对应的行业信息");
            }
            return Result.buildSuccess("行业列表查询成功").addResult(industryList);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("行业列表查询异常:" + e.toString());
            return Result.buildError("500", "服务端错误");
        }finally {
            logger.info("行业列表查询结束");
        }
    }

    /**
     * 行业编辑
     * @param industryDTO
     * @return
     */
    @PostMapping("/saveorupdate")
    public Result saveOrUpdate(@RequestBody IndustryDTO industryDTO, HttpServletRequest request){
        logger.info("行业编辑开始:" + industryDTO.toString());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(industryDTO.getIndustryname())){
                return Result.buildError("410","行业名称不能为空，且不超过30个字符");
            }
            if (industryDTO.getIndustryname().length()>30){
                return Result.buildError("410","行业名称不能为空，且不超过30个字符");
            }
            if (VaildUtil.isEmpty(industryDTO.getStatus())){
                return Result.buildError("410","行业状态不能为空");
            }
            if (!VaildUtil.isNumeric(industryDTO.getStatus().toString())){
                return Result.buildError("410", "status数据错误");
            }
            //验证名称是否可用
            boolean nameCanUseBo = industryService.nameCanUse(industryDTO);
            if (!nameCanUseBo){
                return Result.buildError("410","行业名称不可用");
            }

            // 读请求头，得到会话标识，获取登陆人姓名
            Enumeration<String> enumeration = request.getHeaderNames();
            Map<String, String> map = new HashMap<String, String>();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                map.put(name, value);
            }
            String loginId = map.get("loginId");

            //编辑
            Map resultMap = industryService.saveOrUpdate(industryDTO);
            if (null != resultMap.get("erroMessage")){
                return Result.buildError("500", resultMap.get("erroMessage").toString());
            }
            return Result.buildSuccess("行业编辑成功").addResult(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("行业编辑异常:" + e.toString());
            return Result.buildError("500", "服务端错误");
        }finally {
            logger.info("行业编辑结束");
        }
    }

    /**
     * 行业详情查询
     * @param industryDTO
     * @return
     */
    @PostMapping("/details")
    public Result getDetails(@RequestBody IndustryDTO industryDTO){
        logger.info("行业详情查询开始:" + industryDTO.toString());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(industryDTO.getIndustrycode())){
                return Result.buildError("410","行业编号不能为空");
            }

            //查询
            Map detailsMap = industryService.getDetails(industryDTO.getIndustrycode());
            if (null == detailsMap){
                return Result.buildError("not find","行业信息未找到，请刷新重试");
            }
            return Result.buildSuccess("行业详情查询成功").addResult(detailsMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("行业详情查询异常:" + e.toString());
            return Result.buildError("500", "服务端错误");
        }finally {
            logger.info("行业详情查询结束");
        }
    }

    /**
     * 行业状态操作
     * @param industryDTO
     * @return
     */
    @PostMapping("/stateofoperation")
    public Result operationStatus(@RequestBody IndustryDTO industryDTO){
        logger.info("行业状态操作开始:" + industryDTO.toString());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(industryDTO.getIndustrycode())){
                return Result.buildError("410","需要操作的行业编号不能为空！");
            }
            if (VaildUtil.isEmpty(industryDTO.getStatus())){
                return Result.buildError("410","更改后的状态不能为空");
            }

            //更改状态
            Map resultMap = industryService.updateStatus(industryDTO);
            if (null != resultMap.get("erroMessage")){
                return Result.buildError("500", resultMap.get("erroMessage").toString());
            }
            return Result.buildSuccess("行业状态操作成功").addResult(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("行业状态操作异常:" + e.toString());
            return Result.buildError("500", "服务端错误");
        }finally {
            logger.info("行业状态操作结束");
        }
    }

    /**
     * 行业名称重复性校验
     * @param industryDTO
     * @return
     */
    @PostMapping("/repeatcheck")
    public Result repeatCheck(@RequestBody IndustryDTO industryDTO){
        logger.info("行业名称重复性校验开始:" + industryDTO.toString());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(industryDTO.getIndustryname())){
                return Result.buildError("410","行业名称不能为空");
            }
            //校验长度

            //验证行业名称是否可用
            boolean nameCanUseBo = industryService.nameCanUse(industryDTO);
            if (!nameCanUseBo){
                return Result.buildError("not find code","该行业名称不可用");
            }
            return Result.buildSuccess("该行业名称可用");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("行行业名称重复性校验异常:" + e.toString());
            return Result.buildError("500", "服务端错误");
        }finally {
            logger.info("行业名称重复性校验结束");
        }
    }

    /**
     * 删除行业信息
     * @param industryDTO
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody IndustryDTO industryDTO){
        logger.info("删除行业信息开始:" + industryDTO.toString());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(industryDTO.getIndustrycode())){
                return Result.buildError("410","行业编号不能为空");
            }

            //查询行业信息是否存在
            boolean objectExistBo = industryService.objectExist(industryDTO);
            if (!objectExistBo){
                return Result.buildError("410","行业信息未找到，请刷新重试");
            }

            //查询行业信息是否被企业使用
            boolean isUseBo = industryService.isUse(industryDTO);
            if (isUseBo){
                return Result.buildError("410","该行业已被使用，无法删除");
            }

            //执行删除（假删除）
            boolean isDelete = industryService.delete(industryDTO);
            if (!isDelete){
                return Result.buildError("undefined code", "删除失败");
            }
            return Result.buildSuccess("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除行业信息异常:" + e.toString());
            return Result.buildError("500", "服务端错误");
        }finally {
            logger.info("删除行业信息结束");
        }
    }



}
