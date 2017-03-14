package utils;

import javafx.geometry.Pos;
import model.Post;
import net.sf.json.JSONArray;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xdcao on 2017/3/9.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;


    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

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
            JSONArray jsonArray=JSONArray.fromObject(post);
            System.out.println(jsonArray.toString());
            out.print(jsonArray.toString());
        }
        out.close();
    }

}