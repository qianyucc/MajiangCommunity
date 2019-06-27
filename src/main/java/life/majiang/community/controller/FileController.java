package life.majiang.community.controller;

import life.majiang.community.dto.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author lijing
 * @date 2019-06-27-11:06
 * @discroption
 */
@Controller
public class FileController {
    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("message");
        fileDTO.setUrl("/images/weChat.jpg");
        return fileDTO;
    }
}
