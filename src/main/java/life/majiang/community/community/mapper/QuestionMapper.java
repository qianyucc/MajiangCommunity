package life.majiang.community.community.mapper;

import life.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author lijing
 * @date 2019-06-01-15:43
 * @discroption
 */
@Mapper
@Component
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,like_count,comment_count,view_count,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{likeCount},#{commentCount},#{viewCount},#{creator},#{tag})")
    void insert(Question question);
}
