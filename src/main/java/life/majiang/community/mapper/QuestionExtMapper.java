package life.majiang.community.mapper;

import life.majiang.community.model.*;

public interface QuestionExtMapper {

    void incView(Question question);

    void incCommentCount(Question question);
}