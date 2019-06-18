package com.oskarro.justcode.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;

    @Size(max = 100)
    @NaturalId
    @Column(name = "name_category")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "category_article",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    private Set<Article> articles;


    public Category(@NotNull @Size(max = 100) String name, HashSet<Article> hashSet) {
        this.name = name;
        this.articles = hashSet;
    }

    public void addArticle(Article article) {
        this.articles.add(article);
    }

}
