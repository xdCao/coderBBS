package main.java.action;

import com.sun.xml.internal.ws.transport.http.HttpAdapter;
import main.java.model.Post;
import main.java.model.Users;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Session;
import org.hibernate.Transaction;
import main.java.utils.Main;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by xdcao on 2017/3/13.
 */
public class writeBlogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users users= (Users) req.getSession().getAttribute("currentUser");
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

        String hql="from Users where id="+users.getId();
        Query query=session.createQuery(hql);
        Users now= (Users) query.getResultList().get(0);
        post.setOwnId(now.getId());


        session.save(post);
        transaction.commit();
        resp.setCharacterEncoding("utf-8");
        PrintWriter out=resp.getWriter();
        Map<String,Object> map=new HashedMap();
        map.put("ok",1);
        JSONArray jsonArray=JSONArray.fromObject(map);
        out.write(jsonArray.toString());
        out.flush();
        out.close();
        System.out.println("成功");







    }
}
