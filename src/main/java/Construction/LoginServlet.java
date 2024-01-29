package Construction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");;
        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String psw =request.getParameter("psw");

        String sql ="select * from user where email='"+email+"';";
        MysqlDB db =new MysqlDB();
        ResultSet rs = db.getResult(sql);
        try {
            if (rs.next()) {
                if (psw.equals(rs.getString("password"))){
                    HttpSession session =request.getSession();
                    session.setMaxInactiveInterval(60*60*24);
                    session.setAttribute("username",email);
                    response.sendRedirect("test.html");

                }
                else {
                    out.print("<script>alert('Please check if the password is correct!.');window.history.back();</script>");
                }
            }else {
                out.println(sql);
                //out.print("<script>alert('Please check if the account has been entered correctly!');window.history.back();</script>");
            }
            db.Release();
        }catch (Exception e){
            out.println(e.getMessage());
        }
    }
}
