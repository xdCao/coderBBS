package main.java.action;

import javafx.geometry.Pos;
import main.java.model.Post;
import main.java.model.Users;
import net.sf.json.JSONArray;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xdcao on 2017/3/17.
 */
public class readMyPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        Users users= (Users) req.getSession().getAttribute("currentUser");
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String myPostHql="from Post where ownId="+users.getId();
        Query query=session.createQuery(myPostHql);
        if (query.getResultList().size()>id){
            Post post= (Post) query.getResultList().toArray()[id];
            Map<String,Object> map=new HashedMap();
            map.put("author",post.getAuthor());
            map.put("createDate",post.getCreateDate());
            map.put("favor",post.getFavor());
            map.put("scan",post.getScan());
            map.put("title",post.getTitle());
            map.put("id",post.getId());
            transaction.commit();
            session.close();
            JSONArray jsonArray=JSONArray.fromObject(map);
            resp.setCharacterEncoding("utf-8");
            PrintWriter out=resp.getWriter();
            out.print(jsonArray.toString());
            out.flush();
            out.close();
        }else {
            return;
        }

    }
}
