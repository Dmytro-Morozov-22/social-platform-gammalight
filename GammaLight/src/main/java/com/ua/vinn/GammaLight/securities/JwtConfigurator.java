package com.ua.vinn.GammaLight.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Створено JwtConfigurator, який додає jwtTokenFilter на початок ланцюга Security.
 * Далі jwtTokenFilter при отримані запиту валідує його за допомогою jwtTokenProvider.
 * Потім jwtTokenProvider отримує токен із запиту за допомогою resolveToken, далі перевіряє
 * токен на null та на валідність кінцевої дати, потім отримує для цього токену аутентифікацію
 * та вставляє цю аутентифікацію у контекст вже інснуючої аутентифікації.
 *
 */
@Component
public class JwtConfigurator extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenFilter jwtTokenFilter;

    @Autowired
    public JwtConfigurator(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
