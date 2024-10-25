package com.infosys.retailer.controller;

import com.infosys.retailer.dto.CustomerDto;
import com.infosys.retailer.entity.Customer;
import com.infosys.retailer.service.CustomerService;
import com.infosys.retailer.variables.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.CUSTOMER)
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto){
        Customer customer = customerService.createCustomer(customerDto);
        return ResponseEntity.ok(customer);
    }

}
