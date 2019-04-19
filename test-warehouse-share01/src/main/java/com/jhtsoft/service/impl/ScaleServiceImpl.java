package com.jhtsoft.service.impl;

import com.jhtsoft.dao.ReadAndWriteObjectDao;
import com.jhtsoft.dao.ReadObjectDao;
import com.jhtsoft.dto.IndustryDTO;
import com.jhtsoft.dto.ScaleDTO;
import com.jhtsoft.entity.Industry;
import com.jhtsoft.entity.Scale;
import com.jhtsoft.service.ScaleService;
import com.jhtsoft.util.GenerateUidUtil;
import com.jhtsoft.util.VaildUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.*;

/**
 * @ClassName: ScaleService
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/11
 **/
@Service
public class ScaleServiceImpl implements ScaleService{

    private static Logger logger = LoggerFactory.getLogger(ScaleServiceImpl.class);

    @Autowired
    private ReadObjectDao readObjectDao;

    @Autowired
    private ReadAndWriteObjectDao readAndWriteObjectDao;

    @Override
    public List<Scale> getList(ScaleDTO scaleDTO) {
        //根据条件构造查询语句
        String hqlWhere = getSqlWhere(scaleDTO);
        String hql = "FROM Scale " + hqlWhere;
        List paramsList = getParamsList(scaleDTO);

        //有分页信息则进行分页
        List<Scale> scaleList = null;
        if (null != scaleDTO.getPageNumber() && null != scaleDTO.getPageSize()){
            Integer start = (scaleDTO.getPageNumber()-1) * scaleDTO.getPageSize();
            scaleList = readObjectDao.getObjectsByHql(hql,start,scaleDTO.getPageSize(),paramsList);
        }else {
            scaleList = readObjectDao.getObjectsByHql(hql,null,null,paramsList);
        }
        return scaleList;
    }

    @Override
    public Map saveOrUpdate(ScaleDTO scaleDTO) {
        Map resultMap = new HashMap();
        //根据编号为空判断新增还是修改
        if (VaildUtil.isEmpty(scaleDTO.getScalecode())){
            //新增
            Scale scale = new Scale();
            scale.setScalename(scaleDTO.getScalename());
            scale.setIndustrycode(scaleDTO.getIndustrycode());
            scale.setStatus(scaleDTO.getStatus());
            scale.setScalecode(GenerateUidUtil.generateUid());
            scale.setCredate(new Date());
            scale.setIsdelete("0");
            try {
                readAndWriteObjectDao.addObject(scale);
                resultMap.put("scalecode",scale.getScalecode());
                resultMap.put("successMessage","新增成功");
            }catch (Exception e){
                e.printStackTrace();
                logger.info("规模新增失败:" + e.toString());
                resultMap.put("erroMessage","规模新增失败");
            }
        }else {
            //修改
            String sql = "UPDATE tb_scale SET scalename=?,industrycode=?,status=? WHERE isdelete='0' AND scalecode=? " +
                    "AND isdelete NOT IN(SELECT isdelete FROM (SELECT isdelete FROM tb_scale WHERE scalename=? AND industrycode=?  AND scalecode<>? AND isdelete='0' LIMIT 1) AS TEMP)";
            List paramsList = new ArrayList();
            paramsList.add(scaleDTO.getScalename());
            paramsList.add(scaleDTO.getIndustrycode());
            paramsList.add(scaleDTO.getStatus());
            paramsList.add(scaleDTO.getScalecode());
            paramsList.add(scaleDTO.getScalename());
            paramsList.add(scaleDTO.getIndustrycode());
            paramsList.add(scaleDTO.getScalecode());
            int updateByHql = readAndWriteObjectDao.updateBySql(sql, paramsList);
            if (updateByHql > 0){
                resultMap.put("scalecode",scaleDTO.getScalecode());
                resultMap.put("successMessage","修改成功");
            }else {
                resultMap.put("erroMessage","规模修改失败");
            }
        }
        return resultMap;
    }

    @Override
    public Map getDetails(ScaleDTO scaleDTO) {
        //根据规模编号查询
        String sql = "SELECT ts.industrycode,ti.industryname,ts.scalecode,ts.scalename,date_format(ts.credate, '%Y-%m-%d') as credate,ts.operuname " +
                " FROM tb_scale ts " +
                " LEFT JOIN tb_industry ti ON ts.industrycode=ti.industrycode " +
                " WHERE ts.scalecode=? AND ts.isdelete='0' AND ti.isdelete='0'";
        List paramsList = new ArrayList();
        paramsList.add(scaleDTO.getScalecode());
        //根据企业编号查询
        if (VaildUtil.isEmpty(scaleDTO.getScalecode())){
            sql = "SELECT ts.industrycode,ti.industryname,ts.scalecode,ts.scalename,date_format(ts.credate, '%Y-%m-%d') as credate,ts.operuname " +
                    " FROM tb_cmpyscale tc " +
                    " LEFT JOIN tb_scale ts ON tc.scalecode=ts.scalecode " +
                    " LEFT JOIN tb_industry ti ON ts.industrycode=ti.industrycode " +
                    " WHERE tc.cmpycode=? AND ts.isdelete='0' AND ti.isdelete='0'";
            paramsList.clear();
            paramsList.add(scaleDTO.getCmpycode());
        }
        Map detailsMap = readAndWriteObjectDao.getMapOfObjectBySql(sql, paramsList);
        return detailsMap;
    }

    @Override
    public Map updateStatus(ScaleDTO scaleDTO) {
        Map resultMap = new HashMap();
        String sql = "UPDATE tb_scale SET status=? WHERE FIND_IN_SET(scalecode,?) AND status<>? AND isdelete='0'";
        List paramsList = new ArrayList();
        paramsList.add(scaleDTO.getStatus());
        paramsList.add(scaleDTO.getScalecode());
        paramsList.add(scaleDTO.getStatus());
        int updateByHql = readAndWriteObjectDao.updateBySql(sql, paramsList);
        if (updateByHql > 0){
            String successMessage = (scaleDTO.getStatus() == 0 ? "成功启用" : "成功禁用") + updateByHql + "条规模" ;
            resultMap.put("successMessage",successMessage);
        }else {
            resultMap.put("erroMessage","修改失败");
        }
        return resultMap;
    }

    @Override
    public boolean nameCanUse(ScaleDTO scaleDTO) {
        boolean nameCanUseBo = true;
        List paramsList = new ArrayList();
        paramsList.add(scaleDTO.getScalename());
        paramsList.add(scaleDTO.getIndustrycode());

        //根据编号为空判断新增还是修改,默认为新增
        //新增验证重复
        String hql = "SELECT id FROM Scale WHERE scalename=? AND industrycode=? AND isdelete='0'";
        //修改验证重复
        if (!VaildUtil.isEmpty(scaleDTO.getScalecode())) {
            hql ="SELECT id FROM Scale WHERE scalename=? AND industrycode=? AND scalecode<>? AND isdelete='0'";
            paramsList.add(scaleDTO.getScalecode());
        }
        List<Scale> scaleList = readObjectDao.getObjectsByHql(hql, null, null, paramsList);
        if (null != scaleList && scaleList.size()>0){
            //已有该规模名称
            nameCanUseBo = false;
        }
        return nameCanUseBo;
    }


    private String getSqlWhere(ScaleDTO scaleDTO){
        String hqlWhere = " WHERE isdelete='0' ";
        if (null != scaleDTO.getIndustrycode()){
            hqlWhere = hqlWhere + " AND industrycode=?";
        }
        if (null != scaleDTO.getScalename()){
            hqlWhere = hqlWhere + " AND scalename like ?";
        }
        if (null != scaleDTO.getStatus()){
            hqlWhere = hqlWhere + " AND status=?";
        }
        return hqlWhere;
    }

    private List getParamsList(ScaleDTO scaleDTO){
        List paramsList = new ArrayList();
        if (null != scaleDTO.getIndustrycode()){
            paramsList.add(scaleDTO.getIndustrycode());
        }
        if (null != scaleDTO.getScalename()){
            paramsList.add("%" + scaleDTO.getScalename() + "%");
        }
        if (null != scaleDTO.getStatus()){
            paramsList.add(scaleDTO.getStatus());
        }
        return paramsList;
    }
}
