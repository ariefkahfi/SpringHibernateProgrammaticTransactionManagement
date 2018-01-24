package com.arief.spring.daos.relations;

import com.arief.spring.models.Player;
import com.arief.spring.models.Team;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamDAO teamDAO;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public void save(final Team t){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    teamDAO.save(t);
                }catch (HibernateException ex){
                    transactionStatus.setRollbackOnly();
                }
            }
        });
    }
    public Team getOne(final String teamId){
        return transactionTemplate.execute(new TransactionCallback<Team>() {
            public Team doInTransaction(TransactionStatus transactionStatus) {
                return teamDAO.getOne(teamId);
            }
        });
    }
    public List<Player> getPlayerListFromTeam(final String teamId){
        return transactionTemplate.execute(new TransactionCallback<List<Player>>() {
            public List<Player> doInTransaction(TransactionStatus transactionStatus) {
                Team getTeam = teamDAO.getOne(teamId);
                Hibernate.initialize(getTeam.getPlayerList());
                return getTeam.getPlayerList();
            }
        });
    }
}
