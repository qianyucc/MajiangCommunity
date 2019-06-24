package life.majiang.community.controller;

import life.majiang.community.dto.*;
import life.majiang.community.mapper.*;
import life.majiang.community.model.*;
import life.majiang.community.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

/**
 * @author lijing
 * @date 2019-06-12-18:49
 * @discroption
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationMapper notificationMapper;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("selection", "questions");
            model.addAttribute("selectionName", "我的问题");

            PageInfoDTO questionList = questionService.list(user.getId(), page, size);
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andCreatorEqualTo(user.getId());
            long count = questionMapper.countByExample(questionExample);
            model.addAttribute("questionList", questionList);
            model.addAttribute("count", count);
        }
        if("replies".equals(action)){
            model.addAttribute("selection", "replies");
            model.addAttribute("selectionName", "最新回复");
            PageInfoDTO notificationList = notificationService.list(user.getId(), page, size);
            NotificationExample notificationExample = new NotificationExample();
            notificationExample.createCriteria()
                    .andReceiverEqualTo(user.getId());
            long notificationCount = notificationMapper.countByExample(notificationExample);
            model.addAttribute("notificationList", notificationList);
            model.addAttribute("notificationCount", notificationCount);
        }


        return "profile";
    }
}
