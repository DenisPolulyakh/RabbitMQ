package main.www.javaee.www.rabbit2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@EnableRabbit
@Component
@Slf4j
public class Consumer2 {
    Random random = new Random();

    @RabbitListener(queues = "example2")
    public void worker1(String message) throws InterruptedException {
       log.info("worker1: {}",message);
       Thread.sleep(100*random.nextInt(20));
    }

    @RabbitListener(queues = "example2")
    public void worker2(String message) throws InterruptedException {
        log.info("worker2: {}",message);
        Thread.sleep(100*random.nextInt(20));
    }


    @RabbitListener(queues = "example2")
    public void worker3(String message) throws InterruptedException {
        log.info("worker3: {}",message);
        Thread.sleep(100*random.nextInt(20));
    }
}
