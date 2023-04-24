package com.example.demo;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:activemqBroker.properties")
public class ActiveMQConfig {

    @Value("${internal.broker-url}")
    private String brokerUrlInternal;
    @Value("${external.broker-url}")
    private String brokerUrlExternal;

    @Autowired
    private CamelContext context;
    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory  = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrlInternal);
        return  activeMQConnectionFactory;
    }
    @Bean
    public JmsComponent jmsComponent() throws JMSException {
        // Create the Camel JMS component and wire it to our Artemis connectionfactory
        JmsComponent jms = new JmsComponent();
        jms.setConnectionFactory(connectionFactory());
        context.addComponent("internal", jms);
        return jms;
    }

    @Bean
    public ConnectionFactory connectionFactory2(){
        ActiveMQConnectionFactory activeMQConnectionFactory  = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrlExternal);
        return  activeMQConnectionFactory;
    }
    @Bean
    public JmsComponent jmsComponent2() throws JMSException {
        // Create the Camel JMS component and wire it to our Artemis connectionfactory
        JmsComponent jms = new JmsComponent();
        jms.setConnectionFactory(connectionFactory2());
        context.addComponent("external", jms);
        return jms;
    }
}
