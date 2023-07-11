package com.example.storageservice.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtAuthRoleConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

    public JwtAuthRoleConverter() {
        this.jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        String role =
                (String) Optional.of(jwt)
                        .map(jwt1 -> jwt1.getClaim("realm_access"))
                        .map(Map.class::cast)
                        .map(map -> map.get("roles"))
                        .map(List.class::cast)
                        .stream()
                        .flatMap(List::stream)
                        .findFirst()
                        .orElse("");

        Collection<GrantedAuthority> grantedAuthorities = jwtGrantedAuthoritiesConverter.convert(jwt);
        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return new JwtAuthenticationToken(jwt, grantedAuthorities);
    }

}
