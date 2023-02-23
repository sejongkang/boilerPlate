package com.sj.plate.auth.jwt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //유효한 자격증명을 제공하지 않고 접근하려 할 때 401
        String exception = (String)request.getAttribute("exception");

        if ("Unsupported".equals(exception)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unsupported");
        } else if ("Expired".equals(exception)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Expired");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
