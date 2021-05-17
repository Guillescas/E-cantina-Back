package br.com.projeto.ecantina.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.projeto.ecantina.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // @Autowired
    // RestaurantRepository restaurantRepository;

    // @Autowired
    // ClientRepository clientRepository;

    // @Autowired
    // EstablishmentRepository establishmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DetailService detailService;

    @Autowired
    private TokenService tokenService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        AuthenticationTokenFilter authTokenFilter = new AuthenticationTokenFilter(tokenService, userRepository);

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/client").permitAll()
                .antMatchers(HttpMethod.POST, "/restaurant").permitAll()
                .antMatchers(HttpMethod.POST, "/establishment").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/product").hasRole("RESTAURANT")
                .antMatchers(HttpMethod.POST, "/upload").permitAll()
                .antMatchers(HttpMethod.GET, "/upload").hasRole("CLIENT")
                .antMatchers(HttpMethod.GET, "/upload").hasRole("RESTAURANT")
                .antMatchers(HttpMethod.GET, "/client").hasRole("CLIENT")
                .antMatchers(HttpMethod.DELETE, "/restaurant/*").hasRole("RESTAURANT")
                .antMatchers(HttpMethod.PUT, "/restaurant/*").hasRole("RESTAURANT")
                .anyRequest().permitAll()
            .and()
                .cors()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailService).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

}
