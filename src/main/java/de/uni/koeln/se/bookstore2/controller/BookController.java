package de.uni.koeln.se.bookstore2.controller;

import de.uni.koeln.se.bookstore2.datamodel.Book;
import de.uni.koeln.se.bookstore2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/bookStore")
@RestController
public class BookController {

    @Autowired
    BookService bookSer;

    @GetMapping
    public ResponseEntity<List<Book>> getAllbooks() {

        List<Book> books = new ArrayList<Book>();
        books = bookSer.findBooks();

        return new ResponseEntity<>(books, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {

        return new ResponseEntity<>(bookSer.fetchBook(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        bookSer.addBook(book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> removeBookById(@PathVariable int id) {

        Book book = bookSer.fetchBook(id).get();

        if (bookSer.deleteBook(id)) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/latest")
    public ResponseEntity<Book> getLatestBook() {

        List<Book> books = new ArrayList<Book>();
        books = bookSer.findBooks();
        Book latestBook = books.get(1);

        for (int i = 2; i < books.size(); i++) {
            if (books.get(i).getDateYear() > latestBook.getDateYear()) {
                latestBook = books.get(i);
            }

        }
        return new ResponseEntity<>(latestBook, HttpStatus.OK);
    }

    @GetMapping("/oldest")
    public ResponseEntity<Book> getOldestBook() {

        List<Book> books = new ArrayList<Book>();
        books = bookSer.findBooks();
        Book oldestBook = books.get(1);

        for (int i = 2; i < books.size(); i++) {
            if (books.get(i).getDateYear() < oldestBook.getDateYear()) {
                oldestBook = books.get(i);
            }

        }
        return new ResponseEntity<>(oldestBook, HttpStatus.OK);
    }

}
