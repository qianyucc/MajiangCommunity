package life.majiang.community.controller;

import life.majiang.community.dto.*;
import life.majiang.community.enums.*;
import life.majiang.community.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author lijing
 * @date 2019-06-14-15:27
 * @discroption
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("question/{id}")
    public String question(@PathVariable("id") Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentDTO> comments =  commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        List<QuestionDTO> relatedQuestions =  questionService.selectRelated(questionDTO);
        // 增加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
