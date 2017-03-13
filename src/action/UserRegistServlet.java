package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xdcao on 2017/3/13.
 */
public class UserRegistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String sex=req.getParameter("sex");
        int age=Integer.parseInt(req.getParameter("age"));
        System.out.println(username);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(age);
    }
}
