package com.jhtsoft.controller;

import com.jhtsoft.dao.ReadAndWriteObjectDao;
import com.jhtsoft.dao.ReadObjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: Test
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
@RestController
@RequestMapping("/test")
public class Test {

    private static Logger logger = LoggerFactory.getLogger(IndustryConctroller.class);

    @Autowired
    private ReadObjectDao readObjectDao;

    @Autowired
    private ReadAndWriteObjectDao readAndWriteObjectDao;


    @RequestMapping("/get/version")
    public Object getVersion(){
        return "1:" + readObjectDao.getStringListBySql("SELECT VERSION()");
    }
    @RequestMapping("/get/version2")
    public Object getVersion2(){
        return "2:" + readAndWriteObjectDao.getStringListBySql("SELECT VERSION()");
    }
}
