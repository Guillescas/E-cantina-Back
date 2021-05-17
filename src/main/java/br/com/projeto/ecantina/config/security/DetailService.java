package br.com.projeto.ecantina.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;
import br.com.projeto.ecantina.repository.UserRepository;

@Service
public class DetailService implements UserDetailsService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException("Dados inválidos");
    }

}
