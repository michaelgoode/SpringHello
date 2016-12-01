package com.springapp.com.springapp.config;

import javax.sql.DataSource;

import com.springapp.dao.*;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@ComponentScan(basePackages="com.springapp")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public DataSource getMSDataSource() {
        return getDataSource("net.sourceforge.jtds.jdbcx.JtdsDataSource",
                "jdbc:jtds:sqlserver://192.168.2.9/MS_Production",
                "mike","Pass123");
    }

    @Bean
    public DataSource getLookupDataSource() {
        return getDataSource("net.sourceforge.jtds.jdbcx.JtdsDataSource",
                "jdbc:jtds:sqlserver://192.168.0.65:1489/lookup",
                "lookup_admin","sbit");
    }

    private DataSource getDataSource(String driver, String url, String user, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

//    @Bean
//    public DataSource getUtileMachineDataSource() {
//        try {
//            DriverManagerDataSource dataSource = new DriverManagerDataSource();
//            dataSource.setDriverClassName("net.ucanaccess.jdbc.UcanaccessDriver");
//            dataSource.setUrl("jdbc:ucanaccess://C:/Users/michaelgoode/Documents/Utile Engineering/DB/MachineSelectorData.mdb");
//            return dataSource;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }

    @Bean
    public HeaderDAO getHeaderDAO() {
        return new HeaderDAOImpl(getMSDataSource());
    }

    @Bean
    public EPCDAO getEPCDAO() {
        return new EPCDAOImpl(getMSDataSource());
    }

    @Bean
    public ContractDAO getContractDAO() {
        return new ContractDAOImpl(getMSDataSource());
    }

    @Bean
    public AssociationBatchDAO getAssociationBatchDAO() {
        return new AssociationBatchDAOImpl(getMSDataSource());
    }

    @Bean
    public UnassociatedReportDAO getUnassociatedReportDAO() { return new UnassociatedReportDAOImpl(getMSDataSource()); }

    @Bean
    public BurberryShoeDAO getBurberryShoeDAO() { return new BurberryShoeDAOImpl(getLookupDataSource()); }

}
