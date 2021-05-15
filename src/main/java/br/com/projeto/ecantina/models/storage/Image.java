package br.com.projeto.ecantina.models.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Image {
    
    @Value("${profile.image.root}")
    private String root;

    @Value("${profile.image.directory-images}")
    private String directoryImages;

    public void saveImage(MultipartFile image) {
        this.save(this.directoryImages, image);
    }

    public void save(String directory, MultipartFile file) {
        Path directoryPath = Paths.get(this.root, directory);
        Path filePath = directoryPath.resolve(file.getOriginalFilename());

        try {

            Files.createDirectories(directoryPath);
            file.transferTo(filePath.toFile());

        } catch (IOException ex) {

            throw new RuntimeException("Problemas na tentativa de salvar arquivo");
        }
    }
}
