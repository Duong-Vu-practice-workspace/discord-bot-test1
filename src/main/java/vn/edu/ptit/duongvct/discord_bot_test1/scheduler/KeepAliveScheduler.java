package vn.edu.ptit.duongvct.discord_bot_test1.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeepAliveScheduler {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${keepalive.url}")
    private  String KEEPALIVE_URL;

    @Scheduled(fixedRate = 60000)
    public void pingSelf() {
        try {
            restTemplate.getForObject(KEEPALIVE_URL, String.class);
        } catch (Exception e) {
        }
    }
}