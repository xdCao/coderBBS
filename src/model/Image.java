package model;

import org.apache.commons.fileupload.FileItem;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * Created by xdcao on 2017/3/15.
 */
@Entity
public class Image {
    private int id;
    private FileItem content;
    private Integer index;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = true)
    public FileItem getContent() {
        return content;
    }

    public void setContent(FileItem content) {
        this.content = content;
    }

    @Basic
    @Column(name = "index", nullable = true)
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


}
