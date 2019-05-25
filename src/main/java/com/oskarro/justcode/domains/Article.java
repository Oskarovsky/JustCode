package com.oskarro.justcode.domains;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "categories")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "title_article")
    private String title;

    @NotNull
    @Size(max = 500)
    @Column(name = "description_article")
    private String description;

    @NotNull
    @Lob
    @Column(name = "content_article")
    private String content;

    @NotNull
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date postedAt = new Date();

    @NotNull
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date lastUpdatedAt = new Date();

    @OneToMany(
            mappedBy = "article",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "articles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Category> categories = new HashSet<>();

    public Article(@NotNull @Size(max = 100) String title, @NotNull @Size(max = 500) String description, @NotNull String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getArticles().add(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setArticle(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setArticle(null);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getArticles().remove(this);
    }

    @PreRemove
    public void removeArticlesFromCategory() {
        for (Category category : categories) {
            category.getArticles().remove(this);
        }
    }


}
