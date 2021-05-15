package br.com.projeto.ecantina.dto.request;

import org.springframework.web.multipart.MultipartFile;

import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.EstablishmentRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

public class RequestUploadDto {
    
    private Long idUser;
    private String nameUser;
    private String typeUser;

    private MultipartFile image;

    public Long getUserId() {
        return idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public MultipartFile getImage() {
        return image;
    }

    public User convert (ClientRepository clientRepository ,RestaurantRepository restaurantRepository, EstablishmentRepository establishmentRepository) {
        User user = null;
        switch(getTypeUser()) {
            case "client":
                user = clientRepository.findUserById(getUserId());
                break;
            case "restaurant":
                user = restaurantRepository.findUserById(getUserId());
                break;
            case "establishment":
                user = establishmentRepository.findUserById(getUserId());
        }
        return user;
    }
}
