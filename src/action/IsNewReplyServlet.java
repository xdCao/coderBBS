package action;

import model.Reply;
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
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xdcao on 2017/3/20.
 */
public class IsNewReplyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="from Users where username="+username;
        Query query=session.createQuery(hql);
        Users users= (Users) query.getResultList().get(0);
        String hql1="from Reply where createDate >? and comentsByCommentId.usersByUserId.id=?";
        Query query1=session.createQuery(hql1).setParameter(0,users.getLasttime()).setParameter(1,users.getId());
        List<Reply> replies=query1.getResultList();
        if (replies.size()>0){
            PrintWriter writer=resp.getWriter();
            writer.print("1");
            writer.close();
        }
        System.out.println("replies: "+replies.size());
        System.out.println("replies: "+replies.size());
        System.out.println("replies: "+replies.size());
        System.out.println("replies: "+replies.size());
        System.out.println("replies: "+replies.size());
        System.out.println("replies: "+replies.size());
    }
}
