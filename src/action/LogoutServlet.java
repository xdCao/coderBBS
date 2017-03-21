package action;

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
import java.util.Date;

/**
 * Created by xdcao on 2017/3/21.
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        System.out.println("User logout: "+username);
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="from Users where username="+username;
        Query query=session.createQuery(hql);
        Users users= (Users) query.getResultList().get(0);
        users.setLasttime(new Date());
        session.update(users);
        transaction.commit();
        session.close();
    }
}
