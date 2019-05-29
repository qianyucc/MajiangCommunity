package life.majiang.community.community.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lijing
 * @date 2019-05-29-11:28
 * @discroption
 */
@Setter
@Getter
@ToString
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
}
