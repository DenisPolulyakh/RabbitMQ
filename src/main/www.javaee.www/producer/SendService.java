package main.www.javaee.www.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendService {

    @Autowired
    @Qualifier("rabbitTemplate")
    RabbitTemplate topicTemplate;

    @Autowired
    @Qualifier("directReplyTo")
    RabbitTemplate directReplyTo;

    @Autowired
    private ThreadRunner threadRunner;
    private Thread thread;


    public void startSendThread() {
        if (thread == null) {
            threadRunner.setRun(true);
            thread = new Thread(threadRunner);
            thread.start();
            log.info("Запуск потока отправки сообщений");

        } else if (thread.isAlive()) {
            log.info("Поток крутится, лавеха мутится");
        }


    }

    public void stopThread() {
        log.info("Остановка потока отправки сообщений");
        thread = null;
        threadRunner.setRun(false);
    }


    public void sendTopic(String key, String message) {
        topicTemplate.setExchange("topic-exchange");
        topicTemplate.convertAndSend(key, message);
    }


    public Integer directReplyTo(String message) {
        Integer response = (Integer) topicTemplate.convertSendAndReceive("example9", message);
        return response;
    }
}
