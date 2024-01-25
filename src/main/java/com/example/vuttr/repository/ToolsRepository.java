package com.example.vuttr.repository;

import com.example.vuttr.model.Tool;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ToolsRepository extends MongoRepository<Tool, String> {
    boolean existsByTitle(String title);

    Optional<Tool> findByTitle(String title);

    List<Tool> findAllByTags(String tag);
}
