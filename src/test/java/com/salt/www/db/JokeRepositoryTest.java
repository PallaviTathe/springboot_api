package com.salt.www.db;

import com.salt.www.dto.JokeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JokeRepositoryTest {
    private static JokeRepository repository = new JokeRepository();


    @Test
    void shouldAddJokeToDb() {
        UUID id = UUID.randomUUID();
        JokeDTO joke = new JokeDTO("a test", "now", id);
        boolean result = repository.addJoke(joke);
        assertTrue(result, "joke should exist in the repository");
    }


    @Test
    void shouldGetAJokeFromTheRepo() {
        UUID id = UUID.randomUUID();
        JokeDTO joke = new JokeDTO("a test", "now", id);
        repository.addJoke(joke);
        JokeDTO q = repository.getJokeById(id);
        assertEquals(joke, q);
    }

    @Test
    void shouldReturnNullForNonExistingJoke() {
        UUID id = UUID.randomUUID();
        assertNull(repository.getJokeById(id));
    }

    @Test
    void shouldReturnNullForNonNullInput() {
        assertNull(repository.getJokeById(null));
    }

    @Test
    void shouldDeleteJoke() {
        UUID id = UUID.randomUUID();
        JokeDTO joke = new JokeDTO("a test", "now", id);
        repository.addJoke(joke);
        boolean result = repository.removeJoke(id);
        assertTrue(result);
        assertNull(repository.getJokeById(id));
    }

    @Test
    void shouldListJokes() {
        UUID id = UUID.randomUUID();
        JokeDTO joke = new JokeDTO("a test", "now", id);
        repository.addJoke(joke);
        List<JokeDTO> jokes = repository.listJokes();
        assertTrue(jokes.contains(joke));
    }

}
