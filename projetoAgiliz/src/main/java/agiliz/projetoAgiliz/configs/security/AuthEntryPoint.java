package agiliz.projetoAgiliz.configs.security;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // if (authException instanceof BadCredentialsException || authException instanceof InsufficientAuthenticationException) {
        //     response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido, ou você não possui privilégios suficientes para esse conteúdo");
        // } 
        // else {
        //     response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token inválido ou não enviado, faça login novamente ou contate nossa equipe técnica");
        // }
    }
}
