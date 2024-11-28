package com.cinexpress.videofriend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinexpress.videofriend.models.Customer;
import com.cinexpress.videofriend.models.Promotion;
import com.cinexpress.videofriend.repository.CustomerRepository;
import com.cinexpress.videofriend.repository.PromotionRepository;
import com.cinexpress.videofriend.services.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addPromotion(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public List<Customer> currentCustomersWithPromotion(Promotion promotion) {
        Promotion promotionCust = promotionRepository.findById(promotion.getId()).orElseThrow();
        return promotionCust.getCustomers();
    }

    @Override
    public void addPromotionToCustomer(Long promotionId, Long customerId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow();
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        if (!customer.getPromotions().contains(promotion)) {
            customer.getPromotions().add(promotion);
            customerRepository.save(customer);
        }
    }
    
}
