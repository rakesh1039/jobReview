package com.job.jobApp.company;

import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {

    public List<Company> findAll();

    Company getCompanyById(Long id);

    void createCompany(Company company);

    boolean updateCompany(Long id, Company updatecompany);

    boolean deleteCompany(Long id);
}
