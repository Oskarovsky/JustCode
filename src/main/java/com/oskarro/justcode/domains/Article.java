package com.oskarro.justcode.domains;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    @Lob
    private String content;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posted_at")
    private Date postedAt = new Date();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories = new HashSet<>();

    public Article(@NotNull @Size(max = 100) String title, @NotNull @Size(max = 500) String description, @NotNull String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.getArticles().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getArticles().remove(this);
    }


}
