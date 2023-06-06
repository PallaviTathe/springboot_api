package com.salt.www;


import com.salt.www.db.JokeRepository;
import com.salt.www.dto.JokeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class JokeController {
    private static JokeRepository repository = new JokeRepository();
    @Autowired
    private JokeFetcher jokeFetcher;

    @PutMapping("/jokes")
    public ResponseEntity<JokeDTO> putJoke() {
        JokeDTO joke = jokeFetcher.fetchJoke();
        repository.addJoke(joke);
        return ResponseEntity.created(URI.create("http://localhost:8080/api/jokes/"+joke.id())).contentType(MediaType.APPLICATION_JSON).body(joke);
    }

    @GetMapping("/jokes/{jokeId}")
    public ResponseEntity<JokeDTO> getJokeId(@PathVariable UUID jokeId) {
        JokeDTO joke = repository.getJokeById(jokeId);
        if (joke != null) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(joke);
        }
        return ResponseEntity.notFound().build();
    }
}
