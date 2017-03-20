package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by xdcao on 2017/3/20.
 */
@Entity
public class Reply {
    private int id;
    private String content;
    private Date createDate;
    private String username;
    private Coments comentsByCommentId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reply reply = (Reply) o;

        if (id != reply.id) return false;
        if (content != null ? !content.equals(reply.content) : reply.content != null) return false;
        if (createDate != null ? !createDate.equals(reply.createDate) : reply.createDate != null) return false;
        if (username != null ? !username.equals(reply.username) : reply.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    public Coments getComentsByCommentId() {
        return comentsByCommentId;
    }

    public void setComentsByCommentId(Coments comentsByCommentId) {
        this.comentsByCommentId = comentsByCommentId;
    }
}
