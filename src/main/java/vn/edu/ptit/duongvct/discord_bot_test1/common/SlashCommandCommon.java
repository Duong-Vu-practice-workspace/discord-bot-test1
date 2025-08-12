package vn.edu.ptit.duongvct.discord_bot_test1.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public final class SlashCommandCommon {
    public static final String GREET_COMMAND = "greet";
    public static final String PING_COMMAND = "ping";
    public static final String GET_AVATAR_COMMAND = "get-avatar";
    //topic
    public static final String CREATE_TOPIC_COMMAND = "create-topic";
    public static final String EDIT_TOPIC_COMMAND = "edit-topic";
    public static final String GET_TOPIC_COMMAND = "get-topic";
    public static final String DELETE_TOPIC_COMMAND = "delete-topic";
    //event
    public static final String CREATE_EVENT_COMMAND = "create-event";
    public static final String EDIT_EVENT_COMMAND = "edit-event";
    public static final String GET_EVENT_COMMAND = "get-event";
    public static final String DELETE_EVENT_COMMAND = "delete-event";
    //course
    public static final String CREATE_COURSE_COMMAND = "create-course";
    //resource
    public static final String CREATE_RESOURCE_COMMAND = "create-resource";
}
