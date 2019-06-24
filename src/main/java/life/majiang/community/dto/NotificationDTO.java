package life.majiang.community.dto;

import lombok.*;

/**
 * @author lijing
 * @date 2019-06-24-17:33
 * @discroption
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
