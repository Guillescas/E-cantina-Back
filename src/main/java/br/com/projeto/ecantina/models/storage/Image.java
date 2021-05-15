package br.com.projeto.ecantina.models.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.projeto.ecantina.models.User;

@Component
public class Image {

    @Value("${profile.image.directory-images}")
    private String directoryImages;

    public void saveImage(MultipartFile image, User user) {
        this.save(this.directoryImages, image, user);
    }

    public void save(String directory, MultipartFile file, User user) {
        Path absolutePath = Paths.get("").toAbsolutePath();
        System.out.println(absolutePath.normalize().toString());

        String root = absolutePath.normalize().toString();

        Path directoryPath = Paths.get(root, directory);
        Path filePath = directoryPath.resolve(user.getId().toString() + fileFormat(file));

    

        try {

            Files.createDirectories(directoryPath);
            Files.copy(file.getInputStream(), filePath);

        } catch (IOException ex) {

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
