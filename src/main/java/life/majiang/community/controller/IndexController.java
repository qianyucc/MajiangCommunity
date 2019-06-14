package life.majiang.community.controller;

import life.majiang.community.dto.*;
import life.majiang.community.mapper.*;
import life.majiang.community.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author lijing
 * @date 2019-05-26-22:26
 * @discroption
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        //查询问题列表
        PageInfoDTO questionList = questionService.list(page,size);
        model.addAttribute("questionList", questionList);
        return "index";
    }
}
