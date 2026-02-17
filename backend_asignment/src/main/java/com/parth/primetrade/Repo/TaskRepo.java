package com.parth.primetrade.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.parth.primetrade.Entity.Task;
 
@Repository
public interface TaskRepo extends MongoRepository<Task, String> {

    List<Task> findByUserId(String userId);

    Optional<Task> findByIdAndUserId(String id, String userId);
}
