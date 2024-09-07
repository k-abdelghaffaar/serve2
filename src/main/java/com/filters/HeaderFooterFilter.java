package com.filters;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class HeaderFooterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Set custom headers (before)
        PrintWriter out = httpResponse.getWriter();
        out.println("<header><h1>Welcome to My Website</h1></header>");

        // Pass the request along the filter chain
        chain.doFilter(request, response);

        // Set custom footers (after)
        out.println("<footer><p>Footer Content Here</p></footer>");
    }

}
