package com.yangcan.repository;

import com.yangcan.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface BookBaseRepository extends JpaRepository<Book, Long> {

    Book findBookByBookIdAndValid(Long bookId, Boolean valid);

    List<Book> findAllByBookIdInAndValid(List<Long> bookIds, Boolean valid);

    List<Book> findAllByValid(Boolean valid);


    @Query(value = "select * from book where name like %?1%", nativeQuery = true)
    List<Book> findByNameLike(String name);

    @Transactional
    @Modifying
    @Query(value = "update book set valid = ?3, update_time = ?2 where book_id = ?1", nativeQuery = true)
    void deleteByBookId(Long bookId, Date updateTime, Boolean valid);

    @Transactional
    @Modifying
    @Query(value = "update book set name = ?2, author = ?3, publish = ?4, isbn = ?5, introduction = ?6, language = ?7, price = ?8, publish_date = ?9, class_id = ?10, state = ?11, update_time = ?12 where book_id = ?1", nativeQuery = true)
    void updateBook(Long bookId, String name, String author, String publish, String isbn, String introduction, String language, BigDecimal price, Date publishDate, int classId, int state, Date updateTime);
}