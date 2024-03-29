package microservices.gamification.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class MultiplicationSolvedEvent {
    private final Long multiplicationResultAttemptId;
    private final Long userId;
    private final boolean correct;

    public MultiplicationSolvedEvent() {
        this(-1L,-1L,false);
    }
}
