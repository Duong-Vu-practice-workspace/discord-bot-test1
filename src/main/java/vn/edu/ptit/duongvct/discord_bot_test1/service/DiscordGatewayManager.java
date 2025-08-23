package vn.edu.ptit.duongvct.discord_bot_test1.service;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.rest.RestClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.edu.ptit.duongvct.discord_bot_test1.autocomplete.AutoCompleteHandler;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.exception.DiscordExceptionHandler;
import vn.edu.ptit.duongvct.discord_bot_test1.listeners.SlashCommandListener;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscordGatewayManager {

    @Value("${discord.token}")
    private String token;

    private final List<SlashCommand> slashCommands;
    private final List<AutoCompleteHandler> autoCompleteHandlers;
    private final DiscordExceptionHandler discordExceptionHandler;

    private final AtomicReference<GatewayDiscordClient> clientRef = new AtomicReference<>();
    private volatile SlashCommandListener listener;

    @PostConstruct
    public void start() {
        connect();
    }

    public synchronized void connect() {
        try {
            // if already connected, skip
            GatewayDiscordClient existing = clientRef.get();
            if (existing != null) {
                try {
                    existing.getRestClient(); // touch to see if usable
                } catch (Exception ignored) {}
            }

            log.info("Connecting to Discord...");
            GatewayDiscordClient client = DiscordClientBuilder.create(token)
                    .build()
                    .gateway()
                    .login()
                    .block();

            if (client == null) {
                log.error("Failed to create GatewayDiscordClient (null).");
                return;
            }

            // replace reference
            clientRef.set(client);

            // create and register listener for this client
            listener = new SlashCommandListener(slashCommands, autoCompleteHandlers, discordExceptionHandler);
            listener.register(client);

            log.info("Discord client connected and listeners registered.");
        } catch (Exception e) {
            log.error("Error while connecting Discord client", e);
        }
    }

    public synchronized void reconnect() {
        try {
            GatewayDiscordClient old = clientRef.getAndSet(null);
            if (old != null) {
                try {
                    log.info("Logging out old Discord client...");
                    old.logout().block();
                } catch (Exception e) {
                    log.warn("Error while logging out old client", e);
                }
            }
        } finally {
            // ensure we always try to create a new client
            connect();
        }
    }

    public RestClient getRestClient() {
        GatewayDiscordClient c = clientRef.get();
        if (c == null) throw new IllegalStateException("Discord client not connected");
        return c.getRestClient();
    }

    public String status() {
        GatewayDiscordClient c = clientRef.get();
        return (c != null) ? "connected" : "disconnected";
    }

    // scheduled reconnect every 1 minute (60000 ms)
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void scheduledReconnect() {
        log.info("Scheduled reconnect triggered");
        reconnect();
    }
}