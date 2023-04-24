package com.example.demo;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.stereotype.Component;

/**
 * A Camel Java DSL Router
 */
@Component
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//        JmsComponent activeMQComponent = new JmsComponent();
//        activeMQComponent.setConnectionFactory(connectionFactory);
//        getContext().addComponent("jms", activeMQComponent);
        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
//        from("file:src/data?noop=true")
//            .choice()
//                .when(xpath("/person/city = 'London'"))
//                    .log("UK message")
//                    .to("file:target/messages/uk")
//                .otherwise()
//                    .log("Other message")
//                    .to("file:target/messages/others");
        from("internal:queue:WaitingLobby")
        .log("Sending message")
        .to("external:topic:processed");
        from("external:topic:processed")
        .log("Processing message");
    }

}
