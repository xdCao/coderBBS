package action;

import model.Post;
import model.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xdcao on 2017/3/14.
 */
public class isFavorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        PrintWriter out=resp.getWriter();
        Users users= (Users) req.getSession().getAttribute("currentUser");
        if (users.getPostsById().size()>0){
            for (Post post:users.getPostsById()){
                if (id==post.getId()){
                    out.print("favored");
                    out.close();
                    return;
                }
            }
        }
        out.print("nofavor");
        out.close();
    }
}
