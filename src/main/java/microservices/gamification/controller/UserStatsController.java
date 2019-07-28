package microservices.gamification.controller;

import microservices.gamification.domain.GameStats;
import microservices.gamification.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class UserStatsController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public GameStats getStatesForUser(@RequestParam("userId") Long userId){
        return gameService.retrieveStatsForUser(userId);
    }
}
