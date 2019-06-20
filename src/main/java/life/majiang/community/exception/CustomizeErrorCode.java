package life.majiang.community.exception;

/**
 * @author lijing
 * @date 2019-06-20-17:44
 * @discroption
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不存在，换个问题试试？");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
