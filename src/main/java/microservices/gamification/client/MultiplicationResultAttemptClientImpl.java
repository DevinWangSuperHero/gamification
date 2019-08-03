package microservices.gamification.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "multiplicationResultAttempt")
    public MultiplicationResultAttempt retrieveMultiplicationResultAttemptbyId(Long mulplicationId) {
        return restTemplate.getForObject(mulplicationHost+"/results/"+mulplicationId,
                MultiplicationResultAttempt.class);
    }

    private MultiplicationResultAttempt multiplicationResultAttempt(){
        return new MultiplicationResultAttempt("fakeAlia",10,10,100,true);
    }
}
