package com.cinexpress.videofriend.services;

import com.cinexpress.videofriend.models.Company;

public interface CompanyService {
    void createCompany(Company company);
    void deleteCompany(Long id);
}
