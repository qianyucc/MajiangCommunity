package life.majiang.community.interceptor;

import life.majiang.community.enums.*;
import life.majiang.community.mapper.*;
import life.majiang.community.model.*;
import life.majiang.community.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;
import java.util.*;

/**
 * @author lijing
 * @date 2019-06-14-14:30
 * @discroption
 */
@Service
public class SessionInterveptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationService notificationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria()
                            .andTokenEqualTo(token);
                    List<User> users = userMapper.selectByExample(userExample);
                    if (users.size() != 0) {
                        User user = users.get(0);
                        request.getSession().setAttribute("user", user);
                        long unreadNotificationCount = notificationService.getUnreadNotificationCount(user);
                        request.getSession().setAttribute("unreadNotificationCount", unreadNotificationCount);
                    }
                    break;
                }
            }
        }
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
