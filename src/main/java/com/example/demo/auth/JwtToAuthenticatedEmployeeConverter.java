package com.example.demo.auth;

import com.example.demo.model.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtToAuthenticatedEmployeeConverter implements Converter<Jwt, JwtAuthenticationToken> {

    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {

        Employee employee = new Employee();
        employee.setEmail(jwt.getClaim("sub"));
        List<SimpleGrantedAuthority> authorities = getAuthoritiesFromJwt(jwt);

        AuthenticatedEmployee authenticatedEmployee = new AuthenticatedEmployee();
        authenticatedEmployee.setEmployee(employee);

        return new JwtAuthenticationToken(jwt, authorities, authenticatedEmployee.getUsername());
    }

    private List<SimpleGrantedAuthority> getAuthoritiesFromJwt(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList("scope");
        if (roles == null) {
            return Collections.emptyList();
        }
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
