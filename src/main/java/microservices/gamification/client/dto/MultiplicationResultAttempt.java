package microservices.gamification.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import microservices.gamification.client.MultiplicationResultAttemptDeserializer;

@RequiredArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
@JsonDeserialize(using = MultiplicationResultAttemptDeserializer.class)
public class MultiplicationResultAttempt {

    private final String userAlias;
    private final int multiplicationFactorA;
    private final int multiplicationFactorB;
    private final int resultAttempt;

    private final boolean correct;

    public MultiplicationResultAttempt() {
        this.multiplicationFactorA = -1;
        this.multiplicationFactorB = -1;
        this.correct = false;
        this.userAlias = null;
        this.resultAttempt = -1;
    }
}
