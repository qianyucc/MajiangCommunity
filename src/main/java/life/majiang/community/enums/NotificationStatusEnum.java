package life.majiang.community.enums;

/**
 * @author lijing
 * @date 2019-06-24-17:21
 * @discroption
 */
public enum NotificationStatusEnum {
    READ(1),
    UNREAD(0);
    private Integer status;

    NotificationStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
