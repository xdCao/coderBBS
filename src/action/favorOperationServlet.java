package action;

import javafx.geometry.Pos;
import model.Post;
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
public class favorOperationServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String count=req.getParameter("count");
        int id=Integer.parseInt(req.getParameter("id"));
        PrintWriter writer=resp.getWriter();
        if(count.equals("1")){
            updatePostWithFavor(id,1);
            writer.print("1");
        }else if(count.equals("-1")){
            updatePostWithFavor(id,-1);
            writer.print("-1");
        }
        writer.close();
    }

    private void updatePostWithFavor(int id,int num){
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="from Post where id="+id;
        Query query=session.createQuery(hql);
        List<Post> posts=query.getResultList();
        Post post=posts.get(0);
        if(post.getFavor()==null){
            post.setFavor(0);
        }
        post.setFavor(post.getFavor()+num);
        session.update(post);
        transaction.commit();
        session.close();
    }

}
