package com.example.demo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * A Camel Java DSL Router
 */
@Lazy
@Component
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("external:queue:WaitingLobby")
        .log("Processing message")
        .to("internal:queue:test");
    }

}
