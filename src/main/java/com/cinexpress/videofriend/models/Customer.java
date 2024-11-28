package com.cinexpress.videofriend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String type;

    @ElementCollection
    private List<String> preferences;

    private String subscription;

    //FALTABAN : relatedProduct, promotions, Recommendation

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<RelatedProduct> relatedProducts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "premium_subscription_id", referencedColumnName = "id")
    private PremiumSubscription premiumSubscription;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Promotion> promotions;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations;

    //movies alquiladas --> One to Many
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Movie> movies;
}
