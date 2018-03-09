package Controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(servletNames = {"home"},urlPatterns = {"/home","/Home.jsp","addpub.jsp","publications.jsp","publications"})
public class WhiteListFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String userLogin = (String) ((HttpServletRequest) request)
                .getSession().getAttribute("userLogin");
        if (userLogin != null) {
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/");
        }
    }

    @Override
    public void destroy() {

    }
}
