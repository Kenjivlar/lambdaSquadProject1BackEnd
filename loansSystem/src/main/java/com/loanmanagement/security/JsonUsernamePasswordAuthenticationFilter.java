package com.loanmanagement.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanmanagement.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;

public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if ("application/json".equals(request.getContentType())) {
            try (InputStream is = request.getInputStream()) {
                ObjectMapper mapper = new ObjectMapper();
                LoginRequest loginRequest = mapper.readValue(is, LoginRequest.class);
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword());
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                throw new AuthenticationServiceException("Error al parsear el JSON de la solicitud de autenticación", e);
            }
        }
        return super.attemptAuthentication(request, response);
    }
}
