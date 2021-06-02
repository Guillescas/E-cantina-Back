package br.com.projeto.ecantina.config.security;

import br.com.projeto.ecantina.config.errors.ResponseErrors;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private TokenService tokenService;

    public CustomAuthenticationEntryPoint(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException)
            throws IOException, ServletException {

        String contentType = "application/json;charset=UTF-8";

        try {
            String token = req.getHeader("Authorization").split(" ")[1];
            timeExpired(token);
            res.setContentType(contentType);
            res.setStatus(401);

            ObjectMapper mapper = new ObjectMapper();
            res.getWriter().write(mapper.writeValueAsString(new ResponseErrors("Acesso Negado: Token Expirado", 401)));
        } catch (ExpiredJwtException exp) {
            res.setContentType(contentType);
            res.setStatus(401);

            ObjectMapper mapper = new ObjectMapper();
            res.getWriter().write(mapper.writeValueAsString(new ResponseErrors("Acesso Negado: Token Expirado", 401)));
        } catch (Exception exp) {
            res.setContentType(contentType);
            res.setStatus(403);

            ObjectMapper mapper = new ObjectMapper();
            res.getWriter().write(mapper.writeValueAsString(new ResponseErrors("Acesso Negado: Aqui não amigão", 403)));
        }

    }

    private boolean timeExpired(String token) {
        Long expired = tokenService.getExpired(token);
        Long issuedAt = tokenService.getIssuedAt(token);

        return issuedAt > expired;
    }
}
