package action;

import model.Post;
import model.Users;
import org.hibernate.Session;
import utils.Main;

import javax.persistence.Query;
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
        if (users!=null){
            Session session= Main.getSession();
            String hql="from Users where id="+users.getId();
            Query query=session.createQuery(hql);
            Users now= (Users) query.getResultList().get(0);
            if (now.getPostsById().size()>0){
                for (Post post:now.getPostsById()){
                    if (id==post.getId()){
                        out.print("favored");
                        out.close();
                        return;
                    }
                }
            }
            out.print("nofavor");
        }else {
            out.print("nouser");
        }
        out.close();
    }
}
