package action;

import model.Coments;
import model.Post;
import model.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.Main;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by xdcao on 2017/3/18.
 */
public class CommentDealerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("id"));

        int id=Integer.parseInt(req.getParameter("id"));
        String content=req.getParameter("content");

        Users users= (Users) req.getSession().getAttribute("currentUser");

        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="from Users where id="+users.getId();
        Query query=session.createQuery(hql);
        Users now= (Users) query.getResultList().get(0);
        String hql1="from Post where id="+id;
        Query query1=session.createQuery(hql1);
        Post currentPost= (Post) query1.getResultList().get(0);

        Coments coments=new Coments();
        coments.setContent(content);
        coments.setCreateDate(new Date());
        coments.setStar(0);
        coments.setPostByPostId(currentPost);
        coments.setUsersByUserId(now);
        session.save(coments);

        transaction.commit();

        session.close();

    }
}
