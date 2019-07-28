package microservices.gamification.repository;

import microservices.gamification.domain.LeaderBoardRow;
import microservices.gamification.domain.ScoreCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {

    @Query("SELECT SUM(s.score) FROM microservices.gamification.domain.ScoreCard as s where s.userId = :userId GROUP BY s.score")
    long getTotalScoreForUser(@Param("userId") Long userId);

    @Query("SELECT new microservices.gamification.domain.LeaderBoardRow(s.userId,SUM(s.score)) FROM microservices.gamification.domain.ScoreCard as s GROUP BY s.userId ORDER BY SUM(s.score) DESC ")
    List<LeaderBoardRow> findFirst10();

    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final Long timeStamp);
}
