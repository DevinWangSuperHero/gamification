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
@ToString
@EqualsAndHashCode
@Getter
@Entity
public final class BadgeCard {
    @Id
    @GeneratedValue
    @Column(name = "BADGE_ID")
    private final Long badgeId;

    private final Long userId;
    private final Long badgeTimestamp;
    private final Badge badge;

    public BadgeCard() {
        this(null, null, 0L, null);
    }
    public BadgeCard(Long userId, Badge badge) {
        this(null,userId,System.currentTimeMillis(),badge);
    }
}
