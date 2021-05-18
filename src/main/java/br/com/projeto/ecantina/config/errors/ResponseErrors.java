package br.com.projeto.ecantina.config.errors;

public class ResponseErrors {
     
    private String error;
    private String value;
    private String stackTrace;

    public ResponseErrors(String error, String value) {
        this.error = error;
        this.value = value;
    }

    public ResponseErrors(String error, String value, String stackTrace) {
        this.error = error;
        this.value = value;
        this.stackTrace = stackTrace;
    }

    public String getError() {
        return error;
    }

    public String getValue() {
        return value;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}
