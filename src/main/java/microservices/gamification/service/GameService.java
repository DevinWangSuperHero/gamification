package microservices.gamification.service;

import microservices.gamification.domain.GameStats;

public interface GameService {
    GameStats newAttemptForUser(Long userId, Long attempId, boolean correct);
    GameStats retrieveStatsForUser(Long userId);
}
