package life.majiang.community.community.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lijing
 * @date 2019-05-28-10:11
 * @discroption
 */
@Getter
@Setter
@ToString
public class GithubUser {
    private String  name;
    private Long id;
    private String bio;
}
