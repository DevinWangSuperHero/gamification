package microservices.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class ScoreCard {

    public static final long DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue
    @Column(name = "CARD_ID")
    private final Long cardId;

    @Column(name = "USER_ID")
    private final Long userId;

    @Column(name = "ATTEMPT_ID")
    private final Long attempId;

    @Column(name = "SCORE_TS")
    private final Long scoreTimestamp;

    @Column(name = "SCORE")
    private final Long score;

    public ScoreCard() {
        this(null,null,null,0L,0L);
    }

    public ScoreCard(Long userId, Long attempId) {
        this(null, userId, attempId, System.currentTimeMillis(), DEFAULT_SCORE);
    }
}
