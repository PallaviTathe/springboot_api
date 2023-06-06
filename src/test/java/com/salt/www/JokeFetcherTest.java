package com.salt.www;

import com.salt.www.dto.JokeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JokeFetcherTest {

    @Autowired
    private JokeFetcher jokeFetcher;

    @Test
    public void getsJoke(){
        JokeDTO joke = jokeFetcher.fetchJoke();
        assertNotNull(joke.text());
    }

}
