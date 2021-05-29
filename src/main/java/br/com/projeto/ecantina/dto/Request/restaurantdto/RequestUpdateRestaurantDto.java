package br.com.projeto.ecantina.dto.request.restaurantdto;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import br.com.projeto.ecantina.config.exceptions.EmailNotValidException;
import br.com.projeto.ecantina.models.Category;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.CategoryRepository;
import br.com.projeto.ecantina.repository.UserRepository;

public class RequestUpdateRestaurantDto {

    private String name;

    @Email(message = "{email.format}")
    private String email;

    private String cnpj;

    @Size(max = 255, message = "{description.size}")
    private String description;

    // @NotNull(message = "{establishmentName.blank}")
    // private String establishmentName;

    private String category;

    private BigDecimal rating;

    private Boolean paid;

    private Boolean open;

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

    public Boolean getOpen() {
        return open;
    }

    public Boolean getPaid() {
        return paid;
    }

    public BigDecimal getRating() {
        return rating;
    }

    // TODO ver com o gui sobre estabelecimento mudar
    // public String getEstablishmentName() {
    // return establishmentName;
    // }

    public String getName() {
        return this.name;
    }

    public void changeAttributes(Restaurant restaurant, CategoryRepository categoryRepository) {

        if (this.cnpj != null && !getCnpj().isBlank())
            restaurant.setCnpj(getCnpj());
        if (this.description != null && !getDescription().isBlank())
            restaurant.setDescription(getDescription());
        if (this.name != null && !getName().isBlank())
            restaurant.setName(getName());
        if (this.rating != null)
            restaurant.setRating(getRating());
        if (this.paid != null)
            restaurant.setPaid(getPaid());
        if (this.open != null)
            restaurant.setOpen(getOpen());
        if (this.category != null && !getCategory().isBlank()) {
            Optional<Category> categories = categoryRepository.findByName(getCategory());
            if (categories.isPresent()) {
                restaurant.setCategories(categories.get());
            } else {
                Category newCategory = new Category(getCategory());
                restaurant.setCategories(newCategory);
            }
        }

    }

    public Restaurant update(Optional<Restaurant> restaurantFind, UserRepository userRepository,
            CategoryRepository categoryRepository) {
        Restaurant restaurant = restaurantFind.get();

        if (!restaurant.getEmail().equals(getEmail())) {
            Optional<User> user = userRepository.findByEmail(getEmail());
            if (user.isPresent())
                throw new EmailNotValidException("Email já está em uso");
            else
                restaurant.setEmail(getEmail());
        }

        changeAttributes(restaurant, categoryRepository);

        return restaurant;
    }
}
