package agiliz.projetoAgiliz.configs.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import agiliz.projetoAgiliz.services.AutenticacaoService;

public class AuthProvider implements AuthenticationProvider{
    private final AutenticacaoService autenticacaoService;
    private final PasswordEncoder passwordEncoder;

    public AuthProvider(AutenticacaoService autenticacaoService, PasswordEncoder passwordEncoder){
        this.autenticacaoService = autenticacaoService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        
        UserDetails userdetails = this.autenticacaoService.loadUserByUsername(username);

        if(this.passwordEncoder.matches(password, userdetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
        }else{
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
