package action;

import javafx.geometry.Pos;
import model.Post;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.Main;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xdcao on 2017/3/15.
 */
public class ImageUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("进入servlet");
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        ServletFileUpload fileUpload = new ServletFileUpload(factory);
//        //设置上传文件大小的上限，-1表示无上限
//        fileUpload.setSizeMax(-1);
//        List items = new ArrayList();
//        //上传文件，解析表单中包含的文件字段和普通字段
//        try {
//            items = fileUpload.parseRequest(req);
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }
//        //遍历字段进行处理
//        Iterator iterator = items.iterator();
//        int index=0;
//        Post post=null;
//        String title=null;
//        List<Image> images=new ArrayList<>();
//        while(iterator.hasNext()){
//            FileItem fileItem =(FileItem)iterator.next();
//            if(fileItem.isFormField()){//普通字段
//                    if (fileItem.getFieldName().equals("title")){
////                        System.out.print(fileItem.getString("utf-8"));
////                        Session session=Main.getSession();
////                        Transaction transaction=session.beginTransaction();
////                        String hql="from Post where title="+"'"+fileItem.getString("utf-8")+"'";
////                        Query query=session.createQuery(hql);
////                        List<Post> posts=query.getResultList();
////                        post=posts.get(0);
//                        title=fileItem.getString("utf-8");
//                    }
//            }else{//文件字段
//                if(fileItem.getFieldName().equals("myImage"+index)){
//                    System.out.println(fileItem.getName());
//                    //上传;
//                    try {
//                        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddhhmmss");
//                        String currentTime=sdf.format(new Date());
//                        String fName=currentTime+Math.round(100)+"-"+fileItem.getName();//根据时间生成文件名
//                        File file=new File("D:\\lib\\struts2\\struts-2.3.4.1\\Coder BBS\\web\\img",fName);
//                        fileItem.write(file);
//                        Image image=new Image();
//                        image.setContent(file.getAbsolutePath());
//                        image.setImageIndex(index);
////                        Session session= Main.getSession();
////                        Transaction transaction=session.beginTransaction();
////                        session.save(image);
////                        transaction.commit();
//                        images.add(image);
//                        index++;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        Session session=Main.getSession();
//        Transaction transaction=session.beginTransaction();
//        String hql="from Post where title="+"'"+title+"'";
//        Query query=session.createQuery(hql);
//        List<Post> posts=query.getResultList();
//        post=posts.get(0);
//
//        for(int i=0;i<images.size();i++){
//            images.get(i).setPostId(post.getId());
//            session.save(images.get(i));
//        }
//        String hql1="from Image where postId="+post.getId();
//        Query query1=session.createQuery(hql1);
//        List<Image> images1=query1.getResultList();
//        for(int i=0;i<images1.size();i++){
//            post.getImagesById().add(images1.get(i));
//        }
//        session.save(session.merge(post));
//        transaction.commit();
//        System.out.println("111");
    }
}
