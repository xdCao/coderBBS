package main.java.action;

import main.java.model.Post;
import org.hibernate.Session;
import main.java.utils.Main;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xdcao on 2017/3/10.
 */
public class QueryNumOfPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session session= Main.getSession();
        String hql="select count(*) from Post";
        Query query=session.createQuery(hql);
        List<Object> objects=query.getResultList();
        long count= (Long) objects.get(0);
        System.out.println(count);
        PrintWriter writer=resp.getWriter();
        writer.write(String.valueOf(count));
        writer.flush();
        writer.close();
        session.close();
    }

}
