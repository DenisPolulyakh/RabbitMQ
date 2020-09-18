package main.www.javaee.www.rabbit4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class Consumer4 {
    Random random = new Random();

    @RabbitListener(queues = "example5")
    public void worker1(String message) {
        log.info("direct-worker1: {}", message);
    }

    @RabbitListener(queues = "example6")
    public void worker2(String message){
        log.info("direct-worker2: {}", message);
    }


}
