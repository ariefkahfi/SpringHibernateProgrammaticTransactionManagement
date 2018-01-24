package com.arief.spring.daos.relations;

import com.arief.spring.models.Team;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamDAOImpl implements TeamDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Team t) {
        sessionFactory
                .getCurrentSession()
                .save(t);
    }

    public List<Team> getAll() {
        return null;
    }

    public Team getOne(String teamId) {
        return sessionFactory
                    .getCurrentSession()
                    .get(Team.class,teamId);
    }

    public void update(Team t) {
        sessionFactory
                .getCurrentSession()
                .update(t);
    }
}
