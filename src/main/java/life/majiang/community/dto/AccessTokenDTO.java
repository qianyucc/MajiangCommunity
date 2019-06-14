package life.majiang.community.dto;

import lombok.*;

/**
 * @author lijing
 * @date 2019-05-28-9:47
 * @discroption
 */

@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
