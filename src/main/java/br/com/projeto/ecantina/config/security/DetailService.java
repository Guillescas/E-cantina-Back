package br.com.projeto.ecantina.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.ecantina.models.Client;
import br.com.projeto.ecantina.models.Establishment;
import br.com.projeto.ecantina.models.Restaurant;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.EstablishmentRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

@Service
public class DetailService implements UserDetailsService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = clientRepository.findByEmail(username);
        Optional<Establishment> establishment = establishmentRepository.findByEmail(username);
        Optional<Restaurant> restaurant = restaurantRepository.findByEmail(username);

        if (client.isPresent()) {
            return client.get();
        } else if (establishment.isPresent()) {
            return establishment.get();
        } else if (restaurant.isPresent()) {
            return restaurant.get();
        }

        throw new UsernameNotFoundException("Dados inválidos");
    }

}
