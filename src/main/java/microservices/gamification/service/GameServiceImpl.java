package microservices.gamification.service;

import lombok.extern.slf4j.Slf4j;
import microservices.gamification.client.MultiplicationResultAttemptClient;
import microservices.gamification.client.dto.MultiplicationResultAttempt;
import microservices.gamification.domain.Badge;
import microservices.gamification.domain.BadgeCard;
import microservices.gamification.domain.GameStats;
import microservices.gamification.domain.ScoreCard;
import microservices.gamification.repository.BadgeCardRepository;
import microservices.gamification.repository.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    private ScoreCardRepository scoreCardRepository;

    @Autowired
    private BadgeCardRepository badgeCardRepository;

    @Autowired
    private MultiplicationResultAttemptClient multiplicationResultAttemptClient;

    @Override
    public GameStats newAttemptForUser(Long userId, Long attempId, boolean correct) {
        if (correct) {
            ScoreCard scoreCard = new ScoreCard(userId,attempId);
            scoreCardRepository.save(scoreCard);
            log.info("User with id {} scored {} points for attempt id {}", userId, scoreCard.getScore(), attempId);
            List<BadgeCard> badgeCards = processForBadges(userId, attempId);
            return new GameStats(userId, scoreCard.getScore(),badgeCards.stream().map(p -> p.getBadge()).collect(Collectors.toList()));
        }
        return GameStats.emptyStats(userId);
    }
    private List<BadgeCard> processForBadges(final Long userId, final Long attemptId) {
        List<BadgeCard> badgeCards = new ArrayList<>();

        long totalScore = scoreCardRepository.getTotalScoreForUser(userId);
        log.info("New score for user {} is {}", userId, totalScore);

        List<ScoreCard> scoreCards = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        List<BadgeCard> badgeCardList = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);

        checkAndGiveBadgeBasedOnScore(badgeCardList,Badge.BRONZE_MULTIPLICATION, totalScore, 100, userId).ifPresent(p -> badgeCards.add(p));
        checkAndGiveBadgeBasedOnScore(badgeCardList,Badge.SILVER_MULTIPLICATION, totalScore, 500, userId).ifPresent(p -> badgeCards.add(p));
        checkAndGiveBadgeBasedOnScore(badgeCardList,Badge.GOLD_MULPLICATION, totalScore, 999, userId).ifPresent(p -> badgeCards.add(p));

        if (scoreCards.size() == 1 && !containsBadge(badgeCardList,Badge.FIRST_WON)) {
            badgeCards.add(giveBadgeToUser(Badge.FIRST_WON, userId));
        }
        MultiplicationResultAttempt multiplicationResultAttempt = multiplicationResultAttemptClient.retrieveMultiplicationResultAttemptbyId(attemptId);
        if (!containsBadge(badgeCards,Badge.LUCKY_NUMBER) && (multiplicationResultAttempt.getMultiplicationFactorA()==42 || multiplicationResultAttempt.getMultiplicationFactorB() == 42)) {
            badgeCards.add(giveBadgeToUser(Badge.LUCKY_NUMBER,userId));
        }
        return badgeCards;
    }

    private Optional<BadgeCard> checkAndGiveBadgeBasedOnScore(final List<BadgeCard> badgeCards, final Badge badge,
                                                              final long score, final int scoreThreshold, final Long userId) {
        if (score >= scoreThreshold && !containsBadge(badgeCards,badge)) {
            return Optional.of(giveBadgeToUser(badge,userId));
        }
        return Optional.empty();
    }

    private BadgeCard giveBadgeToUser(final Badge badge, final Long userId){
        BadgeCard card = new BadgeCard(userId, badge);
        badgeCardRepository.save(card);
        log.info("User with id {} won a new badge: {}",userId, badge);
        return card;
    }

    private boolean containsBadge(final List<BadgeCard> badgeCards, final Badge badge) {
        return badgeCards.stream().allMatch(p -> p.getBadge().equals(badge));
    }

    @Override
    public GameStats retrieveStatsForUser(Long userId) {
        long score = scoreCardRepository.getTotalScoreForUser(userId);
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
        return new GameStats(userId, score, badgeCards.stream().map(p -> p.getBadge()).collect(Collectors.toList()));
    }
}
