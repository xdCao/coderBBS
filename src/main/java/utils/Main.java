package main.java.utils;

import main.java.model.Post;
import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by xdcao on 2017/3/9.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;


    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void showOnePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        Session session= Main.getSession();
        String hql="from Post where id="+id;
        javax.persistence.Query query=session.createQuery(hql);
        List<Post> posts=query.getResultList();
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        if (posts.size()>0){
            Post post=posts.get(0);
            System.out.print(post.getAuthor());
            Map<String,Object> postMap=new HashedMap();
            postMap.put("author",post.getAuthor());
            postMap.put("createDate",post.getCreateDate());
            postMap.put("favor",post.getFavor());
            postMap.put("scan",post.getScan());
            postMap.put("title",post.getTitle());
            postMap.put("id",post.getId());
            JSONArray jsonArray=JSONArray.fromObject(postMap);
            System.out.println(jsonArray.toString());
            out.print(jsonArray.toString());
        }
        session.close();
        out.close();
    }

//    public static void main(String[] args){
//        Session session=Main.getSession();
//        Transaction transaction=session.beginTransaction();
//        String hql="from Coments where id=1";
//        javax.persistence.Query query=session.createQuery(hql);
//        Coments coments= (Coments) query.getResultList().get(0);
//        Reply reply= (Reply) coments.getRepliesById().toArray()[0];
//        System.out.println(reply.getContent());
//    }

}