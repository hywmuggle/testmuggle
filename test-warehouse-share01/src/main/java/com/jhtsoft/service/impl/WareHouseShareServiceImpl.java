package com.jhtsoft.service.impl;

import com.jhtsoft.dao.ReadAndWriteObjectDao;
import com.jhtsoft.dao.ReadObjectDao;
import com.jhtsoft.dto.WareHouseShareDTO;
import com.jhtsoft.entity.Scale;
import com.jhtsoft.service.WareHouseShareService;
import com.jhtsoft.util.GenerateUidUtil;
import com.jhtsoft.util.VaildUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: WareHouseShareServiceImpl
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/12
 **/
@Service
public class WareHouseShareServiceImpl implements WareHouseShareService {

    private static Logger logger = LoggerFactory.getLogger(WareHouseShareServiceImpl.class);

    @Autowired
    private ReadObjectDao readObjectDao;

    @Autowired
    private ReadAndWriteObjectDao readAndWriteObjectDao;

    @Override
    public List getList(WareHouseShareDTO shareDTO) {
        //根据条件构造查询语句
        String hqlWhere = getSqlWhere(shareDTO);
        String hql = "FROM WareHouseShare " + hqlWhere;
        List paramsList = getParamsList(shareDTO);

        //有分页信息则进行分页
        List<Scale> scaleList = null;
        if (null != shareDTO.getPageNumber() && null != shareDTO.getPageSize()){
            Integer start = (shareDTO.getPageNumber()-1) * shareDTO.getPageSize();
            scaleList = readObjectDao.getObjectsByHql(hql,start,shareDTO.getPageSize(),paramsList);
        }else {
            scaleList = readObjectDao.getObjectsByHql(hql,null,null,paramsList);
        }
        return scaleList;
    }

    @Override
    public Map add(WareHouseShareDTO shareDTO) {
        Map resultMap = new HashMap();
        ArrayList paramsList = new ArrayList();
        String sharecode = GenerateUidUtil.generateUid();
        String sql = "INSERT INTO tb_warehouseshare(sharecode,cmpycode,qydm,license,keyword,suppermission," +
                "buyneedcheck,estdate,regcapital,regaddress,busscope,legrepresentative,contactname,contactphone,audstatus" +
                ",credate,isdelete) " +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        paramsList.add(sharecode);
        paramsList.add(shareDTO.getCmpycode());
        paramsList.add(shareDTO.getQydm());
        paramsList.add(shareDTO.getLicense());
        paramsList.add(shareDTO.getKeyword());
        paramsList.add(shareDTO.getSuppermission());
        paramsList.add(shareDTO.getBuyneedcheck());
        paramsList.add(shareDTO.getEstdate());
        paramsList.add(shareDTO.getRegcapital());
        paramsList.add(shareDTO.getRegaddress());
        paramsList.add(shareDTO.getBusscope());
        paramsList.add(shareDTO.getLegrepresentative());
        paramsList.add(shareDTO.getContactname());
        paramsList.add(shareDTO.getContactphone());
        paramsList.add(0);
        paramsList.add(new Date());
        paramsList.add(0);
        int updateBySql = readAndWriteObjectDao.updateBySql(sql, paramsList);
        if (updateBySql > 0){
            resultMap.put("sucessMessage","添加成功");
            resultMap.put("shareId",sharecode);
        }else {
            resultMap.put("erroMessage","添加失败");
        }
        return resultMap;
    }

    @Override
    public boolean update(WareHouseShareDTO shareDTO) {
        List paramsList = new ArrayList();
        //根据条件构造sql语句
        String sqlSet = getSqlSet(shareDTO);
        paramsList = getParamsList2(shareDTO);
        paramsList.add(shareDTO.getSharecode());
        //此处要区分是运维还是客户进行更改，客户有些字段限制修改，客户不能修改审核中记录,运维拥有所有权限
        String sql = "UPDATE tb_warehouseshare  " + sqlSet + " WHERE sharecode=? AND isdelete='0'";
        if (false){
            //客户进行修改
            sql = "UPDATE tb_warehouseshare  " + sqlSet + " WHERE sharecode=? AND isdelete='0' AND (audstatus=0 OR audstatus=3)";
        }
        int updateBySql = readAndWriteObjectDao.updateBySql(sql, paramsList);
        if (updateBySql > 0){
            return true;
        }
        return false;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public boolean auditApply(WareHouseShareDTO shareDTO) {
        //构造sql与传入参数
        List paramsList = new ArrayList();
        String sql = "UPDATE tb_warehouseshare SET audstatus=?";
        paramsList.add(shareDTO.getAudstatus());
        //审核失败,需要有审核反馈
        if (3 == shareDTO.getAudstatus()){
            sql = sql + ",feedback=?";
            paramsList.add(shareDTO.getFeedback());
        }
        //传入参数:行业与规模信息都不为空，为企业分配行业规模
        if (!VaildUtil.isEmpty(shareDTO.getIndustrycode()) && !VaildUtil.isEmpty(shareDTO.getScalecode())){
            //查询企业以前的行业关系
            List paramsList2 = new ArrayList();
            String sqlQuery = "SELECT cmpycode,qydm FROM tb_cmpyscale WHERE cmpycode=(SELECT cmpycode FROM tb_warehouseshare WHERE sharecode=?  AND isdelete='0' LIMIT 1)";
            paramsList2.add(shareDTO.getSharecode());
            List<Map> mapsList = readObjectDao.getMapOfObjectsBySql(sqlQuery, paramsList2);
            if (null != mapsList && mapsList.size() > 0){
                //更新企业与行业规模关系表
                String sqlUpdate = "UPDATE tb_cmpyscale SET industrycode=?,scalecode=?,upddate=? WHERE cmpycode=? AND isdelete='0'";
                paramsList2.clear();
                paramsList2.add(shareDTO.getIndustrycode());
                paramsList2.add(shareDTO.getScalecode());
                paramsList2.add(new Date());
                paramsList2.add(mapsList.get(0).get("cmpycode"));
                int i = readAndWriteObjectDao.updateBySql(sqlUpdate, paramsList2);
                if (i <= 0){
                    throw new RuntimeException("更新企业与行业规模关系表失败");
                }
            }else {
                //新增企业与行业规模关系表
                String sqlAdd ="INSERT INTO tb_cmpyscale(cmpycode,qydm,industrycode,scalecode,credate,operuname,isdelete) values(?,?,?,?,?,?,?)";
                paramsList2.clear();
                paramsList2.add(mapsList.get(0).get("cmpycode"));
                paramsList2.add(mapsList.get(0).get("qydm"));
                paramsList2.add(shareDTO.getIndustrycode());
                paramsList2.add(shareDTO.getScalecode());
                paramsList2.add(new Date());
                paramsList2.add("侯迎伟");
                paramsList2.add("0");
                int i = readAndWriteObjectDao.updateBySql(sqlAdd, paramsList2);
                if (i <= 0){
                    throw new RuntimeException("新增企业与行业规模关系表失败");
                }
            }
            sql = sql + ",industrycode=?,scalecode=?";
            paramsList.add(shareDTO.getIndustrycode());
            paramsList.add(shareDTO.getScalecode());
        }
        sql = sql + " WHERE sharecode=? AND isdelete='0'";
        paramsList.add(shareDTO.getSharecode());

        //（审核）保存加入仓分享信息
        int updateBySql = readAndWriteObjectDao.updateBySql(sql, paramsList);
        if (updateBySql > 0){
            return true;
        }
        return false;
    }

    @Override
    public Map updateStatus(WareHouseShareDTO shareDTO) {
        Map resultMap = new HashMap();
        String sql = "UPDATE tb_warehouseshare SET status=? WHERE FIND_IN_SET(sharecode,?) AND status<>? AND isdelete='0'";
        List paramsList = new ArrayList();
        paramsList.add(shareDTO.getStatus());
        paramsList.add(shareDTO.getSharecode());
        paramsList.add(shareDTO.getStatus());
        int updateByHql = readAndWriteObjectDao.updateBySql(sql, paramsList);
        if (updateByHql > 0){
            String successMessage = (shareDTO.getStatus() == 0 ? "成功启用" : "成功禁用") + updateByHql + "条企业" ;
            resultMap.put("successMessage",successMessage);
        }else {
            resultMap.put("erroMessage","修改失败");
        }
        return resultMap;
    }

    @Override
    public Map getDetails(String sharecode) {
        //需要多表关联查询：联表查询企业基础信息、行业信息、行业规模信息 和 加入仓分享记录信息
        String sql = "SELECT id FROM tb_warehouseshare WHERE isdelete='0' AND sharecode=?";
        List paramsList = new ArrayList();
        paramsList.add(sharecode);
        Map detailsMap = readAndWriteObjectDao.getMapOfObjectBySql(sql, paramsList);
        return detailsMap;
    }


    private String getSqlWhere(WareHouseShareDTO shareDTO){
        String hqlWhere = " WHERE isdelete='0' ";
        if (null != shareDTO.getCmpycode()){
            hqlWhere = hqlWhere + " AND cmpycode=?";
        }
        if (null != shareDTO.getAudstatus()){
            hqlWhere = hqlWhere + " AND audstatus=?";
        }
        if (null != shareDTO.getKeyword()){
            hqlWhere = hqlWhere + " AND (keyword LIKE ? OR busscope LIKE ?)";
        }
        if (null != shareDTO.getSuppermission()){
            hqlWhere = hqlWhere + " AND suppermission=?";
        }
        if (null != shareDTO.getBuyneedcheck()){
            hqlWhere = hqlWhere + " AND buyneedcheck=?";
        }
        if (null != shareDTO.getBeginDate()){
            hqlWhere = hqlWhere + " AND DATE_FORMAT(credate,'%Y-%m-%d')>=?";
        }
        if (null != shareDTO.getEndDate()){
            hqlWhere = hqlWhere + " AND DATE_FORMAT(credate,'%Y-%m-%d')<=?";
        }
        if (null != shareDTO.getSharecode()){
            hqlWhere = hqlWhere + " AND sharecode=?";
        }
        return hqlWhere;
    }

    private List getParamsList(WareHouseShareDTO shareDTO){
        List paramsList = new ArrayList();
        if (null != shareDTO.getCmpycode()){
            paramsList.add(shareDTO.getCmpycode());
        }
        if (null != shareDTO.getAudstatus()){
            paramsList.add(shareDTO.getAudstatus());
        }
        if (null != shareDTO.getKeyword()){
            paramsList.add("%" + shareDTO.getKeyword() + "%");
            paramsList.add("%" + shareDTO.getKeyword() + "%");
        }
        if (null != shareDTO.getSuppermission()){
            paramsList.add(shareDTO.getSuppermission());
        }
        if (null != shareDTO.getBuyneedcheck()){
            paramsList.add(shareDTO.getBuyneedcheck());
        }
        if (null != shareDTO.getBeginDate()){
            paramsList.add(shareDTO.getBeginDate());
            //logger.info("===========:" + UtilDate.formatDate(shareDTO.getBeginDate(),"yyyy-MM-dd"));
        }
        if (null != shareDTO.getEndDate()){
            paramsList.add(shareDTO.getEndDate());
        }
        if (null != shareDTO.getSharecode()){
            paramsList.add(shareDTO.getSharecode());
        }
        return paramsList;
    }

    private String getSqlSet(WareHouseShareDTO shareDTO){
        String hqlSet = " SET isdelete='0' ";
        if (null != shareDTO.getKeyword()){
            hqlSet = hqlSet + " ,keyword=?";
        }
        if (null != shareDTO.getSuppermission()){
            hqlSet = hqlSet + " ,suppermission=?";
        }
        if (null != shareDTO.getBuyneedcheck()){
            hqlSet = hqlSet + " ,buyneedcheck=?";
        }
        if (null != shareDTO.getLicense()){
            hqlSet = hqlSet + " ,license=?";
        }
        if (null != shareDTO.getIndustrycode()){
            hqlSet = hqlSet + " ,industrycode=?";
        }
        if (null != shareDTO.getScalecode()){
            hqlSet = hqlSet + " ,scalecode=?";
        }
        return hqlSet;
    }

    private List getParamsList2(WareHouseShareDTO shareDTO){
        List paramsList = new ArrayList();
        if (null != shareDTO.getKeyword()){
            paramsList.add(shareDTO.getKeyword());
        }
        if (null != shareDTO.getSuppermission()){
            paramsList.add(shareDTO.getSuppermission());
        }
        if (null != shareDTO.getBuyneedcheck()){
            paramsList.add(shareDTO.getBuyneedcheck());
        }

        if (null != shareDTO.getLicense()){
            paramsList.add(shareDTO.getLicense());
        }
        if (null != shareDTO.getIndustrycode()){
            paramsList.add(shareDTO.getIndustrycode());
        }
        if (null != shareDTO.getScalecode()){
            paramsList.add(shareDTO.getScalecode());
        }
        return paramsList;
    }
}
