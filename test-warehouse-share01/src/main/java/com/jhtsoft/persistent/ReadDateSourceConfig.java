////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//package com.jhtsoft.persistent;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "readentityManagerFactory",
//        basePackages = {"com.jhtsoft.dao"},
//        transactionManagerRef = "readTransactionManager"
//)
//public class ReadDateSourceConfig {
//    public ReadDateSourceConfig() {
//    }
//
//    @Bean({"readdataSource"})
//    @ConfigurationProperties(
//            prefix = "spring.datasource.read"
//    )
//    public DataSource dataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        return druidDataSource;
//    }
//
//    @Bean({"readentityManagerFactory"})
//    public LocalContainerEntityManagerFactoryBean readentityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return builder.dataSource(this.dataSource()).packages(new String[]{"com.jht.entity"}).persistenceUnit("readPersistenceUnit").build();
//    }
//
//    @Bean(
//            name = {"readTransactionManager"}
//    )
//    public PlatformTransactionManager readTransactionManager(@Qualifier("readentityManagerFactory") LocalContainerEntityManagerFactoryBean factory) {
//        return new JpaTransactionManager(factory.getObject());
//    }
//}
