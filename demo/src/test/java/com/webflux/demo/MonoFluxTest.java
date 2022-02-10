package com.webflux.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<String> monoStr = Mono.just("Patata").log();
        monoStr.subscribe(System.out::println);
    }

    @Test
    public void testMonoError(){
        Mono<?> monoStr = Mono.just("Patata")
                .then(Mono.error(new RuntimeException("Exception occured")))
                .log();
        monoStr.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<String> fluxStr = Flux.just("Patata", "Pastanaga", "Col", "Banana")
                .concatWithValues("Blahblah")
                .concatWith(Flux.error(new RuntimeException("Ve un resplandor y aseee buuum")))
                .concatWithValues("Some more blahblah")
                .log();
        fluxStr.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFluxError(){
        Flux<String> fluxStr = Flux.just("Patata", "Pastanaga", "Col", "Banana")
                .concatWithValues("Blahblah")
                .log();
        fluxStr.subscribe(System.out::println);
    }

}
