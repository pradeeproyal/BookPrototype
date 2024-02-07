package com.example.bookprototype.controllers;

import com.example.bookprototype.Result;
import com.example.bookprototype.services.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.bookprototype.dto.BookDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private  BookService bookService;

    //Reactive API to get all books
    @GetMapping("/books")
    public Mono<List<BookDto>> getAllBooks() {
        return bookService.GetAllBooks();
    }

    //Reactive API to get a book by id
    @GetMapping("/books/{id}")
    public Mono<BookDto> getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    //Reactive API to create a new book
    @PostMapping("/books")
    public Mono<Result> createBook(@Valid @RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

   //Reactive API to update a book
    @PostMapping("/books/{id}")
    public Mono<Result> updateBook(@PathVariable int id, @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        return  bookService.updateBook(bookDto);
    }

    //Reactive API to delete a book
    @DeleteMapping("/books/{id}")
    public Mono<Result> deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }


}
