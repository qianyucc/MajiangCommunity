package life.majiang.community.dto;

import life.majiang.community.exception.*;

/**
 * @author lijing
 * @date 2019-06-21-15:54
 * @discroption
 */
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
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.code = 200;
        resultDTO.message = "操作成功";
        return resultDTO;
    }

    public static ResultDTO errorOf(ICustomizeErrorCode ex) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.code = ex.getCode();
        resultDTO.message = ex.getMessage();
        return resultDTO;
    }
}
