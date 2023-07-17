package com.yangcan.entity.book;

import com.yangcan.dto.CreateBookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long bookId;

    private String name;

    private String author;

    private String publish;

    private String isbn;

    private String introduction;

    private String language;

    private BigDecimal price;

    private Date publishDate;

    private Integer classId;

    private Integer state;

    private Boolean valid;

    private Date updateTime;

    private Date createTime;

    public static Book genBookFromCreateBookDTO(CreateBookDTO createBookDTO) {
        Book book = Book.builder()
                .name(createBookDTO.getName())
                .author(createBookDTO.getAuthor())
                .publish(createBookDTO.getPublish())
                .isbn(createBookDTO.getIsbn())
                .introduction(createBookDTO.getIntroduction())
                .language(createBookDTO.getLanguage())
                .price(createBookDTO.getPrice())
                .publishDate(createBookDTO.getPublishDate())
                .classId(createBookDTO.getClassId())
                .state(createBookDTO.getState())
                .createTime(new Date())
                .valid(Boolean.TRUE)
                .build();
        book.setUpdateTime(book.getCreateTime());
        return book;
    }
}
