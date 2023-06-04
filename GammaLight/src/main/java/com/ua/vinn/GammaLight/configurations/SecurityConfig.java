package com.ua.vinn.GammaLight.configurations;

import com.ua.vinn.GammaLight.models.securityElements.Permission;
import com.ua.vinn.GammaLight.securities.JwtConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //для використання авторизації на рівні методів
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurator jwtConfigurator;

    @Autowired
    public SecurityConfig(JwtConfigurator jwtConfigurator) {
        this.jwtConfigurator = jwtConfigurator;
    }

/**
    SessionCreationPolicy.ALWAYS – Session will always be created (if it does not exist).
    SessionCreationPolicy.NEVER – Spring Security will never create a HttpSession, but will use the HttpSession if it already exists (available through application server)
    SessionCreationPolicy.IF_REQUIRED – Spring Security will only create a HttpSession if required (default configuration. If you don’t specify, Spring security will use this option)
    SessionCreationPolicy.STATELESS – Spring Security will never create a HttpSession and it will never use it to get the SecurityContext.
*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //вказує про те що сесії не використовуються SessionCreationPolicy.STATELESS – Spring Security will never create a HttpSession and it will never use it to get the SecurityContext.
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").permitAll()

                .antMatchers("/Users/**").permitAll()
                .antMatchers("/file/**").permitAll()

//                .antMatchers(HttpMethod.POST, "/post").hasAnyRole(Role.USER.name())
//                .antMatchers(HttpMethod.POST, "/post").hasRole(Role.USER.name())
//                .antMatchers(HttpMethod.POST, "/api/**").hasRole(Role.ADMIN.name())
//                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.ADMIN.name())
//                .antMatchers(HttpMethod.GET, "/api/**").hasAuthority(Permission.DEVELOPERS_READ.getPermission())
//                .antMatchers(HttpMethod.POST, "/post").hasAnyAuthority(Permission.READ_CONTENT.getPermission(), Permission.WRITE_CONTENT.getPermission())
                .antMatchers( HttpMethod.POST,"/avatar/**").hasAuthority(Permission.READ_CONTENT.getPermission())
                .antMatchers( HttpMethod.POST,"/post/**").hasAuthority(Permission.READ_CONTENT.getPermission())
                .antMatchers(HttpMethod.POST,"/comment/**").hasAuthority(Permission.READ_CONTENT.getPermission())
//                .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())

                .anyRequest()
                .authenticated()
//                .httpBasic();
                .and()
                .apply(jwtConfigurator); //користувачі будуть аутентифікуватися на основі конфігурацій, які є у класі jwtConfigurator
        }

//                .and()
//                .formLogin()
//                .loginPage("/auth/login").permitAll()
//                .defaultSuccessUrl("/auth/success")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/login");

//        .and()
//        .logout(cust -> {
//            cust.addLogoutHandler(new HeaderWriterLogoutHandler(
//                    new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL)));
//            cust.logoutSuccessHandler(logoutSuccessHandler);
//            cust.deleteCookies(AuthCookieFilter.COOKIE_NAME);
//        });

//     .logout(logout -> logout
//            .logoutUrl("/my/logout")
//            .logoutSuccessUrl("/my/index")
//            .logoutSuccessHandler(logoutSuccessHandler)
//            .invalidateHttpSession(true)
//            .addLogoutHandler(logoutHandler)
//            .deleteCookies(cookieNamesToClear)
//        )

    /**
     *Необхідно перевизначити AuthenticationManager, щоб він викор. стандартний authenticationManagerBean()
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}