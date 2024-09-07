package com.filters;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class LoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String ipAddress = request.getRemoteAddr();
        System.out.println("IP Address: " + ipAddress);

        Map<String, String[]> parameters = request.getParameterMap();
        System.out.println("User Input: ");
        parameters.forEach((key, value) -> System.out.println(key + " = " + String.join(", ", value)));

        chain.doFilter(request, response);
    }

}
