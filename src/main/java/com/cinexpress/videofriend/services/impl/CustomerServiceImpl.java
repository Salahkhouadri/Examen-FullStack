package com.cinexpress.videofriend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinexpress.videofriend.models.Company;
import com.cinexpress.videofriend.models.Customer;
import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Override
    public void addClientToCompany(Customer customer, Company company) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addClientToCompany'");
    }

    @Override
    public List<Movie> listAllCustomerMovies(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAllCustomerMovies'");
    }

    @Override
    public boolean hasPremiumSubscription(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasPremiumSubscription'");
    }

    @Override
    public void updatePremiumSubscription(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePremiumSubscription'");
    }
    
}
