package microservices.gamification.client;

import microservices.gamification.client.dto.MultiplicationResultAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MultiplicationResultAttemptClientImpl implements MultiplicationResultAttemptClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${multiplicationHost}")
    private String mulplicationHost;

    @Override
    public MultiplicationResultAttempt retrieveMultiplicationResultAttemptbyId(Long mulplicationId) {
        return restTemplate.getForObject(mulplicationHost+"/result/"+mulplicationId,
                MultiplicationResultAttempt.class);
    }
}
