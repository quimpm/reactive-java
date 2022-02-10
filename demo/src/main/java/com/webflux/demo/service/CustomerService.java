package com.webflux.demo.service;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.webflux.demo.dao.CustomerDao;
import com.webflux.demo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    public List<Customer> loadAllCostumers(){
        return dao.getCustomers();
    }

    public Flux<Customer> loadAllCostumersStream(){
        return dao.getCustomersStream();
    }
}
