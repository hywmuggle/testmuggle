package com.jhtsoft.controller;

import com.jhtsoft.dto.IndustryDTO;
import com.jhtsoft.dto.WareHouseShareDTO;
import com.jhtsoft.service.WareHouseShareService;
import com.jhtsoft.util.Result;
import com.jhtsoft.util.VaildUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WareHouseShareController
 * @Describe: 加入仓分享信息
 * @Author: houyingwei
 * @Date: 2019/4/12
 **/
@RequestMapping("/WareHouseShare")
@RestController
public class WareHouseShareController {

    private static Logger logger = LoggerFactory.getLogger(WareHouseShareController.class);

    @Autowired
    WareHouseShareService wareHouseShareService;

    /**
     * 加入仓分享申请列表查询
     * @return
     */
    @PostMapping("/getlist")
    public Result getList(@RequestBody WareHouseShareDTO shareDTO){
        logger.info("加入仓分享申请列表查询开始:" + shareDTO.toStringGetList());
        try {
            //验证请求参数
            if (!VaildUtil.isEmpty(shareDTO.getAudstatus())){
                if (!VaildUtil.isNumeric(shareDTO.getAudstatus().toString())){
                    return Result.buildError("410", "audstatus数据错误");
                }
            }
            if (!VaildUtil.isEmpty(shareDTO.getSuppermission())){
                if (!VaildUtil.isNumeric(shareDTO.getSuppermission().toString())){
                    return Result.buildError("410", "suppermission数据错误");
                }
            }
            if (!VaildUtil.isEmpty(shareDTO.getBuyneedcheck())){
                if (!VaildUtil.isNumeric(shareDTO.getBuyneedcheck().toString())){
                    return Result.buildError("410", "buyneedcheck数据错误");
                }
            }

            //查询
            List shareList = wareHouseShareService.getList(shareDTO);
            if (null == shareList || shareList.size() <= 0){
                return Result.buildError("400","未找到对应的信息");
            }
            return Result.buildSuccess("加入仓分享申请列表查询成功").addResult(shareList);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("加入仓分享申请列表查询异常:" + e.toString());
            return Result.buildError("500","服务端异常");
        }finally {
            logger.info("加入仓分享申请列表查询结束");
        }
    }

    /**
     * 申请加入仓分享
     * @param shareDTO
     * @return
     */
    @PostMapping("/save")
    public Result add(@RequestBody WareHouseShareDTO shareDTO){
        logger.info("申请加入仓分享开始:" + shareDTO.toStringAdd());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(shareDTO.getCmpycode())){
                return Result.buildError("410","企业编号不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getQydm())){
                return Result.buildError("410","企业代码不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getLicense())){
                return Result.buildError("410","营业执照不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getKeyword())){
                return Result.buildError("410","关键词不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getSuppermission())){
                return Result.buildError("410","供方权限不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getBuyneedcheck())){
                return Result.buildError("410","需方是否需要审核不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getEstdate())){
                return Result.buildError("410","成立时间不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getRegcapital())){
                return Result.buildError("410","注册资本不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getRegaddress())){
                return Result.buildError("410","注册地址不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getBusscope())){
                return Result.buildError("410","经营范围不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getLegrepresentative())){
                return Result.buildError("410","法定代表人不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getContactname())){
                return Result.buildError("410","联系人不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getContactphone())){
                return Result.buildError("410","联系人电话不能为空");
            }

            //查询企业是否已经加入（根据企业编号）
            WareHouseShareDTO dto = new WareHouseShareDTO();
            dto.setSharecode(shareDTO.getCmpycode());
            List shareList = wareHouseShareService.getList(dto);
            if (null != shareList || shareList.size() > 0){
                return Result.buildError("410","不能重复加入仓分享");
            }

            //添加
            Map resultMap = wareHouseShareService.add(shareDTO);
            if (null != resultMap.get("erroMessage")){
                return Result.buildError("500", "加入仓分享申请失败，请稍后重试");
            }
            return Result.buildSuccess("申请加入仓分享成功").addResult(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("申请加入仓分享异常:" + e.toString());
            return Result.buildError("500","服务端异常");
        }finally {
            logger.info("申请加入仓分享结束");
        }
    }

    /**
     * 加入仓分享记录修改
     * @param shareDTO
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody WareHouseShareDTO shareDTO){
        logger.info("加入仓分享记录修改开始:" + shareDTO.toStringUpdate());
        try {

            //验证请求参数
            if (VaildUtil.isEmpty(shareDTO.getSharecode())){
                return Result.buildError("410","加入仓分享记录编号不能为空");
            }
//            if (VaildUtil.isEmpty(shareDTO.getSuppermission())){
//                return Result.buildError("410","供方权限不能为空");
//            }
//            if (VaildUtil.isEmpty(shareDTO.getBuyneedcheck())){
//                return Result.buildError("410","需方是否需要审核不能为空");
//            }
//            if (VaildUtil.isEmpty(shareDTO.getKeyword())){
//                return Result.buildError("410","关键词不能为空");
//            }
//            if (VaildUtil.isEmpty(shareDTO.getLicense())){
//                return Result.buildError("410","营业执照不能为空");
//            }
//            if (VaildUtil.isEmpty(shareDTO.getIndustrycode())){
//                return Result.buildError("410","行业编号不能为空");
//            }
//            if (VaildUtil.isEmpty(shareDTO.getScalecode())){
//                return Result.buildError("410","规模编号不能为空");
//            }

            //查询该记录是否存在
            WareHouseShareDTO dto = new WareHouseShareDTO();
            dto.setSharecode(shareDTO.getSharecode());
            List shareList = wareHouseShareService.getList(dto);
            if (null == shareList || shareList.size() <= 0){
                return Result.buildError("400","未知的加入仓分享记录，请刷新重试");
            }

            //修改
            boolean updateBo = wareHouseShareService.update(shareDTO);
            if (!updateBo){
                return Result.buildError("500","修改失败");
            }
            return Result.buildSuccess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("加入仓分享记录修改异常:" + e.toString());
            return Result.buildError("500","服务端异常");
        }finally {
            logger.info("加入仓分享记录修改结束");
        }
    }

    /**
     * 加入仓分享申请审核
     * @param shareDTO
     * @return
     */
    @PostMapping("/auditapply")
    public Result auditApply(@RequestBody WareHouseShareDTO shareDTO){
        logger.info("加入仓分享申请审核开始:" + shareDTO.toStringAuditApply());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(shareDTO.getSharecode())){
                return Result.buildError("410","加入仓分享记录编号不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getAudstatus())){
                return Result.buildError("410","审核结果不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getFeedback()) && 3 == shareDTO.getAudstatus()){
                //审核失败
                return Result.buildError("410","审核反馈不能为空");
            }

            //查询记录是否存在 申请记录未找到，请刷新重试

            //保存
            boolean auditApplyBo = wareHouseShareService.auditApply(shareDTO);
            if (!auditApplyBo){
                return Result.buildError("500","加入仓分享申请审核失败");
            }
            return Result.buildSuccess("审核成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("加入仓分享申请审核异常:" + e.toString());
            return Result.buildError("500","服务端异常");
        }finally {
            logger.info("加入仓分享申请审核结束");
        }
    }

    /**
     * 仓分享企业状态操作
     * @param shareDTO
     * @return
     */
    @PostMapping("/stateofoperation")
    public Result operationStatus(@RequestBody WareHouseShareDTO shareDTO){
        logger.info("仓分享企业状态操作开始:" + shareDTO.toStringOperationStatus());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(shareDTO.getSharecode())){
                return Result.buildError("410","仓分享记录编号不能为空");
            }
            if (VaildUtil.isEmpty(shareDTO.getStatus())){
                return Result.buildError("410","更改后的状态不能为空");
            }

            //更改状态
            Map resultMap = wareHouseShareService.updateStatus(shareDTO);
            if (null != resultMap.get("erroMessage")){
                return Result.buildError("500", resultMap.get("erroMessage").toString());
            }
            return Result.buildSuccess("仓分享企业状态操作成功").addResult(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("仓分享企业状态操作异常:" + e.toString());
            return Result.buildError("500", "服务端错误");
        }finally {
            logger.info("仓分享企业状态操作结束");
        }
    }

    /**
     * 加入仓分享数据详情查询
     * @param shareDTO
     * @return
     */
    @PostMapping("/details")
    public Result getDetails(@RequestBody WareHouseShareDTO shareDTO){
        logger.info("加入仓分享数据详情查询开始:" + shareDTO.toStringGetDetails());
        try {
            //验证请求参数
            if (VaildUtil.isEmpty(shareDTO.getSharecode())){
                return Result.buildError("410","仓分享记录编号不能为空");
            }

            //查询
            Map detailsMap = wareHouseShareService.getDetails(shareDTO.getSharecode());
            if (null == detailsMap){
                return Result.buildError("not find","仓分享记录未找到，请刷新重试");
            }
            return Result.buildSuccess("加入仓分享数据详情查询成功").addResult(detailsMap);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("加入仓分享数据详情查询异常:" + e.toString());
            return Result.buildError("500", "服务端错误");
        }finally {
            logger.info("加入仓分享数据详情查询结束");
        }
    }


}
