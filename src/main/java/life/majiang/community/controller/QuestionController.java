package life.majiang.community.controller;

import life.majiang.community.dto.*;
import life.majiang.community.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author lijing
 * @date 2019-06-14-15:27
 * @discroption
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);

        model.addAttribute("question", questionDTO);
        return "question";
    }
}
