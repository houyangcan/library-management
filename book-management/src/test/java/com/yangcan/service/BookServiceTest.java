package com.yangcan.service;

import com.yangcan.dto.CreateBookDTO;
import static org.junit.Assert.*;

import com.yangcan.dto.UpdateBookDTO;
import com.yangcan.vo.BookVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BookServiceTest {

    @Resource
    BookService bookService;

    @Test
    void addBookTest() {
        CreateBookDTO book = CreateBookDTO.builder()
                .author("David")
                .classId(1)
                .introduction("I")
                .isbn("423525")
                .language("En")
                .name("book1")
                .price(new BigDecimal("99.80"))
                .publish("BEIJING")
                .publishDate(new Date())
                .state(1)
                .build();
        bookService.addBook(book);
        List<BookVO> books1 = bookService.getAllBooks();
        System.out.println("books1"+ books1);
        BookVO book1 = bookService.searchByBookId(15L);
        System.out.println(book1);
    }

    @Test
    void allFuncTest() {
        CreateBookDTO book1 = CreateBookDTO.builder()
                .author("David")
                .classId(1)
                .introduction("I")
                .isbn("423525")
                .language("En")
                .name("book1")
                .price(new BigDecimal("99.80"))
                .publish("TIANJIN")
                .publishDate(new Date())
                .state(1)
                .build();
        bookService.addBook(book1);
        CreateBookDTO book2 = CreateBookDTO.builder()
                .author("SAM")
                .classId(1)
                .introduction("GGGG")
                .isbn("42423535")
                .language("En")
                .name("book2")
                .price(new BigDecimal("96.80"))
                .publish("CHONGQING")
                .publishDate(new Date())
                .state(0)
                .build();
        bookService.addBook(book2);
        List<BookVO> books = bookService.searchByBookIds(Arrays.asList(2L, 3L));
        //List<BookVO> books = bookService.getAllBooks();
        System.out.println("books" + books);

        List<BookVO> books2 = bookService.searchByName("book");
        System.out.println("books2"+ books2);

        UpdateBookDTO updateBookDTO = UpdateBookDTO.builder()
                .bookId(2L)
                .name("bookV123")
                .build();
        bookService.updateByBook(updateBookDTO);
        BookVO book3 = bookService.searchByBookId(2L);
        System.out.println("books3"+ book3);

        bookService.deleteById(1L);
        List<BookVO> books1 = bookService.getAllBooks();
        System.out.println("books1"+ books1);

    }
}