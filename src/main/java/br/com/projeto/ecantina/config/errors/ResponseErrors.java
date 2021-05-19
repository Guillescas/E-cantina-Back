package br.com.projeto.ecantina.config.errors;

public class ResponseErrors {
     
    private Boolean error;
    private String status;
    private String message;

    public ResponseErrors(String message, String status) {
        this.error = true;
        this.status = status;
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
