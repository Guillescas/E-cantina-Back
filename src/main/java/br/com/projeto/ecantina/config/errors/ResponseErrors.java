package br.com.projeto.ecantina.config.errors;

public class ResponseErrors {
     
    private Boolean error;
    private Integer status;
    private String message;

    public ResponseErrors(String message, Integer status) {
        this.error = true;
        this.status = status;
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
