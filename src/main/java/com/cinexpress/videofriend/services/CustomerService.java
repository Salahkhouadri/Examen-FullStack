package com.cinexpress.videofriend.services;

import java.util.List;

import com.cinexpress.videofriend.models.Customer;
import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.models.Recommendation;

public interface CustomerService {
    void createCustomer(Customer customer);
    Customer getCustomerById(Long id);
    void updateCustomer(Long id, Customer customer);
    void addClientToCompany(Long customerId, Long companyId);
    List<Movie> listAllCustomerMovies(Long customerId);
    boolean hasPremiumSubscription(Long customerId);
    void deactivatePremiumSubscription(Long customerId);
    List<Recommendation> getRecommendations(Long customerId);
    void activatePremiumSubscription(Long customerId);
}
