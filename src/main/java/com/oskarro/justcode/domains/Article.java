package com.oskarro.justcode.domains;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posted_at")
    private Date postedAt = new Date();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();

    @ManyToMany(mappedBy = "articles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Category> categories = new ArrayList<>();

    public Article(@NotNull @Size(max = 100) String title, @NotNull @Size(max = 500) String description, @NotNull String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.articles.add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getArticles().remove(this);
    }


}
