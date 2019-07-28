package microservices.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class GameStats {
    private final Long userId;
    private final Long score;
    private final List<Badge> badges;

    public GameStats() {
        this.userId = 0L;
        this.score = 0L;
        this.badges = new ArrayList<>();
    }

    public static GameStats emptyStats(final Long userId) {
        return new GameStats(userId, 0L, Collections.EMPTY_LIST);
    }

    public List<Badge> getBadges() {
        return Collections.unmodifiableList(badges);
    }
}
