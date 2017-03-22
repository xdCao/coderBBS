package main.java.action;

import main.java.model.Coments;
import main.java.model.Reply;
import org.hibernate.Session;
import org.hibernate.Transaction;
import main.java.utils.Main;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by xdcao on 2017/3/20.
 */
public class SaveReplyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commentId=req.getParameter("commentId");
        String username=req.getParameter("username");
        String content=req.getParameter("content");
        System.out.println(commentId);
        System.out.println(username);
        System.out.println(content);
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="from Coments where id="+commentId;
        Query query=session.createQuery(hql);
        Coments coments= (Coments) query.getResultList().get(0);
        Reply reply=new Reply();
        reply.setUsername(username);
        reply.setContent(content);
        reply.setCreateDate(new Date());
        reply.setComentsByCommentId(coments);
        session.save(reply);
        coments.getRepliesById().add(reply);
        session.update(session.merge(coments));
        transaction.commit();
        session.close();
    }
}
