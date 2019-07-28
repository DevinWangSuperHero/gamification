package microservices.gamification.service;

import microservices.gamification.domain.LeaderBoardRow;
import microservices.gamification.repository.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {
    @Autowired
    private ScoreCardRepository scoreCardRepository;
    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        List<LeaderBoardRow> leaderBoardRows = scoreCardRepository.findFirst10().stream().limit(10).collect(Collectors.toList());
        return leaderBoardRows;
    }
}
