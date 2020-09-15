package edu.yctc.common.exception;

/**
 * @author luna@win10
 * @date 2020/3/31 13:24
 */
public class LoginException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected final String    message;

    public LoginException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
