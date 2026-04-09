package com.example.mongoplayground1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongoplayground1.entity.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
    
}
