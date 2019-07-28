package microservices.gamification.client;

import microservices.gamification.client.dto.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptClient {

    MultiplicationResultAttempt retrieveMultiplicationResultAttemptbyId(final Long mulplicationId);
}
