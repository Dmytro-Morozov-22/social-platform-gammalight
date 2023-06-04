package com.ua.vinn.GammaLight.securities;

import com.ua.vinn.GammaLight.exceptions.JwtAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.server.ResponseStatusException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Даний клас буде пропускати через себе запити
 * jwtTokenFilter при отримані запиту валідує його за допомогою jwtTokenProvider
 * тобто jwtTokenProvider отримує токен із запиту за допомогою resolveToken, далі перевіряє
 * токен на null та на валідність кінцевої дати, потім отримує для цього токену аутентифікацію
 * та вставляє цю аутентифікацію у контекст вже інснуючої аутентифікації.
 */
@Component
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtTokenFilter(@Lazy JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);

                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e){
            SecurityContextHolder.clearContext();
            ((HttpServletResponse)servletResponse).sendError(e.getHttpStatus().value());
//            throw new JwtAuthenticationException("Jwt token is expired or invalid");
            throw new ResponseStatusException(401, "Jwt token is expired or invalid",null);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
