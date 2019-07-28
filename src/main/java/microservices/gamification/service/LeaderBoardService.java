package microservices.gamification.service;

import microservices.gamification.domain.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardService {
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
