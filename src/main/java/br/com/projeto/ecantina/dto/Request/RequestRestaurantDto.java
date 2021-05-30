package br.com.projeto.ecantina.dto.request;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.projeto.ecantina.config.validation.notations.EmailEquals;
import br.com.projeto.ecantina.models.Category;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.models.UserType;
import br.com.projeto.ecantina.repository.CategoryRepository;
import br.com.projeto.ecantina.repository.EstablishmentRepository;

public class RequestRestaurantDto {
    
    @Email(message = "{email.format}")
    @NotBlank(message = "{email.blank}")
    @EmailEquals(message = "{email.equals}")
    private String email;

    @NotBlank
    @Size(min = 8, message = "{password.size}")
    private String password;

    @NotBlank(message = "{name.blank}")
    private String name;

    @NotBlank(message = "{cnpj.blank}")
    private String cnpj;

    @Size(max = 255, message = "{description.size}")
    private String description;
    
    @NotNull(message = "{establishmentName.blank}")
    private String establishmentName;

    @NotBlank(message = "{category.blank}")
    private String category;

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant convert(EstablishmentRepository establishmentRepository, CategoryRepository categoryRepository) {

        Optional<Establishment> establishment = establishmentRepository.findByName(getEstablishmentName());

        if(establishment.isPresent()) {

            Optional<Category> findCategory = categoryRepository.findByName(getCategory());
            Restaurant restaurant = null;
            
            if (findCategory.isPresent()) {
                restaurant = new Restaurant(getEmail(), getPassword(), getName(), getCnpj(),findCategory.get(), getDescription());
            } else {
                restaurant = new Restaurant(getEmail(), getPassword(), getName(), getCnpj(),new Category(category), getDescription());
            }
            restaurant.getUserTypes().add(new UserType("ROLE_RESTAURANT"));
            establishment.get().getRestaurants().add(restaurant);
            
            return restaurant;
        } else {
            throw new NullPointerException("Estabelecimento não encontrado!!!");
        }
    }   
}
