package com.webflux.demo.handler;

import com.webflux.demo.dao.CustomerDao;
import com.webflux.demo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> getCostumers(ServerRequest request){
        Flux<Customer> customersStream = customerDao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream, Customer.class);
    }

    public Mono<ServerResponse> findCostumer (ServerRequest request){
        int customerId = Integer.valueOf(request.pathVariable("id"));
        Mono<Customer> customerMono = customerDao.getCustomersList().filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCostumer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> customerString = customerMono.map(dto -> dto.getId()+" : "+dto.getName());
        return ServerResponse.ok().body(customerString, String.class);

    }

}
