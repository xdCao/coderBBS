package main.java.action;

import main.java.model.Users;
import net.sf.json.JSON;
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
import java.util.Map;

/**
 * Created by xdcao on 2017/3/20.
 */
public class HisInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HisInfoServlet");
        System.out.println("HisInfoServlet");  System.out.println("HisInfoServlet");

        String userName=req.getParameter("hisName");
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="from Users where username="+userName;
        Query query=session.createQuery(hql);
        Users users= (Users) query.getResultList().get(0);
        Map<String,Object> map=new HashedMap();
        map.put("name",users.getUsername());
        map.put("sex",users.getSex());
        map.put("age",users.getAge());
        JSONArray jsonArray= JSONArray.fromObject(map);
        resp.setCharacterEncoding("utf-8");
        PrintWriter out=resp.getWriter();
        out.print(jsonArray.toString());
        transaction.commit();
        session.close();
    }
}
