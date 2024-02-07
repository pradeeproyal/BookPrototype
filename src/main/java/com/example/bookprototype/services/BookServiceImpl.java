package com.example.bookprototype.services;

import com.example.bookprototype.Result;
import com.example.bookprototype.dto.BookDto;
import com.example.bookprototype.entity.Book;
import com.example.bookprototype.repository.DynamoDbBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
//import com.example.bookprototype.repository.DynanoBookRepository;
import java.util.List;
import java.util.Objects;

import static com.example.bookprototype.Result.FAILURE;
import static com.example.bookprototype.Result.SUCCESS;


@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private DynamoDbBookRepository bookRepository;



    @Autowired
    private ModelMapper modelMapper;

        @Override
        public Mono<BookDto> getBookById(int id) {

          return    Mono.fromFuture(bookRepository.findById(id))
                  .map(book -> modelMapper.map(book, BookDto.class))
                    .doOnSuccess(Objects::requireNonNull)
                    .onErrorReturn(new BookDto());
          

        }

        @Override
        public Mono<Result> updateBook(BookDto bookDTO) {
            Book book= modelMapper.map(bookDTO, Book.class);
            return Mono.fromFuture(bookRepository.save(book))
                    .thenReturn(SUCCESS)
                    .onErrorReturn(FAILURE);


        }

        @Override
        public Mono<Result> deleteBook(int id) {
            return Mono.fromFuture(bookRepository.deleteById(id))
                    .doOnSuccess(Objects::requireNonNull)
                    .thenReturn(SUCCESS)
                    .onErrorReturn(FAILURE);
        }

        @Override
        public Mono<List<BookDto>> GetAllBooks() {
           return  Flux.from(bookRepository.findAll().items())
                        .map(book -> modelMapper.map(book, BookDto.class))
                        .collectList();
                       // .onErrorReturn(Collections.emptyList());

        }

        //create a new book from DTO
    public Mono<Result> createBook(BookDto bookDTO) {

        Book book= modelMapper.map(bookDTO, Book.class);
        return Mono.fromFuture(bookRepository.save(book))
                .thenReturn(SUCCESS)
                .onErrorReturn(FAILURE);
    }
}

