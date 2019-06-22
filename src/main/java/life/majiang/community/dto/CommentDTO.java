package life.majiang.community.dto;

import lombok.*;

/**
 * @author lijing
 * @date 2019-06-21-15:37
 * @discroption
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
