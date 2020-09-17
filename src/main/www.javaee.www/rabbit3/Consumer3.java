package main.www.javaee.www.rabbit3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@EnableRabbit
@Component
@Slf4j
public class Consumer3 {
    Random random = new Random();

    @RabbitListener(queues = "example3")
    public void worker1(String message) throws InterruptedException {
       log.info("fanout-worker1: {}",message);
       //Thread.sleep(100*random.nextInt(20));
    }

    @RabbitListener(queues = "example4")
    public void worker2(String message) throws InterruptedException {
        log.info("fanout-worker2: {}",message);
        //Thread.sleep(100*random.nextInt(20));
    }



}
