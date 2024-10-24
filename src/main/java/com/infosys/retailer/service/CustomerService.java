package com.infosys.retailer.service;

import com.infosys.retailer.dto.CustomerDto;
import com.infosys.retailer.entity.Customer;

public interface CustomerService {

    Customer createCustomer(CustomerDto customerDto);
}
