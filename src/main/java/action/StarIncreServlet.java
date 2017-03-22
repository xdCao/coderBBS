package main.java.action;

import main.java.model.Coments;
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

/**
 * Created by xdcao on 2017/3/19.
 */
public class StarIncreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="from Coments where id="+id;
        Query query=session.createQuery(hql);
        Coments coments= (Coments) query.getResultList().get(0);
        coments.setStar(coments.getStar()+1);
        session.update(coments);
        transaction.commit();
        session.close();
        resp.setCharacterEncoding("utf-8");
        PrintWriter out=resp.getWriter();
        out.print("1");
        out.close();
    }
}
