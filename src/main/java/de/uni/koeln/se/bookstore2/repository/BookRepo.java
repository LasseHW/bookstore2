package de.uni.koeln.se.bookstore2.repository;

import de.uni.koeln.se.bookstore2.datamodel.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
