package com.jhtsoft.dao.impl;

import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;

/**
 * @ClassName: ReadObjectDaoImpl
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
@Repository("readObjectDao")
@Scope("prototype")
@Transactional(
        propagation = Propagation.REQUIRED
)
public class ReadObjectDaoImpl extends AbstractReadObjectDao {
    @PersistenceContext//(unitName = "readPersistenceUnit")
    protected EntityManager manager;

    public ReadObjectDaoImpl() {
    }

    public Session getCurrentSession() {
        return (Session)this.manager.getDelegate();
    }

    public Connection getConn() {
        Connection conn = (Connection)this.manager.unwrap(Connection.class);
        return conn;
    }
}
