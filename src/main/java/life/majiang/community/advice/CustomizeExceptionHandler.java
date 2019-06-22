package life.majiang.community.advice;

import com.alibaba.fastjson.*;
import life.majiang.community.dto.*;
import life.majiang.community.exception.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;
import java.io.*;

/**
 * @author lijing
 * @date 2019-06-20-17:08
 * @discroption
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request,
                        HttpServletResponse response,
                        Throwable ex,
                        Model model) {
        String contentType = request.getContentType();
        ResultDTO resultDTO = null;
        if (contentType != null && contentType.equals("application/json")) {
            if (ex instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) ex);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(200);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }

            return null;
        } else {
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
