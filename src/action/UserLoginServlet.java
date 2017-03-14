package action;

import model.Users;
import org.hibernate.Session;
import utils.Main;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xdcao on 2017/3/11.
 */
public class UserLoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        String isRemember=req.getParameter("isRemember");
        System.out.println(userName);
        System.out.println(password);
        System.out.println(isRemember);
        int isExsit=validateUser(req,userName,password);
        PrintWriter writer=resp.getWriter();
        if (isExsit==1){
            System.out.println("欢迎登录");
            if (isRemember!=null){
                if (isRemember.equals("on")){
                    Cookie c1=new Cookie("username", userName);
                    Cookie c2=new Cookie("password",password);
                    //设置过期时间
                    c1.setMaxAge(6000);
                    c2.setMaxAge(6000);
                    //存储
                    resp.addCookie(c1);
                    resp.addCookie(c2);
                }
            }
            Users user=new Users();
            user.setUsername(userName);
            req.getSession().setAttribute("user",user);
            writer.write("1");
        }else if(isExsit==2){
            System.out.println("密码错误");
            writer.write("2");
        }else if(isExsit==3){
            System.out.println("尚未注册");
            writer.write("3");
        }
        writer.close();
    }

    private int validateUser(HttpServletRequest req,String userName, String password) {
        Session session= Main.getSession();
        String hql="from Users where username="+userName;
        System.out.println(hql);
        Query query=session.createQuery(hql);
        List<Users> usersList=query.getResultList();
        if (usersList.size()>0 ){
            Users currentUser=usersList.get(0);
            if (currentUser.getPassword().equals(password)){
                HttpSession httpSession=req.getSession();
                httpSession.setAttribute("currentUser",currentUser);
                return 1;//正确
            }else {
                return 2;//密码错误
            }
        }else {
            return 3;//尚未注册
        }

    }
}
