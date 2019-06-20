package life.majiang.community.advice;

import life.majiang.community.exception.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;

/**
 * @author lijing
 * @date 2019-06-20-17:08
 * @discroption
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request,Throwable ex,Model model){
        if(ex instanceof CustomizeException){
            model.addAttribute("message", ex.getMessage());
        }else{
            model.addAttribute("message", "服务器忙，请稍后再试！");
        }
        return new ModelAndView("error");
    }
}
