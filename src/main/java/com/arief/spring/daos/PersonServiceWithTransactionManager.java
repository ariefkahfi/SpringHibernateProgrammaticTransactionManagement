package com.arief.spring.daos;

import com.arief.spring.models.Person;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PersonServiceWithTransactionManager {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private PlatformTransactionManager transactionManager;

    private static final Logger LOGGER = Logger.getLogger(PersonServiceWithTransactionManager.class.getName());


    public void save(Person p ){
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            personDAO.save(p);
            // PERSISTENCE STATE
                p.setName("Hello world after personDAO.save");
            // PERSISTENCE STATE
            transactionManager.commit(transactionStatus);
            LOGGER.info("after commit....");
        }catch (HibernateException ex){
            transactionManager.rollback(transactionStatus);
            LOGGER.log(Level.SEVERE,"rollback transaction ....");
        }

    }

    public void update(Person p){
        DefaultTransactionDefinition transactionDefinition  =
                new DefaultTransactionDefinition();
        TransactionStatus transactionStatus  = transactionManager.getTransaction(transactionDefinition);
        try {
            personDAO.update(p);
            p.setName("putro after update first and before commit ....");
            p.setName("putro after update second and before commit....");
            transactionManager.commit(transactionStatus);
            LOGGER.info("after commit....");
        }catch (HibernateException ex){
            transactionManager.rollback(transactionStatus);
            LOGGER.log(Level.SEVERE,"rollback transaction ....");
        }
    }
}
