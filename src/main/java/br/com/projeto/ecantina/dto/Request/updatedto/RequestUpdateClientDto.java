package br.com.projeto.ecantina.dto.request.updatedto;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.projeto.ecantina.config.exceptions.EmailNotValidException;
import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.UserRepository;

public class RequestUpdateClientDto {

    @NotBlank(message = "{name.blank}")
    private String name;

    @Email(message = "{email.format}")
    @NotBlank(message = "{email.blank}")
    private String email;

    @Size(max = 14)
    private String cpf;

    private String urlImage;

    public String getCpf() {
        return cpf;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Client update(Optional<Client> client, UserRepository userRepository) {

        if (!client.get().getEmail().equals(getEmail())) {
            Optional<User> user = userRepository.findByEmail(getEmail());
            if (user.isPresent())
                throw new EmailNotValidException("Email já está em uso");
            else
                client.get().setEmail(getEmail());
        }
        client.get().setCpf(getCpf());
        client.get().setUrlImage(getUrlImage());
        client.get().setName(getName());
        return client.get();

    }
}
