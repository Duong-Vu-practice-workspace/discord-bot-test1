package vn.edu.ptit.duongvct.discord_bot_test1.common.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class GreetCommandCommon {
    public static final String USER_PARAMETER = "user";
    public static final String GREET_COMMAND_DESCRIPTION = "Greet a user";
    public static class GreetParameterCommon {
        public static final String USER_PARAMETER_DESCRIPTION = "User to greet";
    }
}
