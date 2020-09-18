package main.www.javaee.www.producer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.www.javaee.www.rabbit4.RountingKey;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@AllArgsConstructor
@Slf4j
@Component
@NoArgsConstructor
class ThreadRunner implements Runnable {
    private final Random random = new Random();
    private boolean run = true;
    @Autowired
    private AmqpTemplate template;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * Отдельный поток отправляет сообщения в очереди
     */
    @Override
    public void run() {
        while (run) {
            try {
                Thread.sleep(random.nextInt(1001));
                //hello world
                //template.convertAndSend("example1", "Hello, World");
                //Work queues
                //template.convertAndSend("example2", "Try, Try, Try, catch....");
                //Publish/Subscribe
                //rabbitTemplate.setExchange("exchange-example-3");
                //rabbitTemplate.convertAndSend("Fanout message...");
                //Routing
                rabbitTemplate.setExchange("direct-exchange");
                rabbitTemplate.convertAndSend("info", "info message");
                rabbitTemplate.convertAndSend("error","error message");
                rabbitTemplate.convertAndSend("warning","warning message");


            } catch (InterruptedException e) {
                log.error("Ошибка работы в потоке ", e);
                return;
            }
        }
    }

    public void setRun(boolean b) {
        run = b;
    }


}
