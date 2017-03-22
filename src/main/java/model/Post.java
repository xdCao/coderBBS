package main.java.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

/**
 * Created by xdcao on 2017/3/14.
 */
@Entity
public class Post {
    private int id;
    private String title;
    private String author;
    private Date createDate;
    private Integer scan;
    private Integer favor;
    private String content;
    private Integer ownId;
    private Collection<Coments> comentssById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 255)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "create_date", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "scan", nullable = true)
    public Integer getScan() {
        return scan;
    }

    public void setScan(Integer scan) {
        this.scan = scan;
    }

    @Basic
    @Column(name = "favor", nullable = true)
    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (author != null ? !author.equals(post.author) : post.author != null) return false;
        if (createDate != null ? !createDate.equals(post.createDate) : post.createDate != null) return false;
        if (scan != null ? !scan.equals(post.scan) : post.scan != null) return false;
        if (favor != null ? !favor.equals(post.favor) : post.favor != null) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (scan != null ? scan.hashCode() : 0);
        result = 31 * result + (favor != null ? favor.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "own_id", nullable = true)
    public Integer getOwnId() {
        return ownId;
    }

    public void setOwnId(Integer ownId) {
        this.ownId = ownId;
    }

    @OneToMany(mappedBy = "postByPostId")
    public Collection<Coments> getComentssById() {
        return comentssById;
    }

    public void setComentssById(Collection<Coments> comentssById) {
        this.comentssById = comentssById;
    }
}
