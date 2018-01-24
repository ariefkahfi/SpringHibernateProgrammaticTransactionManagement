package com.arief.spring.daos;

import com.arief.spring.models.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private TransactionTemplate transactionTemplate;


    public void save(final Person p){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    personDAO.save(p);
                    // PERSISTENCE STATE
                        p.setName("after personDAO.save");
                    // PERSISTENCE STATE
                    // PERSISTENCE STATE
                }catch (HibernateException ex){
                    transactionStatus.setRollbackOnly();
                }

                    // PERSISTENCE STATE if not thrown error
                    // PERSISTENCE STATE
                        // p.setName("after try catch save....");
                    // PERSISTENCE STATE
            }

        });

    }

    private void checkSession(Session s){
        if(s.isOpen()){
            System.out.println("session is opened");
        }
    }

}
