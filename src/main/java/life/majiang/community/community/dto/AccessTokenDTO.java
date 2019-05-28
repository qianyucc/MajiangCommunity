package life.majiang.community.community.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lijing
 * @date 2019-05-28-9:47
 * @discroption
 */

@Setter
@Getter
@ToString
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
