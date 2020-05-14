package com.mycompany.online_shop_demo_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Clob;

@Data
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("author"),
        })
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description", columnDefinition = "CLOB", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "price", nullable = false)
    private double price;

}
