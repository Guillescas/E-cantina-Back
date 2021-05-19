package br.com.projeto.ecantina.config.errors;

public class ResponseErrors {
     
    private Boolean error;
    private String code;
    private String message;

    public ResponseErrors(String message, String code) {
        this.error = true;
        this.code = code;
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
