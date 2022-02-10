package com.webflux.demo.dao;

import com.webflux.demo.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers(){
        return IntStream.rangeClosed(1,50)
                .peek(CustomerDao::sleepExecution)
                .peek((i) -> System.out.println("process count: "+i))
                .mapToObj(i -> new Customer(i, "Customer"+i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream(){
        return Flux.range(1,50).map( i -> new Customer(i, "Customer"+i))
                .doOnNext((i) -> System.out.println("customer: "+i))
                .delayElements(Duration.ofSeconds(1));
    }

    public Flux<Customer> getCustomersList(){
        return Flux.range(1,50).map( i -> new Customer(i, "Customer"+i))
                .doOnNext((i) -> System.out.println("customer: "+i));
    }

}
