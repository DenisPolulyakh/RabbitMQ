package main.www.javaee.www.rabbit1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
@Slf4j
public class Consumer1 {
    @RabbitListener(queues = "example1")
    public void processQueue(String message){
        log.info("Received from example1: {}",message);
    }
}
