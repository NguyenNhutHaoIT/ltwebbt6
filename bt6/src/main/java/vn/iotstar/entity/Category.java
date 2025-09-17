package vn.iotstar.entity;

import java.io.Serializable;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="Category")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String categoryname;

    @Column(columnDefinition = "NVARCHAR(500)")
    private String icon;	

    // Quan hệ Many-to-One với User
    @ManyToOne
    @JoinColumn(name = "user_id")   // khóa ngoại trong bảng Category
    private User user;

    public Category() {}

    // getter, setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCategoryname() { return categoryname; }
    public void setCategoryname(String categoryname) { this.categoryname = categoryname; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
