package com.example.bookprototype.repository;

import com.example.bookprototype.entity.Book;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Integer> {
}
