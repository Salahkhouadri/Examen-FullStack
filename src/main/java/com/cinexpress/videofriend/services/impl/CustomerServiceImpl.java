package com.cinexpress.videofriend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinexpress.videofriend.models.Company;
import com.cinexpress.videofriend.models.Customer;
import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.models.PremiumSubscription;
import com.cinexpress.videofriend.models.Recommendation;
import com.cinexpress.videofriend.repository.CompanyRepository;
import com.cinexpress.videofriend.repository.CustomerRepository;
import com.cinexpress.videofriend.repository.PremiumSubscriptionRepository;
import com.cinexpress.videofriend.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PremiumSubscriptionRepository premiumSubscriptionRepository;

    @Override
    public void addClientToCompany(Long customerId, Long companyId) {
        Customer customer = customerRepository.findById(customerId).get();
        Optional<Company> company = companyRepository.findById(companyId);
        if(!company.isEmpty()){
            Company updateCompany = company.get();
            updateCompany.getCustomers().add(customer);
            companyRepository.save(updateCompany);
        }
    }

    @Override
    public List<Movie> listAllCustomerMovies(Long customerId) {
        return customerRepository.findMoviesByCustomerId(customerId).get();
    }

    @Override
    public boolean hasPremiumSubscription(Long customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        return customer.getPremiumSubscription().getExclusiveCatalog();
    }

    @Override
    public void deactivatePremiumSubscription(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer niCustomer = customer.get();
            PremiumSubscription premium = new PremiumSubscription();
            premium.setDiscounts(false);
            premium.setExclusiveCatalog(false);
            premium.setPreReleases(false);
            premium.setCustomer(niCustomer);
            premiumSubscriptionRepository.save(premium);
        }
        
    }

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {            
            return null;
        }
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        Customer newCustomer = customerRepository.getReferenceById(id);
        newCustomer.setName(customer.getName());
        newCustomer.setType(customer.getType());
        customerRepository.save(newCustomer);
    }

    @Override
    public List<Recommendation> getRecommendations(Long customerId) {
        return customerRepository.findById(customerId).get().getRecommendations();
    }

    @Override
    public void activatePremiumSubscription(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        
        PremiumSubscription premiumSubscription = new PremiumSubscription();
        premiumSubscription.setExclusiveCatalog(true); 
        premiumSubscription.setDiscounts(true);
        premiumSubscription.setPreReleases(true);

        premiumSubscription.setCustomer(customer);
        customer.setPremiumSubscription(premiumSubscription);

        customerRepository.save(customer);
        premiumSubscriptionRepository.save(premiumSubscription);
    }
    
}
