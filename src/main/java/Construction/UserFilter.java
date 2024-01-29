package Construction;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=((HttpServletRequest)request).getSession(true);
        String url =((HttpServletRequest) request).getRequestURI();
        Object obj = session.getAttribute("username");
        if(url.endsWith("LoginServlet")||url.endsWith("login.html")||url.endsWith("RegistServlet")){
            chain.doFilter(request, response);
            return;
        }
        if(null==obj||((String)obj).length()==0){
            out.print("<script>alert('Please go to Login');window.history.back();</script>");
        }else {
            chain.doFilter(request, response);
        }
    }
}
