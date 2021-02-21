package com.oguzhan.petclinic.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(value = "/TestServlet")
public class TestFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.getWriter().write("before ...  ");
        chain.doFilter(request, response);
        response.getWriter().write("after ... ");
    }
}
