package life.majiang.community.community.model;

import lombok.*;

/**
 * @author lijing
 * @date 2019-05-29-11:28
 * @discroption
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
