package vn.edu.ptit.duongvct.discord_bot_test1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.duongvct.discord_bot_test1.service.DiscordMemberScanner;

@RestController
public class KeepAliveController {
    private final DiscordMemberScanner scanner;

    public KeepAliveController(DiscordMemberScanner scanner) {
        this.scanner = scanner;
    }

    @GetMapping("/keepalive")
    public String keepAlive() {
        return "OK";
    }
    @GetMapping("/{guildId}/save-members")
    public String saveMembers(@PathVariable Long guildId){
        return this.scanner.scanAndSaveMembers(guildId);
    }
}
