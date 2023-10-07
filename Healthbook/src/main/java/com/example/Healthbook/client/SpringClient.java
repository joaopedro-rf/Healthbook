package com.example.Healthbook.client;

import com.example.Healthbook.model.Pacient;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {

        ResponseEntity<Pacient> entity = new RestTemplate().getForEntity("http://localhost:8080/pacients/{id}", Pacient.class, 2);
        log.info(entity);

        Pacient object = new RestTemplate().getForObject("http://localhost:8080/pacients/{id}", Pacient.class,2 );

        log.info(object);
    }
}
