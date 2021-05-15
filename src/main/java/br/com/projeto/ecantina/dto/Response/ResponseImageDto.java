package br.com.projeto.ecantina.dto.response;

public class ResponseImageDto {
    
    private String directoryImage = "src/main/java/br/com/projeto/ecantina/images";

    private String nameFile;

    public ResponseImageDto(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getDirectoryImage() {
        return directoryImage;
    }
    public String getNameFile() {
        return nameFile;
    }
}
