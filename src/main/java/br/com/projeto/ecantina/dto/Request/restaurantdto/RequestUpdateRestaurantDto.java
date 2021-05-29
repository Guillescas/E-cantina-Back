package br.com.projeto.ecantina.dto.request.restaurantdto;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.projeto.ecantina.config.exceptions.EmailNotValidException;
import br.com.projeto.ecantina.models.Category;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.CategoryRepository;
import br.com.projeto.ecantina.repository.UserRepository;

public class RequestUpdateRestaurantDto {
    
    @NotBlank(message = "{name.blank}")
    private String name;

    @Email(message = "{email.format}")
    @NotBlank(message = "{email.blank}")
    private String email;

    @NotBlank(message = "{cnpj.blank}")
    private String cnpj;
    
    @Size(max = 255, message = "{description.size}")
    private String description;

    // @NotNull(message = "{establishmentName.blank}")
    // private String establishmentName;

    @NotBlank(message = "{category.blank}")
    private String category;

    public String getCategory() {
        return category;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }
    
    // TODO ver com o gui sobre estabelecimento mudar
    // public String getEstablishmentName() {
    //     return establishmentName;
    // }

    public String getName() {
        return name;
    }

    public Restaurant update(Optional<Restaurant> restaurantFind, UserRepository userRepository, CategoryRepository categoryRepository) {
        Restaurant restaurant = restaurantFind.get();
        Optional<Category> categories = categoryRepository.findByName(getCategory());

        if (!restaurant.getEmail().equals(getEmail())) {
            Optional<User> user = userRepository.findByEmail(getEmail());
            if (user.isPresent())
                throw new EmailNotValidException("Email já está em uso");
            else
                restaurant.setEmail(getEmail());
        }
        restaurant.setCnpj(getCnpj());
        restaurant.setDescription(getDescription());
        restaurant.setName(getName());
        
        if(categories.isPresent()) {
            restaurant.setCategories(categories.get());
        } else {
            Category newCategory = new Category(getCategory());
            restaurant.setCategories(newCategory);
        }

        return restaurant;
    }
}
