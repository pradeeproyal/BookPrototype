package com.example.bookprototype.services;


import com.example.bookprototype.Result;
import com.example.bookprototype.dto.BookDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookService {

    /* CRUD operations on BookDTO */
    Mono<BookDto> getBookById(int id);
    Mono<Result> updateBook(BookDto bookDTO);
    Mono<Result> deleteBook(int id);

    Mono<List<BookDto>> GetAllBooks();


    Mono<Result> createBook(BookDto bookDTO);



}
