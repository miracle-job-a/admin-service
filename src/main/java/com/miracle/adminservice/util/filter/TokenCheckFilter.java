package com.miracle.adminservice.util.filter;


import org.springframework.core.env.Environment;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/v1/*")
public class TokenCheckFilter implements Filter {

    private Environment environment;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.environment = new StandardServletEnvironment();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String privateKey = environment.getProperty("privateKey");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String id = httpServletRequest.getHeader("Session-Id") + privateKey;
        int miracle = id.hashCode();
        int token = httpServletRequest.getIntHeader("Miracle");

        if (miracle != token) {
            RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/errors/token");
            requestDispatcher.forward(request,response);
            return;
        }
        chain.doFilter(request, response);
        }
}
