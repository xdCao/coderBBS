package action;

import jdk.internal.dynalink.linker.LinkerServices;
import model.Coments;
import model.Reply;
import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.SecondaryTableSecondPass;
import utils.Main;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xdcao on 2017/3/19.
 */
public class GetCommentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("进入进入进入进入进入进入进入进入进入进入进入进入进入");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out=resp.getWriter();
        int id=Integer.parseInt(req.getParameter("id"));
        Session session= Main.getSession();
        Transaction transaction=session.beginTransaction();
        String hql="from Coments where postByPostId.id="+id;
        Query query=session.createQuery(hql);
        List<Coments> comentss=query.getResultList();
        List<Map<String,Object>> list=new ArrayList<>();

        if(comentss.size()>0){
            for (Coments coments:comentss){
                Map<String,Object> map=new HashedMap();
                map.put("id",coments.getId());
                map.put("user",coments.getUsersByUserId().getUsername());
                map.put("content",coments.getContent());
                map.put("date",coments.getCreateDate());
                map.put("star",coments.getStar());
                List<Map<String,Object>> replyList=new ArrayList<>();
                for (Reply reply:coments.getRepliesById()){
                    Map<String,Object> map1=new HashedMap();
                    map1.put("id",reply.getId());
                    map1.put("content",reply.getContent());
                    map1.put("date",reply.getCreateDate());
                    map1.put("username",reply.getUsername());
                    replyList.add(map1);
                }
                map.put("replys",replyList);
                list.add(map);
            }
            JSONArray jsonArray=JSONArray.fromObject(list);
            out.print(jsonArray.toString());
            System.out.print(jsonArray.toString());
        }
        transaction.commit();
        session.close();
        out.close();
    }
}
