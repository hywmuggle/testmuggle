package com.jhtsoft.service.impl;

import com.jhtsoft.dao.ReadAndWriteObjectDao;
import com.jhtsoft.dao.ReadObjectDao;
import com.jhtsoft.dto.IndustryDTO;
import com.jhtsoft.entity.Industry;
import com.jhtsoft.service.IndustryService;
import com.jhtsoft.util.GenerateUidUtil;
import com.jhtsoft.util.VaildUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: IndustryServiceImpl
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
@Service
public class IndustryServiceImpl implements IndustryService {

    private static Logger logger = LoggerFactory.getLogger(IndustryServiceImpl.class);

    @Autowired
    private ReadObjectDao readObjectDao;

    @Autowired
    private ReadAndWriteObjectDao readAndWriteObjectDao;

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public List<Industry> getList(IndustryDTO industryDTO) {
        //根据条件构造查询语句
        String hqlWhere = getSqlWhere(industryDTO);
        String hql = "FROM Industry " + hqlWhere;
        List paramsList = getParamsList(industryDTO);

        //有分页信息则进行分页
        List<Industry> industryList = null;
        if (null != industryDTO.getPageNumber() && null != industryDTO.getPageSize()){
            Integer start = (industryDTO.getPageNumber()-1) * industryDTO.getPageSize();
            industryList = readObjectDao.getObjectsByHql(hql,start,industryDTO.getPageSize(),paramsList);
        }else {
            industryList = readObjectDao.getObjectsByHql(hql,null,null,paramsList);
        }
        return industryList;
    }

    @Override
    public boolean nameCanUse(IndustryDTO industryDTO) {
        boolean nameCanUseBo = true;
        String hql = "FROM Industry WHERE industryname=? AND isdelete='0'";
        List paramsList = new ArrayList();
        paramsList.add(industryDTO.getIndustryname());

        //根据编号为空判断新增还是修改,默认判断为新增
        if (!VaildUtil.isEmpty(industryDTO.getIndustrycode())) {
            //修改时验证名称是否可用
            hql ="SELECT id FROM Industry WHERE industryname=? AND industrycode<>? AND isdelete='0'";
            paramsList.add(industryDTO.getIndustrycode());
        }
        List<Industry> industryList = readObjectDao.getObjectsByHql(hql, null, null, paramsList);
        if (null != industryList && industryList.size()>0){
            //已有该行业名称
            nameCanUseBo = false;
        }
        return nameCanUseBo;
    }

    @Override
    public Map saveOrUpdate(IndustryDTO industryDTO) {
        Map resultMap = new HashMap();
        //根据编号为空判断新增还是修改
        if (VaildUtil.isEmpty(industryDTO.getIndustrycode())){
            //新增
            Industry industry = new Industry();
            industry.setIndustryname(industryDTO.getIndustryname());
            industry.setStatus(industryDTO.getStatus());
            industry.setIndustrycode(GenerateUidUtil.generateUid());
            industry.setCredate(new Date());
            industry.setIsdelete("0");
            try {
                readAndWriteObjectDao.addObject(industry);
                resultMap.put("industrycode",industry.getIndustrycode());
                resultMap.put("successMessage","新增成功");
            }catch (Exception e){
                e.printStackTrace();
                logger.info("行业新增失败:" + e.toString());
                resultMap.put("erroMessage","行业新增失败");
            }
        }else {
            //修改
            String sql = "UPDATE tb_industry SET industryname=?,status=? WHERE isdelete='0' AND industrycode=? " +
                    "AND isdelete NOT IN(SELECT isdelete FROM (SELECT isdelete FROM tb_industry WHERE industryname=? AND industrycode<>? AND isdelete='0' LIMIT 1) AS TEMP)";
            List paramsList = new ArrayList();
            paramsList.add(industryDTO.getIndustryname());
            paramsList.add(industryDTO.getStatus());
            paramsList.add(industryDTO.getIndustrycode());
            paramsList.add(industryDTO.getIndustryname());
            paramsList.add(industryDTO.getIndustrycode());
            int updateByHql = readAndWriteObjectDao.updateBySql(sql, paramsList);
            if (updateByHql > 0){
                resultMap.put("industrycode",industryDTO.getIndustrycode());
                resultMap.put("successMessage","修改成功");
            }else {
                resultMap.put("erroMessage","行业修改失败");
            }
        }

        return resultMap;
    }

    @Override
    public Map getDetails(String industrycode) {
        String sql = "SELECT industrycode,industryname,status,credate,operuname FROM tb_industry WHERE isdelete='0' AND industrycode=?";
        List paramsList = new ArrayList();
        paramsList.add(industrycode);
        Map detailsMap = readAndWriteObjectDao.getMapOfObjectBySql(sql, paramsList);
        return detailsMap;
    }

    @Override
    public Map updateStatus(IndustryDTO industryDTO) {
        Map resultMap = new HashMap();
        String sql = "UPDATE tb_industry SET status=? WHERE FIND_IN_SET(industrycode,?) AND status<>? AND isdelete='0'";
        List paramsList = new ArrayList();
        paramsList.add(industryDTO.getStatus());
        paramsList.add(industryDTO.getIndustrycode());
        paramsList.add(industryDTO.getStatus());
        int updateByHql = readAndWriteObjectDao.updateBySql(sql, paramsList);
        if (updateByHql > 0){
            String successMessage = (industryDTO.getStatus() == 0 ? "成功启用" : "成功禁用") + updateByHql + "条行业" ;
            resultMap.put("successMessage",successMessage);
        }else {
            resultMap.put("erroMessage","修改失败");
        }
        return resultMap;
    }

    @Override
    public boolean objectExist(IndustryDTO industryDTO) {
        String hql = "FROM Industry WHERE industrycode=? AND isdelete='0'";
        List paramsList = new ArrayList();
        paramsList.add(industryDTO.getIndustrycode());
        List<Industry> industryList = readObjectDao.getObjectsByHql(hql, null, null, paramsList);
        if (null != industryList && industryList.size()>0){
            //存在该行业信息
            return true;
        }
        return false;
    }

    @Override
    public boolean isUse(IndustryDTO industryDTO) {
        return false;
    }

    @Override
    public boolean delete(IndustryDTO industryDTO) {
        String uid = GenerateUidUtil.generateUid();
        String sql = "UPDATE tb_industry SET isdelete='1',uid=?,upddate=? WHERE industrycode=? AND isdelete='0'";
        List paramsList = new ArrayList();
        paramsList.add(uid);
        paramsList.add(new Date());
        paramsList.add(industryDTO.getIndustrycode());
        int updateBySql = readAndWriteObjectDao.updateBySql(sql, paramsList);
        if (updateBySql > 0){
            return true;
        }
        return false;
    }

    private String getSqlWhere(IndustryDTO industryDTO){
        String hqlWhere = " WHERE isdelete='0' ";
        if (null != industryDTO.getIndustryname()){
            hqlWhere = hqlWhere + " AND industryname like ?";
        }
        if (null != industryDTO.getStatus()){
            hqlWhere = hqlWhere + " AND status=?";
        }
        return hqlWhere;
    }

    private List getParamsList(IndustryDTO industryDTO){
        List paramsList = new ArrayList();
        if (null != industryDTO.getIndustryname()){
            paramsList.add("%" + industryDTO.getIndustryname() + "%");
        }
        if (null != industryDTO.getStatus()){
            paramsList.add(industryDTO.getStatus());
        }
        return paramsList;
    }
}
