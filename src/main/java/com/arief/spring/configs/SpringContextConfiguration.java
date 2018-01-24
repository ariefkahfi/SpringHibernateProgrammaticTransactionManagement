package com.arief.spring.configs;

import com.arief.spring.models.Person;
import com.arief.spring.models.Player;
import com.arief.spring.models.Team;
import org.hibernate.dialect.MySQL57Dialect;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Properties;

@Configuration
@ComponentScan(
        basePackages ={"com.arief.spring.daos"},
        basePackageClasses = {
                com.arief.spring.configs.SpringContextAware.class
        })
public class SpringContextConfiguration {


    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_tx2");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("arief");
        dataSource.setPassword("arief");
        return dataSource;
    }

    @Bean
    public Properties hibernateProps(){
        Properties props = new Properties();
        props.setProperty("hibernate.show_sql","true");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        props.setProperty("hibernate.hbm2ddl.auto","update");
        return props;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(hibernateProps());
        sessionFactoryBean.setAnnotatedClasses(
                Person.class,
                Team.class,
                Player.class
        );
        return sessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(){
        HibernateTransactionManager hibernateTransactionManager  =
                    new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactoryBean().getObject());
        return hibernateTransactionManager;
    }



    @Bean
    public TransactionTemplate transactionTemplate(){
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(hibernateTransactionManager());
        return transactionTemplate;
    }

}
