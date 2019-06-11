package life.majiang.community.community.mapper;

import life.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.*;

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

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();
}
