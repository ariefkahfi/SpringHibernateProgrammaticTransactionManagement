package com.arief.spring.daos.relations;

import com.arief.spring.models.Player;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class PlayerService {
    @Autowired
    private PlayerDAO playerDAO;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public void save(final Player p){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                  try {
                      playerDAO.save(p);
                  }catch (HibernateException ex){
                      transactionStatus.setRollbackOnly();
                  }
            }
        });
    }
    public Player getOne(final int playerId){
        return transactionTemplate
                .execute(new TransactionCallback<Player>() {
                    public Player doInTransaction(TransactionStatus transactionStatus) {
                        Player p = playerDAO.getOne(playerId);
                        return p;
                    }
                });
    }
    public void getOnePlayerAndUpdateName(final int playerId, final String newPlayerName){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Player getPlayer = playerDAO.getOne(playerId);
                getPlayer.setName(newPlayerName);
            }
        });
    }

}
