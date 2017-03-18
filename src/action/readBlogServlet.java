package action;

import model.Post;
import model.Users;
import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
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
import java.util.Map;


/**
 * Created by xdcao on 2017/3/12.
 */
public class readBlogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        Session session= Main.getSession();
        String hql="from Post where id="+id;
        javax.persistence.Query query=session.createQuery(hql);
        List<Post> posts=query.getResultList();
        if (posts.size()>0){
            Post post=posts.get(0);
            if(post.getScan()==null){
                post.setScan(1);
            }else {
                int scan=post.getScan()+1;
                post.setScan(scan);
            }

            Transaction transaction=session.beginTransaction();
//        String update="update Post set Post.scan="+scan+" where Post.id="+postId;
//        Query updateQuery=session.createQuery(update);
//        updateQuery.executeUpdate();
            session.update(post);
            transaction.commit();

            Map<String,Object> map=new HashedMap();
            map.put("title",post.getTitle());
            map.put("author",post.getAuthor());
            map.put("createDate",post.getCreateDate());
            map.put("scan",post.getScan());
            map.put("favor",post.getFavor());
            map.put("content",post.getContent());

            JSONArray jsonArray=JSONArray.fromObject(map);
            out.print(jsonArray.toString());
            out.close();
            System.out.println(jsonArray.toString());
        }
        session.close();


    }



}
