package com.example.mongoplayground1.repository;

import com.example.mongoplayground1.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
