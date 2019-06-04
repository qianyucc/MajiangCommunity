package life.majiang.community.community.dto;

import lombok.*;

/**
 * @author lijing
 * @date 2019-05-28-10:11
 * @discroption
 */
@Data
public class GithubUser {
    private String  name;
    private Long id;
    private String bio;
    private String avatar_url;
}
