package br.com.projeto.ecantina.config.validation;

public class ResponseRegisterError {
    
    private String field;
    private String value;
    private String status;

    public ResponseRegisterError(String field, String value, String status) {
        this.field = field;
        this.value = value;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
