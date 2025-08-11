package vn.edu.ptit.duongvct.discord_bot_test1.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class KeepAliveScheduler {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${keepalive.url}")
    private  String KEEPALIVE_URL;

    @Scheduled(fixedRate = 60000)
    public void pingSelf() {
        try {
            String response = restTemplate.getForObject(KEEPALIVE_URL, String.class);
            log.info("Call api endpoint to keep alive, response: {}", response);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
    }
}