package life.majiang.community.community.controller;

import life.majiang.community.community.dto.*;
import life.majiang.community.community.mapper.*;
import life.majiang.community.community.model.*;
import life.majiang.community.community.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author lijing
 * @date 2019-05-26-22:26
 * @discroption
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        //查询问题列表
                        List<QuestionDTO> questionList = questionService.list();
                        model.addAttribute("questions", questionList);
                    }
                    break;
                }
            }
        }

        return "index";
    }
}
