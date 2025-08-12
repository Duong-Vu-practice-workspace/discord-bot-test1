package vn.edu.ptit.duongvct.discord_bot_test1.common;

import java.util.Collections;
import java.util.Map;

public final class DiscordParameterType {
    public static final String SUB_COMMAND = "SUB_COMMAND";
    public static final String SUB_COMMAND_GROUP = "SUB_COMMAND_GROUP";
    public static final String STRING = "STRING";
    //	Any integer between -2^53 and 2^53
    public static final String INTEGER = "INTEGER";
    public static final String BOOLEAN = "BOOLEAN";
    public static final String USER = "USER";
    public static final String CHANNEL = "CHANNEL";
    public static final String ROLE = "ROLE";
    //	Includes users and roles
    public static final String MENTIONABLE = "MENTIONABLE";
    //	Any double between -2^53 and 2^53
    public static final String NUMBER = "NUMBER";
    //	attachment object
    public static final String ATTACHMENT = "ATTACHMENT";

    private static final Map<String, Integer> PARAMETER_TYPE_MAP = Collections.unmodifiableMap(Map.ofEntries(
            Map.entry(SUB_COMMAND,        1),
            Map.entry(SUB_COMMAND_GROUP,  2),
            Map.entry(STRING,             3),
            Map.entry(INTEGER,            4),
            Map.entry(BOOLEAN,            5),
            Map.entry(USER,               6),
            Map.entry(CHANNEL,            7),
            Map.entry(ROLE,               8),
            Map.entry(MENTIONABLE,        9),
            Map.entry(NUMBER,             10),
            Map.entry(ATTACHMENT,         11)
    ));

    private DiscordParameterType() {}

    public static int getType(String typeName) {
        return PARAMETER_TYPE_MAP.getOrDefault(typeName, 3); // Default to STRING type
    }

    public static Map<String, Integer> getTypeMap() {
        return PARAMETER_TYPE_MAP;
    }
}