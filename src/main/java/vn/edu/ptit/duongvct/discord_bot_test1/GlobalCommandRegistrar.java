package vn.edu.ptit.duongvct.discord_bot_test1;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import vn.edu.ptit.duongvct.discord_bot_test1.common.*;

import java.util.Arrays;
import java.util.List;

@Component
public class GlobalCommandRegistrar implements ApplicationRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final RestClient client;

    public GlobalCommandRegistrar(RestClient client) {
        this.client = client;
    }

    @Override
    public void run(ApplicationArguments args) {
        final ApplicationService applicationService = client.getApplicationService();
        final long applicationId = client.getApplicationId().block();

        // Define your commands here
        List<ApplicationCommandRequest> commands = Arrays.asList(
                ApplicationCommandRequest.builder()
                        .name(SlashCommandCommon.GREET_COMMAND)
                        .description(GreetCommandCommon.GREET_COMMAND_DESCRIPTION)
                        .addOption(ApplicationCommandOptionData.builder()
                                .name(GreetCommandCommon.USER_PARAMETER)
                                .description(GreetCommandCommon.GreetParameterCommon.USER_PARAMETER_DESCRIPTION)
                                .type(DiscordParameterType.getType(DiscordParameterType.USER))
                                .required(true).build()
                        )
                        .build(),
                ApplicationCommandRequest.builder()
                        .name(SlashCommandCommon.PING_COMMAND)
                        .description(PingCommandCommon.PING_COMMAND_DESCRIPTION)
                        .build(),
                ApplicationCommandRequest.builder()
                        .name(SlashCommandCommon.GET_AVATAR_COMMAND)
                        .description(GetAvatarCommandCommon.GET_AVATAR_COMMAND_DESCRIPTION)
                        .addOption(ApplicationCommandOptionData.builder()
                                .name(GetAvatarCommandCommon.USER_PARAMETER)
                                .description(GetAvatarCommandCommon.GetAvatarParameter.USER_PARAMETER_DESCRIPTION)
                                .type(DiscordParameterType.getType(DiscordParameterType.USER))
                                .required(true)
                                .build())
                        .build()
        );

        applicationService.bulkOverwriteGlobalApplicationCommand(applicationId, commands)
                .doOnNext(ignore -> LOGGER.debug("Successfully registered Global Commands"))
                .doOnError(e -> LOGGER.error("Failed to register global commands", e))
                .subscribe();
    }
}