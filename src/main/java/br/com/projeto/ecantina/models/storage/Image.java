package br.com.projeto.ecantina.models.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.projeto.ecantina.dto.request.RequestUploadDto;
import br.com.projeto.ecantina.repository.ImageStorageRepository;

@Component
public class Image {

    @Value("${image.directory-images}")
    private String directoryImages;

    public void saveImage(MultipartFile image, Long id, ImageStorageRepository imageStorageRepository) {
        String fileName = this.save(this.directoryImages, image, id);
        this.saveDataBase(imageStorageRepository, fileName);
    }

    public String save(String directory, MultipartFile file, Long id) {
        // TODO make change in the file name.
        Path absolutePath = Paths.get("").toAbsolutePath();
        System.out.println(absolutePath.normalize().toString());

        String root = absolutePath.normalize().toString();

        Path directoryPath = Paths.get(root, directory);
        Path filePath = directoryPath.resolve(file.getOriginalFilename());

    

        try {

            Files.createDirectories(directoryPath);
            Files.copy(file.getInputStream(), filePath);
            return file.getOriginalFilename();
        } catch (IOException ex) {

            throw new RuntimeException("Problemas na tentativa de salvar arquivo");
        }
    }

    private void saveDataBase(ImageStorageRepository imageStorageRepository, String fileName) {
        // RequestUploadDto requestUploadDto = re
    }

    public String fileFormat(MultipartFile file) {
        // TODO return error if not a image
        String fileName = file.getOriginalFilename();
        String fileFormat = fileName.substring(fileName.length() - 4, fileName.length());

        return fileFormat;
    }
}
