package br.com.projeto.ecantina.config.components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.projeto.ecantina.models.User;

@Component
public class ImageComponent {

    @Value("${image.directory-images}")
    private String directoryImages;

    public String saveImage(MultipartFile image, User user) {
        String userType = user.getType();
        String urlImage = this.save(this.directoryImages, userType, "",image);
        return image.getOriginalFilename();
    }

    public String save(String directory, String userType, String product, MultipartFile file) {
        Path absolutePath = Paths.get("").toAbsolutePath();
        
        String root = absolutePath.normalize().toString();

        Path directoryPath = Paths.get(root, String.format("%s/%s", directory, userType));
        Path filePath = directoryPath.resolve(file.getOriginalFilename());

    

        try {
            Files.createDirectories(directoryPath);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Problemas na tentativa de salvar arquivo");
        }
    }

    public String fileFormat(MultipartFile file) {
        // TODO return error if not a image
        String fileName = file.getOriginalFilename();
        String fileFormat = fileName.substring(fileName.length() - 4, fileName.length());

        return fileFormat;
    }
}
