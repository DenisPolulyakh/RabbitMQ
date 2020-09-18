package main.www.javaee.www;

import lombok.extern.slf4j.Slf4j;
import main.www.javaee.www.rabbit4.RountingKey;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitConfiguration {

    //Насйтроики соединения с RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }


    //объявляем очередь с именем example1
    @Bean
    public Queue myQueue1() {
        return new Queue("example1");
    }


    //объявляем очередь с именем example2
    @Bean
    public Queue myQueue2() {
        return new Queue("example2");
    }


    //объявляем очередь с именем example3
    @Bean
    public Queue myQueue3() {
        return new Queue("example3");
    }


    //объявляем очередь с именем example4
    @Bean
    public Queue myQueue4() {
        return new Queue("example4");
    }


    //объявляем очередь с именем example5
    @Bean
    public Queue myQueue5() {
        return new Queue("example5");
    }


    //объявляем очередь с именем example6
    @Bean
    public Queue myQueue6() {
        return new Queue("example6");
    }


    //объявляем очередь с именем example7
    @Bean
    public Queue myQueue7() {
        return new Queue("example7");
    }


    //объявляем очередь с именем example8
    @Bean
    public Queue myQueue8() {
        return new Queue("example8");
    }




    //Publish/Subscribe
    @Bean
    public FanoutExchange fanoutExchangeA() {
        return new FanoutExchange("exchange-example-3");
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(myQueue3()).to(fanoutExchangeA());
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(myQueue4()).to(fanoutExchangeA());
    }

    //Routing
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    public Binding errorBinding1(){
        return BindingBuilder.bind(myQueue5()).to(directExchange()).with("error");
    }

    @Bean
    public Binding errorBinding2(){
        return BindingBuilder.bind(myQueue6()).to(directExchange()).with("error");
    }

    @Bean
    public Binding infoBinding(){
        return BindingBuilder.bind(myQueue5()).to(directExchange()).with("info");
    }

    @Bean
    public Binding warningBinding(){
        return BindingBuilder.bind(myQueue5()).to(directExchange()).with("warning");
    }

    //Topic

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic-exchange");
    }


    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(myQueue7()).to(topicExchange()).with("*.orange.*");
    }

    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(myQueue8()).to(topicExchange()).with("*.*.rabbit");
    }

    @Bean
    public Binding topicBinding3(){
        return BindingBuilder.bind(myQueue8()).to(topicExchange()).with("lazy.#");
    }




}
