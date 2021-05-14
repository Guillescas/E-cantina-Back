package br.com.projeto.ecantina.config.validation;

public class ResponseRegisterError {
    
    private String field;
    private String value;

    public ResponseRegisterError(String field, String value) {
        this.field = field;
        this.value = value;
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
