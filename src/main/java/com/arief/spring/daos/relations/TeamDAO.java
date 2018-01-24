package com.arief.spring.daos.relations;

import com.arief.spring.models.Team;

import java.util.List;

public interface TeamDAO {
    void save(Team t);
    List<Team> getAll();
    Team getOne(String teamId);
    void update(Team t);
}
