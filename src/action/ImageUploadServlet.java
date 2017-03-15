package action;

import javafx.geometry.Pos;
import model.Image;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xdcao on 2017/3/15.
 */
public class ImageUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入servlet");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //设置上传文件大小的上限，-1表示无上限
        fileUpload.setSizeMax(-1);
        List items = new ArrayList();
        //上传文件，解析表单中包含的文件字段和普通字段
        try {
            items = fileUpload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        //遍历字段进行处理
        Iterator iterator = items.iterator();
        while(iterator.hasNext()){
            FileItem fileItem =(FileItem)iterator.next();
            if(fileItem.isFormField()){//普通字段
                    System.out.println("普通字段");
            }else{//文件字段
                if("myImage".equals(fileItem.getFieldName())){
                    System.out.println(fileItem.getName());
                    //上传;
                    try {
                        File file=new File("D:\\myCoderBBSTemp\\",fileItem.getName());
                        fileItem.write(file);
                        Image image=new Image();
                        image.setContent(file.getAbsolutePath());
                        image.setImageIndex(1);
                        Session session= Main.getSession();
                        Transaction transaction=session.beginTransaction();
                        session.save(image);
                        transaction.commit();
                        session.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("111");
    }
}
