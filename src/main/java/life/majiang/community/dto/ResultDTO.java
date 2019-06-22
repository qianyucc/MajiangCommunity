package life.majiang.community.dto;

import life.majiang.community.exception.*;
import lombok.*;

/**
 * @author lijing
 * @date 2019-06-21-15:54
 * @discroption
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.code = code;
        resultDTO.message = message;
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode noLogin) {
        return errorOf(noLogin.getCode(),noLogin.getMessage());
    }

    public static ResultDTO okOf() {
        return errorOf(200,"操作成功");
    }

    public static ResultDTO errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(),ex.getMessage());
    }
}
