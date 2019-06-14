package life.majiang.community.dto;

import life.majiang.community.model.*;
import lombok.*;

/**
 * @author lijing
 * @date 2019-06-09-19:46
 * @discroption
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;
    private Integer creator;
    private String tag;
    private User user;
}
