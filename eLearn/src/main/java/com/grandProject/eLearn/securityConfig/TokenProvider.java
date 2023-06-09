package com.grandProject.eLearn.securityConfig;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.minutes}")
    private Long jwtExpirationMinutes;
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "MA";
    public static final String TOKEN_AUDIENCE = "MAUI";

    public String generate(Authentication authentication) {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        byte[] signingKey = jwtSecret.getBytes();

        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMinutes).toInstant()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setId(UUID.randomUUID().toString())
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .claim("rol", roles)
                .compact();
    }

    public Optional<Jws<Claims>> validateTokenAndGetJws(String token) {
        try {
            byte[] signingKey = jwtSecret.getBytes();

            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);

            return Optional.of(jws);
        } catch (ExpiredJwtException exception) {
            log.error("Token {} has expired", token);
        } catch (UnsupportedJwtException exception) {
            log.error("Token {} is not supported", token);
        } catch (MalformedJwtException exception) {
            log.error("Token {} is malformed", token);
        } catch (SignatureException exception) {
            log.error("Token {} signature does not match", token);
        } catch (IllegalArgumentException exception) {
            log.error("Token {} is not valid", token);
        }
        return Optional.empty();
    }


}
