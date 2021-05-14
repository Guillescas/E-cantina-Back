package br.com.projeto.ecantina.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.projeto.ecantina.models.User;
import br.com.projeto.ecantina.repository.ClientRepository;
import br.com.projeto.ecantina.repository.EstablishmentRepository;
import br.com.projeto.ecantina.repository.RestaurantRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private ClientRepository clientRepository;

    private RestaurantRepository restaurantRepository;

    private EstablishmentRepository establishmentRepository;

    public AuthenticationTokenFilter(TokenService tokenService, 
            ClientRepository clientRepository,
            RestaurantRepository restaurantRepository,
            EstablishmentRepository establishmentRepository) {
        this.tokenService = tokenService;
        this.clientRepository = clientRepository;
        this.restaurantRepository = restaurantRepository;
        this.establishmentRepository = establishmentRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        User user = null;
        String token = getToken(request);
        Boolean valid = tokenService.isTokenValid(token);

        if (valid) {
            Long idUser = tokenService.getIdUser(token);
            String typeUser = tokenService.getTypeUser(token);
            switch(typeUser) {
                case "client":
                    user = clientRepository.findUserById(idUser);
                    break;
                case "restaurant":
                    user = restaurantRepository.findUserById(idUser);
                    break;
                case "establishment":
                    user = establishmentRepository.findUserById(idUser);
                    break;
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
