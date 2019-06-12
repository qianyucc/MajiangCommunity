package life.majiang.community.community.model;

import lombok.*;

/**
 * @author lijing
 * @date 2019-06-01-15:48
 * @discroption
 */
@Data
public class Question {
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
}