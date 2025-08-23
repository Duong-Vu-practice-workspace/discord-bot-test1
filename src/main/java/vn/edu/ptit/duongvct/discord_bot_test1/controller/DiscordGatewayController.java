package vn.edu.ptit.duongvct.discord_bot_test1.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.duongvct.discord_bot_test1.service.DiscordGatewayManager;

@RestController
@AllArgsConstructor
@RequestMapping("/discord")
public class DiscordGatewayController {
    private final DiscordGatewayManager manager;

    @PostMapping("/reconnect")
    public ResponseEntity<String> reconnect() {
        manager.reconnect();
        return ResponseEntity.ok("reconnect triggered");
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok(manager.status());
    }
}