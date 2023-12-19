package com.example.Healthbook.client;

import com.example.Healthbook.model.Pacient;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class SpringClient {
    public static void main(String[] args) {

        ResponseEntity<Pacient> entity = new RestTemplate().getForEntity("http://localhost:8080/pacients/{id}", Pacient.class, 2);

        ResponseEntity<List<Pacient>> exchange = new RestTemplate().exchange("http://localhost:8080/pacients/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Pacient>>() {
                });

    }
}
