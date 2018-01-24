package com.arief.spring.daos.relations;

import com.arief.spring.models.Player;

import java.util.List;

public interface PlayerDAO {
    void save(Player p);
    List<Player> getAll();
    Player getOne(int playerId);
    void update(Player p);
}
