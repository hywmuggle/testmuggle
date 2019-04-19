package com.jhtsoft.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;

/**
 * @ClassName: ReadAndWriteObjectDaoImpl
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
@Repository("readAndWriteObjectDao")
@Transactional(
        propagation = Propagation.REQUIRED
)
public class ReadAndWriteObjectDaoImpl extends AbstractReadAndWriteObjectDao {
    @PersistenceContext
    protected EntityManager manager;

    public ReadAndWriteObjectDaoImpl() {
    }

    public Session getCurrentSession() {
        return (Session)this.manager.getDelegate();
    }

    public Connection getConn() {
        Connection conn = (Connection)this.manager.unwrap(Connection.class);
        return conn;
    }
}
