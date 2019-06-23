package life.majiang.community.mapper;

import life.majiang.community.model.*;

import java.util.*;

public interface QuestionExtMapper {

    void incView(Question question);

    void incCommentCount(Question question);

    List<Question> selectRelated(Question question);
}