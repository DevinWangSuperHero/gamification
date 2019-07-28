package microservices.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class LeaderBoardRow {
    private final Long userId;
    private final Long totalScore;

    public LeaderBoardRow() {
        this(0L,0L);
    }
}
