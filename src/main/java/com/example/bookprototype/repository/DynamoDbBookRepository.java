package com.example.bookprototype.repository;

import com.example.bookprototype.entity.Book;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.util.concurrent.CompletableFuture;

@Repository
public class DynamoDbBookRepository {
    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private final DynamoDbAsyncTable<Book> bookDynamoDbAsyncTable;

    public DynamoDbBookRepository(DynamoDbEnhancedAsyncClient asyncClient) {
        this.enhancedAsyncClient = asyncClient;
        this.bookDynamoDbAsyncTable = enhancedAsyncClient.table(Book.class.getSimpleName(), TableSchema.fromBean(Book.class));
    }

    public CompletableFuture<Void> save(Book book) {
        return bookDynamoDbAsyncTable.putItem(book);
    }

    //Read
    public CompletableFuture<Book> findById(int id) {
        try{
            return bookDynamoDbAsyncTable.getItem(r -> r.key(k -> k.partitionValue(id)));
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    //Update
    public CompletableFuture<Book> update(Book book) {
        return bookDynamoDbAsyncTable.updateItem(book);
    }

    //Delete
    public CompletableFuture<Book> deleteById(int id) {
        return bookDynamoDbAsyncTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }

    //Getall
    public PagePublisher<Book> findAll() {
        return bookDynamoDbAsyncTable.scan();
    }
}
