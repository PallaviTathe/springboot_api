package com.salt.www;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salt.www.dto.JokeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@Service
public class JokeFetcher {

    @Value("${joker.source.url}")
    String url;

    @JsonIgnoreProperties(ignoreUnknown = true)
    record JokeResponse(String text){
    }

    public JokeDTO fetchJoke(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JokeResponse> entity = restTemplate.getForEntity(url, JokeResponse.class);
        String now = ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        UUID id = UUID.randomUUID();
        //entity.getStatusCode();
        return new JokeDTO(entity.getBody().text, now, id);
    }
}
