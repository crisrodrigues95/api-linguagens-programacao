package com.cristhian.linguagensApi;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinguagemRepository extends MongoRepository<Linguagem, String> {

    List<Linguagem> findByOrderByRanking();
    List<Linguagem> findByOrderByTitle();
    List<Linguagem> findByTitleContainingIgnoreCase(String txt);
    List<Linguagem> deleteByTitleContainingIgnoreCase(String txt);
    
}