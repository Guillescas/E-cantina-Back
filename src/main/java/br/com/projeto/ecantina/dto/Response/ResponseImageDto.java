package br.com.projeto.ecantina.dto.response;

public class ResponseImageDto {

    private String nameFile;

    public ResponseImageDto(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getNameFile() {
        return nameFile;
    }
}
