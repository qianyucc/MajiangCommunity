package life.majiang.community.service;

import life.majiang.community.dto.*;
import life.majiang.community.mapper.*;
import life.majiang.community.model.*;
import org.apache.ibatis.session.*;
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
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        // 计算总页数
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        if (page < 0) {
            page = 1;
        }

        List<QuestionDTO> questionDTOS = new ArrayList<>();

        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setQuestions(questionDTOS);
        pageInfoDTO.setPageInfo(totalPage, page);

        //计算offset和size
        Integer offset = size * (page - 1);

        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset, size));
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }


        return pageInfoDTO;
    }

    public PageInfoDTO list(Integer id, Integer page, Integer size) {

        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(id);
        Integer totalCount = (int)questionMapper.countByExample(example);
        // 计算总页数
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        if (page < 0) {
            page = 1;
        }

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setQuestions(questionDTOS);
        pageInfoDTO.setPageInfo(totalPage, page);

        //计算offset和size
        Integer offset = size * (page - 1);

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));

        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        return pageInfoDTO;
    }

    public QuestionDTO getById(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void insertOrUpdate(Question question) {
        if (question.getId() != null) {
            // 更新
            Question updQuestion = new Question();
            updQuestion.setGmtModified(System.currentTimeMillis());
            updQuestion.setTitle(question.getTitle());
            updQuestion.setDescription(question.getDescription());
            updQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updQuestion, example);
        } else {
            // 添加
            questionMapper.insert(question);
        }
    }
}
