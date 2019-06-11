package life.majiang.community.community.service;

import life.majiang.community.community.dto.*;
import life.majiang.community.community.mapper.*;
import life.majiang.community.community.model.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author lijing
 * @date 2019-06-09-19:50
 * @discroption
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PageInfoDTO list(Integer page, Integer size) {
        //计算offset和size
        Integer offset = size * (page - 1);

        List<Question> questionList = questionMapper.list(offset, size);
        PageInfoDTO pageInfoDTO = new PageInfoDTO();

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        pageInfoDTO.setQuestions(questionDTOS);
        Integer totalCount = questionMapper.count();
        pageInfoDTO.setPageInfo(totalCount, page, size);
        return pageInfoDTO;
    }
}
