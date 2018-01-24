package com.arief.spring.daos.relations;

import com.arief.spring.models.Player;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerDAOImpl implements PlayerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Player p) {
        sessionFactory
                .getCurrentSession()
                .save(p);
    }

    public List<Player> getAll() {
        return null;
    }

    public Player getOne(int playerId) {
        return sessionFactory
                    .getCurrentSession()
                    .get(Player.class,playerId);
    }

    public void update(Player p) {
        sessionFactory
                .getCurrentSession()
                .update(p);
    }
}
