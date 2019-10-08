//package com.leo.app.config;
//
//
//import com.leo.app.db.DbContextHolder;
//import com.leo.app.db.MasterSlaveRoutingDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//
//
//@Configuration
//@EnableTransactionManagement
//@RefreshScope
//public class DruidDBConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);
//
//
//    @Value("${mybatis.type-aliases-package}")
//    private String typeAliasesPackage;
//
//    //  配置mapper的扫描，找到所有的mapper.xml映射文件
//    @Value("${mybatis.mapper-locations}")
//    private String mapperLocations;
//
//    @Value("${druid.type}")
//    private Class<? extends DataSource> dataSourceType;
//
//    @Bean("masterDataSource")
//    @ConfigurationProperties(prefix = "druid.cloud.master")
//    public  DataSource  masterDataSource (){
//        return DataSourceBuilder.create().type(dataSourceType).build();
//    }
//
//    @Bean("slaveDataSource")
//    @ConfigurationProperties(prefix = "druid.cloud.slave")
//    public  DataSource  slaveDataSource (){
//        return DataSourceBuilder.create().type(dataSourceType).build();
//    }
//
//    @Bean(name = "dataSource")
//    @Primary
//    public AbstractRoutingDataSource dataSource() {
//        MasterSlaveRoutingDataSource proxy = new MasterSlaveRoutingDataSource();
//        Map<Object, Object> targetDataResources = new HashMap<>();
//        targetDataResources.put(DbContextHolder.DbType.Master, masterDataSource());
//        targetDataResources.put(DbContextHolder.DbType.Slave, slaveDataSource());
//        proxy.setDefaultTargetDataSource(masterDataSource());
//        proxy.setTargetDataSources(targetDataResources);
//        proxy.afterPropertiesSet();
//        return proxy;
//    }
//
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource ) throws Exception {
//       SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
//       sqlSessionFactoryBean.setDataSource(dataSource);
//       Resource[] resource = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
//       sqlSessionFactoryBean.setMapperLocations(resource);
//       return  sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource){
//        return  new DataSourceTransactionManager(dataSource);
//
//    }
//
//
//
//   /* @Bean
//    public ServletRegistrationBean druidServlet() {
//        logger.info("init Druid Servlet Configuration ");
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        // IP白名单
//        servletRegistrationBean.addInitParameter("allow", "");
//        // IP黑名单(共同存在时，deny优先于allow)
//        //servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
//        //控制台管理用户
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//        servletRegistrationBean.addInitParameter("loginPassword", "admin");
//        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
//        servletRegistrationBean.addInitParameter("resetEnable", "false");
//
//        return servletRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        return filterRegistrationBean;
//
//    }
//    @Bean     //声明其为Bean实例
//    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
//    public DataSource dataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl(url);
//        datasource.setUsername(username);
//        datasource.setPassword(password);
//        datasource.setDriverClassName(driverClassName);
//
//        //configuration
//        datasource.setInitialSize(initialSize);
//        datasource.setMinIdle(minIdle);
//        datasource.setMaxActive(maxActive);
//        datasource.setMaxWait(maxWait);
//        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        datasource.setValidationQuery(validationQuery);
//        datasource.setTestWhileIdle(testWhileIdle);
//        datasource.setTestOnBorrow(testOnBorrow);
//        datasource.setTestOnReturn(testOnReturn);
//        datasource.setPoolPreparedStatements(poolPreparedStatements);
//        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//        try {
//            datasource.setFilters(filters);
//        } catch (SQLException e) {
//            System.err.println("druid configuration initialization filter: " + e);
//        }
//        datasource.setConnectionProperties(connectionProperties);
//        return datasource;
//    }
//
//    private String url;
//
//    private String username;
//
//    private String password;
//
//    private String driverClassName;
//
//    private int initialSize;
//
//    private int minIdle;
//
//    private int maxActive;
//
//    private int maxWait;
//
//    private int timeBetweenEvictionRunsMillis;
//
//    private int minEvictableIdleTimeMillis;
//
//    private String validationQuery;
//
//    private boolean testWhileIdle;
//
//    private boolean testOnBorrow;
//
//    private boolean testOnReturn;
//
//    private boolean poolPreparedStatements;
//
//    private int maxPoolPreparedStatementPerConnectionSize;
//
//    private String filters;
//
//    private String connectionProperties;*/
//
//}
