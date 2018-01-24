package com.arief.spring.daos;

import com.arief.spring.models.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;


@Repository
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Person p) {
        sessionFactory
                .getCurrentSession()
                .save(p);
    }

    public List<Person> getAll() {
        return null;
    }

    public Person getOne(int personId) {
        return
                sessionFactory
                    .getCurrentSession()
                    .get(Person.class,personId);
    }

    public void update(Person p) {
        sessionFactory
                .getCurrentSession()
                .update(p);
    }
}
