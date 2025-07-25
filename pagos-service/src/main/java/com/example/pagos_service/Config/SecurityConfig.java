package com.example.pagos_service.Config;

import jakarta.ws.rs.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/pagos/chequear-pago").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(configure -> configure
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter()))
                );

        return http.build();
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthConverter(){
        JwtAuthenticationConverter converter=new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return converter;
    }
}

//sirve para mapear los roles de keycloak a roles de spring security
@SuppressWarnings("unchecked")
class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>>{

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        if(jwt.getClaims()==null){
            return List.of();
        }
        final Map<String, List<String>> realmAccess=(Map<String, List<String>>)jwt.getClaims().get("realm_access");

        return realmAccess.get("roles").stream()
                .map(roleName->"ROLE_"+roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
