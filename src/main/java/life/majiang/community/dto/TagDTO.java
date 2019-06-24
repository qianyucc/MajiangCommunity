package life.majiang.community.dto;

import lombok.*;

import java.util.*;

/**
 * @author lijing
 * @date 2019-06-23-17:05
 * @discroption
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
