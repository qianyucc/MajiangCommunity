package life.majiang.community.exception;

import com.sun.xml.internal.bind.v2.model.core.*;

/**
 * @author lijing
 * @date 2019-06-20-17:44
 * @discroption
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不存在，换个问题试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何评论"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试！"),
    SYS_ERROR(2004,"服务器忙，请稍后再试！"),
    COMMENT_TYPE_WRONG(2005,"评论类型不正确！"),
    COMMENT_NOT_FOUND(2006,"找不到评论！"),
    COMMENT_CONTENT_NULL(2007,"评论内容不能为空！")
    ;

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }



}
