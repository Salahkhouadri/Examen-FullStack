package com.cinexpress.videofriend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "related_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    //el inventorio donde se encuentra la pelicula
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    //el producto es una pelicula
    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
