package com.arief.spring.main;

import com.arief.spring.configs.SpringContextConfiguration;
import com.arief.spring.daos.PersonDAO;
import com.arief.spring.daos.PersonDAOImpl;
import com.arief.spring.daos.PersonService;
import com.arief.spring.daos.PersonServiceWithTransactionManager;
import com.arief.spring.daos.relations.PlayerService;
import com.arief.spring.daos.relations.PlayerTeamService;
import com.arief.spring.daos.relations.TeamService;
import com.arief.spring.models.Person;
import com.arief.spring.models.Player;
import com.arief.spring.models.Team;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class SpringMainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext =
                    new AnnotationConfigApplicationContext(SpringContextConfiguration.class);

        PlayerService playerService = appContext.getBean(PlayerService.class);
        TeamService teamService = appContext.getBean(TeamService.class);
        PlayerTeamService playerTeamService = appContext.getBean(PlayerTeamService.class);

    }
}
