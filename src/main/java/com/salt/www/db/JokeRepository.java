package com.salt.www.db;

import com.salt.www.dto.JokeDTO;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JokeRepository {
    private static final Map<UUID, JokeDTO> jokeRepository = new HashMap<>();

    public boolean addJoke(JokeDTO jokeToAdd) {
        jokeRepository.put(jokeToAdd.id(), jokeToAdd);
        return jokeRepository.containsKey(jokeToAdd.id());
    }
    public boolean removeJoke(UUID id) {
        jokeRepository.remove(id);
        return !jokeRepository.containsKey(id);
    }

    public JokeDTO getJokeById(UUID jokeID) {
        return jokeRepository.get(jokeID);
    }

    public List<JokeDTO> listJokes(){
        return new ArrayList<>(jokeRepository.values());
    }
}

