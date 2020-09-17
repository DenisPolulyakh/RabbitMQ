package main.www.javaee.www;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitConfiguration {

    //Насйтроики соединения с RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(){
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory());
    }


    //объявляем очередь с именем example1
    @Bean
    public Queue myQueue1(){
        return new Queue("example1");
    }


    //объявляем очередь с именем example2
    @Bean
    public Queue myQueue2(){
        return new Queue("example2");
    }



    //объявляем очередь с именем example3
    @Bean
    public Queue myQueue3(){
        return new Queue("example3");
    }


    //объявляем очередь с именем example4
    @Bean
    public Queue myQueue4(){
        return new Queue("example4");
    }

    @Bean
    public FanoutExchange fanoutExchangeA(){
        return new FanoutExchange("exchange-example-3");
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(myQueue3()).to(fanoutExchangeA());
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(myQueue4()).to(fanoutExchangeA());
    }
}
