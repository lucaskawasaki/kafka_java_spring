package com.lucas.strconsumer.listener;

import custom.StrConsumerCustomListener;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {

    @SneakyThrows
    @StrConsumerCustomListener(groupId = "group-1")
    public void create(String message) throws Exception {
        log.info("CREATE ::: Receive message {}", message);
        throw new Exception("Erro ao ler mensagem");
    }

    @StrConsumerCustomListener(groupId = "group-1")
    public void log(String message){
        log.info("LOG ::: Receive message {}", message);
    }

    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void history(String message){
        log.info("HISTORY ::: Receive message {}", message);
    }

    @KafkaListener(groupId = "group-3", topics = "str-topic", containerFactory = "earlContainerFactory")
    public void listenerEarl(String message){
        log.info("EARLIEST ::: Receive message {}", message);
    }
}
