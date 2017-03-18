package action;

import javafx.geometry.Pos;
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
            updatePostWithFavor(req,id,1);
            writer.print("1");
        }else if(count.equals("-1")){
            updatePostWithFavor(req,id,-1);
            writer.print("-1");
        }
        writer.close();
    }

    private void updatePostWithFavor(HttpServletRequest req,int id,int num){
        Users currentUser= (Users) req.getSession().getAttribute("currentUser");
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String postHql="from Post where id="+id;
        Query query=session.createQuery(postHql);
        List<Post> posts=query.getResultList();
        Post post=posts.get(0);
        if(post.getFavor()==null){
            post.setFavor(0);
        }else if(post.getFavor()==0&&(num==-1)){
           post.setFavor(1);
        }
        post.setFavor(post.getFavor()+num);
//        session.update(post);

        String userHql="from Users where id="+currentUser.getId();
        Query query1=session.createQuery(userHql);
        List<Users> usersList=query1.getResultList();
        Users now=usersList.get(0);


        if(num==-1){
            now.getPostsById().remove(post);
            System.out.println("remove remove remove remove");
        }else if(num==1){
            now.getPostsById().add(post);
            System.out.println("add add add add add");
        }
        session.update(now);
        transaction.commit();
        session.close();



    }

}
