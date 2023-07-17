package com.yangcan.service;

import com.yangcan.dto.CreateBookDTO;
import com.yangcan.dto.UpdateBookDTO;
import com.yangcan.vo.BookVO;
import com.yangcan.entity.book.Book;
import com.yangcan.repository.BookBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    @Resource
    private BookBaseRepository bookbaseRepository;

    /**
     * 添加图书
     * @param createBookDTO 图书详情
     */
    public void addBook(CreateBookDTO createBookDTO){
        log.info("BookService addBook param:{}", createBookDTO);
        Book book = Book.genBookFromCreateBookDTO(createBookDTO);
        bookbaseRepository.save(book);
    }

    /**
     * 根据图书Id查找图书
     * @param bookId 图书Id
     * @return Book
     */
    @Cacheable(cacheNames = "book", key = "#bookId")
    public BookVO searchByBookId(Long bookId) {
        log.info("BookService searchByBookId param:{}", bookId);
        try {
            Book book = bookbaseRepository.findBookByBookIdAndValid(bookId, Boolean.TRUE);

            if (book == null) {
                log.info("BookService searchByBookId can not find result, bookId:{}", bookId);
                return null;
            }
            log.info("BookService searchByBookId result:{}", book);
            return BookVO.genBookVO(book);
        } catch (Exception e) {
            log.error("BookService searchByBookId fail, bookId:{}, exception:{}", bookId, e);
            return null;
        }
    }

    /**
     * 根据图书Id批量查找图书
     * @param bookIds 图书Id
     * @return 图书列表
     */
    public List<BookVO> searchByBookIds(List<Long> bookIds) {

        log.info("BookService searchByBookIds param:{}", bookIds);
        List<Book> books = bookbaseRepository.findAllByBookIdInAndValid(bookIds, Boolean.TRUE);
        log.info("BookService searchByBookIds result:{}", books);

        List<BookVO> bookVOS = new ArrayList<>();
        books.forEach(book -> bookVOS.add(BookVO.genBookVO(book)));
        return bookVOS;
    }

    /**
     * 根据图书名称模糊查询
     * @param name 图书名称
     * @return 图书列表
     */
    @Cacheable(cacheNames = "book", key = "#name")
    public List<BookVO> searchByName(String name) {
        log.info("BookService searchByName param:{}", name);
        List<Book> books = bookbaseRepository.findByNameLike(name);
        log.info("BookService searchByName result:{}", books);

        books = books.stream().filter(book -> Boolean.TRUE.equals(book.getValid())).collect(Collectors.toList());
        List<BookVO> bookVOS = new ArrayList<>();
        books.forEach(book -> bookVOS.add(BookVO.genBookVO(book)));
        return bookVOS;
    }

    /**
     * 查找所有的图书
     * @return 图书列表
     */
    public List<BookVO> getAllBooks() {

        List<Book> books = bookbaseRepository.findAllByValid(Boolean.TRUE);
        log.info("BookService getAllBooks result:{}", books);

        List<BookVO> bookVOS = new ArrayList<>();
        books.forEach(book -> bookVOS.add(BookVO.genBookVO(book)));
        return bookVOS;
    }


    /**
     * 删除图书（软删）
     * @param bookId
     * @return 删除是否成功
     */
    public Boolean deleteById(Long bookId) {

        bookbaseRepository.deleteByBookId(bookId, new Date(), Boolean.FALSE);
        return Boolean.TRUE;
    }

    /**
     * 更新图书信息
     * @param updateBookDTO 更新参数
     * @return 更新是否成功
     */
    public Boolean updateByBook(UpdateBookDTO updateBookDTO) {
            log.info("BookService updateByBook updateBookDTO:{}", updateBookDTO);
            Book oldBook = bookbaseRepository.findBookByBookIdAndValid(updateBookDTO.getBookId(), Boolean.TRUE);

            if (oldBook == null) {
                log.warn("BookService updateByBook book not exist, bookId:{}", updateBookDTO.getBookId());
                return Boolean.TRUE;
            }

            Book newBook = oldBook;
            if (Strings.isNotBlank(updateBookDTO.getName())) {
                newBook.setName(updateBookDTO.getName());
            }
            if (Strings.isNotBlank(updateBookDTO.getAuthor())) {
                newBook.setAuthor(updateBookDTO.getAuthor());
            }
            if (Strings.isNotBlank(updateBookDTO.getPublish())) {
                newBook.setPublish(updateBookDTO.getPublish());
            }
            if (Strings.isNotBlank(updateBookDTO.getIsbn())) {
                newBook.setIsbn(updateBookDTO.getIsbn());
            }
            if (Strings.isNotBlank(updateBookDTO.getIntroduction())) {
                newBook.setIntroduction(updateBookDTO.getIntroduction());
            }
            if (Strings.isNotBlank(updateBookDTO.getLanguage())) {
                newBook.setLanguage(updateBookDTO.getLanguage());
            }
            if (Objects.nonNull(updateBookDTO.getPrice())) {
                newBook.setPrice(updateBookDTO.getPrice());
            }
            if (Objects.nonNull(updateBookDTO.getPublishDate())) {
                newBook.setPublishDate(updateBookDTO.getPublishDate());
            }
            if (Objects.nonNull(updateBookDTO.getClassId())) {
                newBook.setClassId(updateBookDTO.getClassId());
            }
            if (Objects.nonNull(updateBookDTO.getState())) {
                newBook.setState(updateBookDTO.getState());
            }
            bookbaseRepository.updateBook(newBook.getBookId(), newBook.getName(), newBook.getAuthor(), newBook.getPublish(), newBook.getIsbn(),
                    newBook.getIntroduction(), newBook.getLanguage(), newBook.getPrice(), newBook.getPublishDate(), newBook.getClassId(), newBook.getState(), new Date());
            return Boolean.TRUE;
    }
}