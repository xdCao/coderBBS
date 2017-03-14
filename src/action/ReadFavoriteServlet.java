package action;

import model.Post;
import model.Users;
import net.sf.json.JSONArray;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.Main;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xdcao on 2017/3/14.
 */
public class ReadFavoriteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        System.out.println("get id: "+id);
        resp.setCharacterEncoding("utf-8");
        PrintWriter out=resp.getWriter();
        Users users= (Users) req.getSession().getAttribute("currentUser");
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="from Users where id="+users.getId();
        Query query=session.createQuery(hql);
        List<Users> usersList=query.getResultList();
        Users myUser=usersList.get(0);
        Post post= (Post) myUser.getPostsById().toArray()[id];

        JSONArray jsonArray=JSONArray.fromObject(post);
        out.print(jsonArray.toString());
        System.out.println(jsonArray.toString());

        transaction.commit();
        session.close();
        out.close();
    }
}
