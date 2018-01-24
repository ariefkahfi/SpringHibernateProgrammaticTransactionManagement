package com.arief.spring.daos.relations;

import com.arief.spring.models.Player;
import com.arief.spring.models.Team;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class PlayerTeamService {
    @Autowired
    private PlayerDAO playerDAO;
    @Autowired
    private TeamDAO teamDAO;
    @Autowired
    private TransactionTemplate transactionTemplate;


    public void removePlayerFromTeam(final String teamId, final int playerIdToRemove){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Team getTeam = teamDAO.getOne(teamId);
                Player getPlayer = playerDAO.getOne(playerIdToRemove);
                boolean found = false;
                int whichIndex = 0;

                getTeam.getPlayerList().remove(getPlayer);


            }
        });
    }
    public void updatePlayerTeam(final int playerId , final String toTeamId){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Player p = playerDAO.getOne(playerId);
                Team getTeam = teamDAO.getOne(toTeamId);
                p.setTeam(getTeam);
            }
        });
    }

}
