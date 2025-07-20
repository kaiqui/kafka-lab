package com.example.producer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            for (int i = 1; i <= 10; i++) {
                String msg = "Mensagem " + i;
                kafkaTemplate.send("main-topic", msg);
                System.out.println("Enviado: " + msg);
            }
        };
    }
}