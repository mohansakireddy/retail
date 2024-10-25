package com.infosys.retailer.service.impl;

import com.infosys.retailer.dto.CustomerDto;
import com.infosys.retailer.entity.Customer;
import com.infosys.retailer.repository.CustomerRepository;
import com.infosys.retailer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        return customerRepository.save(customer);
    }
}
