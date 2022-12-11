package org.omsoft.filter;

import com.sun.net.httpserver.HttpServer;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CorsBasicAuthenticationFilter extends BasicHttpAuthenticationFilter {
    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String httpMethod = httpServletRequest.getMethod();
        if(!"OPTIONS".equalsIgnoreCase(httpMethod)) {
            super.doFilterInternal(request, response, chain);
        }
    }
}
