package com.ua.vinn.GammaLight.securities;

import com.ua.vinn.GammaLight.exceptions.JwtAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    /**
     * Значення для полів secretKey та validityInMilliseconds підтягуються з property файлу
     */
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.header}")
    private String authorizationHeader;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    @Autowired
    public JwtTokenProvider(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     *@PostConstruct означає, що метод виконається після того як Spring створить екземпляр компонента,
     * тобто класу JwtTokenProvider, а потім будуть створені інші компоненти
     * init зашивровує в стрічку String секретний ключ
     */
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }



    public String createToken(String firstName, String role){
        /**
         * Claims - це фрагменти інформації, які підтверджують користувача
         * напр. ID-токен може містити імя користувача та роль, який проходить аутентифікацію
         */
        Claims claims = Jwts.claims().setSubject(firstName);
        claims.put("role", role);

        /**
         * Дата З ЯКОЇ токен буде працювати
         */
        Date now = new Date();

        /**
         * Дата ДО ЯКОЇ токен буде працювати
         */
        Date validity = new Date(now.getTime() + validityInMilliseconds * 1000);

        /**
         * builder() створює токен за допомогою claims, now, validity,
         * алгоритму, який викор. у signature та секретного ключа secretKey,
         * який підтягується з property файлу
         */
        return Jwts.builder()
                .addClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    /**
     * validateToken приймає token і перевіряє чи він є дійсним
     * тобто перевіряє його термін придатності чи він не перейшов
     * за ту дату яка вказана у validity
     */
    public boolean validateToken(String token){
        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e){
            throw new JwtAuthenticationException("Jwt token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * getAuthentication надає права користувача тобто аутентифікацію
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserNameFromToken(token));
        System.out.println(userDetails);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     *getUserNameFromToken витягує імя користувача з токена
     */
    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     *resolveToken використовується для контролера
     */
    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(authorizationHeader);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}
