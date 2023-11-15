package net.pascalpex.model;

/**
 * The Response class is a data class used to receive and evaluate the response from the server after sending a Result
 */
public class Response {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
