package action;

import com.sun.xml.internal.ws.transport.http.HttpAdapter;
import model.Post;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
 * Created by xdcao on 2017/3/13.
 */
public class writeBlogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title=req.getParameter("title");
        String author=req.getParameter("author");
        String content=req.getParameter("content");
        Date date=new Date();
        Post post=new Post();
        post.setTitle(title);
        post.setAuthor(author);
        post.setContent(content);
        post.setCreateDate(date);
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        session.save(post);
        transaction.commit();
        session.close();
        System.out.println("成功");
    }
}
