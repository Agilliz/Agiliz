package agiliz.projetoAgiliz.configs.security;

import java.io.IOException;
import java.util.Objects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import agiliz.projetoAgiliz.configs.security.JWT.GerenciadorTokenJWT;
import agiliz.projetoAgiliz.services.AutenticacaoService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AutenticacaoFilter extends OncePerRequestFilter{

    private final AutenticacaoService autenticacaoService;
    private final GerenciadorTokenJWT jwtTokenManager;


    public AutenticacaoFilter(AutenticacaoService autenticacaoService, GerenciadorTokenJWT jwtTokenManager) {
        this.autenticacaoService = autenticacaoService;
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String userName = null;
        String jwtToken = null;

        String requestTokenHeader = request.getHeader("Authorization");

        if(Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);

            try {
                userName = jwtTokenManager.getUsernameFromToken(jwtToken);
            } catch (ExpiredJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            addUsernameInContext(request, userName, jwtToken);
        }
        filterChain.doFilter(request, response);
    }

    private void addUsernameInContext(HttpServletRequest request, String userName, String jwtToken){
        UserDetails userDetails = autenticacaoService.loadUserByUsername(userName);

        if(jwtTokenManager.validateToken(jwtToken, userDetails)){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new 
            UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
    
}
