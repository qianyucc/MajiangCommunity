package life.majiang.community.enums;

/**
 * @author lijing
 * @date 2019-06-24-17:24
 * @discroption
 */
public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论")
        ;

    private Integer type;
    private String massage;

    public Integer getType() {
        return type;
    }

    public String getMassage() {
        return massage;
    }

    NotificationTypeEnum(Integer type, String massage) {
        this.type = type;
        this.massage = massage;
    }

    public static String nameOfType(Integer type){
        return type == NotificationTypeEnum.REPLY_COMMENT.getType()
                ? NotificationTypeEnum.REPLY_COMMENT.getMassage() : NotificationTypeEnum.REPLY_QUESTION.getMassage();
    }
}
