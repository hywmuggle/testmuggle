////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//package com.jhtsoft.persistent;
//
//import com.alibaba.druid.filter.Filter;
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.wall.WallConfig;
//import com.alibaba.druid.wall.WallFilter;
//import java.util.ArrayList;
//import java.util.List;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "readandWriteentityManagerFactory",
//        basePackages = {"com.jhtsoft.dao"}
//)
//public class ReadAndWriteDateSourceConfig {
//    public ReadAndWriteDateSourceConfig() {
//    }
//
//    @Bean({"readAndWritedataSource"})
//    @ConfigurationProperties(
//            prefix = "spring.datasource.readandwrite"
//    )
//    @Primary
//    public DataSource dataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        List<Filter> filters = new ArrayList();
//        filters.add(this.getFilter());
//        druidDataSource.setProxyFilters(filters);
//        return druidDataSource;
//    }
//
//    public WallFilter getFilter() {
//        WallConfig config = new WallConfig();
//        config.setNoneBaseStatementAllow(true);
//        WallFilter filter = new WallFilter();
//        filter.setConfig(config);
//        return filter;
//    }
//
//    @Primary
//    @Bean({"readandWriteentityManagerFactory"})
//    public LocalContainerEntityManagerFactoryBean readandWriteentityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return builder.dataSource(this.dataSource()).packages(new String[]{"com.jht.entity"}).persistenceUnit("readAndwritePersistenceUnit").build();
//    }
//
//    @Primary
//    @Bean(
//            name = {"readandWriteTransactionManager"}
//    )
//    public PlatformTransactionManager readTransactionManager(@Qualifier("readandWriteentityManagerFactory") LocalContainerEntityManagerFactoryBean factory) {
//        return new JpaTransactionManager(factory.getObject());
//    }
//}
