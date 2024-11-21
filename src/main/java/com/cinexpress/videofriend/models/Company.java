package com.cinexpress.videofriend.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Movie> movies;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Customer> customers;
}
