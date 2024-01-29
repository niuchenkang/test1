package Construction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "RegistServlet", value = "/RegistServlet")
public class RegistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pas = request.getParameter("psw");


        String sql = "INSERT INTO user (name,password,email)values('"+name+"','"+pas+"','"+email+"');";
        MysqlDB db = new MysqlDB();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        out.println("</head>");
        out.println("<body>");
        if(db.exeUpdate(sql)){
            out.println(name+"，注册成功,"+"<a href=\"login.html\">返回登录</a>");
        }
        else {
            out.println(sql);
            out.println(email);
            out.println(name+"，注册失败,"+"<a href=\"login.html\">返回登录</a>");
        }
        out.println("</body>");
        out.println("</html>");
        try {
            db.Release();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
