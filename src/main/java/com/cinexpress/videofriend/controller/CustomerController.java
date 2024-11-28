package com.cinexpress.videofriend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinexpress.videofriend.models.Customer;
import com.cinexpress.videofriend.models.Movie;
import com.cinexpress.videofriend.models.Recommendation;
import com.cinexpress.videofriend.services.CustomerService;
import com.cinexpress.videofriend.services.PromotionService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    PromotionService promotionService;

    @PostMapping("/")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return ResponseEntity.ok().build();
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        Customer cust = customerService.getCustomerById(id);
        if(cust == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cust);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Void> addCustomerToCompany(@PathVariable("id") Long id, @RequestBody Long companyId) {
        customerService.addClientToCompany(id, companyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping ("/{id}/movies")
    public ResponseEntity<List<Movie>> listAllCustomerMovies(@PathVariable("id") Long customer) {
        return ResponseEntity.ok(customerService.listAllCustomerMovies(customer));
    }

    @GetMapping("/{id}/premium-subscription")
    public ResponseEntity<Boolean> checkPremiumSubscription(@PathVariable("id") Long customerId) {
        boolean hasPremium = customerService.hasPremiumSubscription(customerId);
        return ResponseEntity.ok(hasPremium);
    }
    @PutMapping("/{id}/premium-subscription/deactivate")
    public ResponseEntity<String> deactivatePremiumSubscription(@PathVariable("id") Long customerId) {
        customerService.deactivatePremiumSubscription(customerId);
        return ResponseEntity.ok("Premium subscription deactivated successfully.");
    }

    @PutMapping("/{id}/premium-subscription/activate")
    public ResponseEntity<String> activatePremiumSubscription(@PathVariable("id") Long customerId) {
        customerService.activatePremiumSubscription(customerId);
        return ResponseEntity.ok("Premium subscription activated successfully.");
    }

    @GetMapping("/{customerId}/recommendations")
    public List<Recommendation> getRecommendations(@PathVariable Long customerId) {
        return customerService.getRecommendations(customerId);
    }

    @PutMapping("/{customerId}/promotions/{promotionId}")
    public void addPromotionToCustomer(@PathVariable Long customerId, @PathVariable Long promotionId) {
        promotionService.addPromotionToCustomer(promotionId, customerId);
    }    
    
}
