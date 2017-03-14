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
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xdcao on 2017/3/13.
 */
public class UserRegistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String nick=req.getParameter("nick");
        String sex=req.getParameter("sex");
        int age=Integer.parseInt(req.getParameter("age"));
        System.out.println(username);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(age);
        Users user=new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setNick(nick);
        user.setAge(age);
        user.setSex(sex);
        PrintWriter writer=resp.getWriter();
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="select count(*) from Users where username="+username;
        Query query=session.createQuery(hql);
        List<Long> count=query.getResultList();
        System.out.print(count);
        if(count.get(0)==0){
            session.save(user);
            transaction.commit();
            session.close();
            writer.print("ok");
            writer.close();
        }else {
            session.close();
            writer.print("exsit");
            writer.close();
        }

    }
}
