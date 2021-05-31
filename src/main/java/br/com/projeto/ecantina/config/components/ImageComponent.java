package br.com.projeto.ecantina.config.components;

import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.projeto.ecantina.config.exceptions.ImageTypeMismatchException;
import br.com.projeto.ecantina.models.Product;
import br.com.projeto.ecantina.models.User;

@Component
public class ImageComponent {

    @Value("${image.directory-images}")
    private String directoryImages;

    public void deleteImageUser(User user) {
        if (user.getUrlImage() != null) {
            delete(user.getUrlImage(), this.directoryImages);
        }
    }

    public void deleteImageProduct(Product product) {
        if (product.getUrlImage() != null) {
            delete(product.getUrlImage(), this.directoryImages);
        }
    }

    public String saveImage(MultipartFile image, User user, Product product) {
        System.out.println(image.getContentType());
        if (image.getContentType().contains("image")) {
            Path directory = createDirectoryPath(this.directoryImages + "/images", user, product);
            verifyIfAlreadyHaveImage(directory.toString(), user, product);
            return this.save(directory, image);
        } else {
            throw new ImageTypeMismatchException("Tipo de arquivo não é uma imagem");
        }
    }

    private void verifyIfAlreadyHaveImage(String directory, User user, Product product) {
        String image = directory.split("images")[0];
        try {
            if (user.getUrlImage() != null) {
                if (product != null) {
                    Path file = Paths.get(image + product.getUrlImage());
                    Files.deleteIfExists(file);
                } else {
                    Path file = Paths.get(image + user.getUrlImage());
                    Files.deleteIfExists(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileSystemNotFoundException("Erro em verificar se imagem existe");
        }
    }

    private String save(Path directory, MultipartFile file) {

        Path filePath = directory.resolve(file.getOriginalFilename());

        try {
            Files.createDirectories(directory);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            String[] paths = filePath.toString().split("/E-cantina-Back/");
            return paths[1].substring(26);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new FileSystemNotFoundException("Problemas na tentativa de salvar arquivo");
        }
    }

    private Path createDirectoryPath(String directory, User user, Product product) {
        Path directoryPath;
        Path absolutePath = Paths.get("").toAbsolutePath();
        String root = absolutePath.normalize().toString();

        if (product != null) {
            directoryPath = Paths.get(root, String.join("/", directory, "products"));
        } else {
            directoryPath = Paths.get(root, String.join("/", directory, user.getType()));
        }

        return directoryPath;
    }

    private void delete(String urlImage, String directory) {
        try {
            Path file = Paths.get("").toAbsolutePath().resolve(directory).resolve(urlImage);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileSystemNotFoundException("Erro ao deletar imagens");
        }
    }
}
