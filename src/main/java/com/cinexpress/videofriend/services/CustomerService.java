package com.cinexpress.videofriend.services;

import java.util.List;

import com.cinexpress.videofriend.models.Company;
import com.cinexpress.videofriend.models.Customer;
import com.cinexpress.videofriend.models.Movie;

public interface CustomerService {
    void addClientToCompany(Customer customer, Company company);
    List<Movie> listAllCustomerMovies(Customer customer);
    boolean hasPremiumSubscription(Customer customer);
    void updatePremiumSubscription(Customer customer);
}
