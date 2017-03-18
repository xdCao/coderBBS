package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by xdcao on 2017/3/18.
 */
@Entity
public class Coments {
    private int id;
    private String content;
    private Date createDate;
    private Integer star;
    private Users usersByUserId;
    private Post postByPostId;

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
    @Column(name = "star", nullable = true)
    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coments coments = (Coments) o;

        if (id != coments.id) return false;
        if (content != null ? !content.equals(coments.content) : coments.content != null) return false;
        if (createDate != null ? !createDate.equals(coments.createDate) : coments.createDate != null) return false;
        if (star != null ? !star.equals(coments.star) : coments.star != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (star != null ? star.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    public Post getPostByPostId() {
        return postByPostId;
    }

    public void setPostByPostId(Post postByPostId) {
        this.postByPostId = postByPostId;
    }
}
