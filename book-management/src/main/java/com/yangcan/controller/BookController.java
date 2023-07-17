package com.yangcan.controller;

import com.yangcan.common.api.ApiCode;
import com.yangcan.common.api.ApiResult;
import com.yangcan.dto.CreateBookDTO;
import com.yangcan.dto.UpdateBookDTO;
import com.yangcan.service.BookService;
import com.yangcan.vo.BookVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/book")
@Api(value = "图书操作接口", tags = {"图书操作接口"})
@Slf4j
public class BookController {

    @Resource
    BookService bookService;

    @PostMapping("/createBook")
    @ApiOperation("新增图书")
    public ApiResult createBook(@RequestBody CreateBookDTO createBookDTO) {

        if (createBookDTO == null) {
            log.warn("BookController createBook params is null!");
            return ApiResult.fail(ApiCode.PARAMETER_EXCEPTION.getMsg());
        }

        try {
            bookService.addBook(createBookDTO);
            return ApiResult.success();
        } catch (Exception e) {
            log.error("BookController createBoo fail, param:{}, exception:{}", createBookDTO, e);
            return ApiResult.fail(ApiCode.BOOK_INSERT_FAIL.getMsg());
        }
    }

    @PostMapping("/queryBook")
    @ApiOperation("查询图书")
    public ApiResult<BookVO> queryBook(@RequestParam Long bookId) {

        if (bookId == null) {
            log.warn("BookController queryBook params is null!");
            return ApiResult.fail(ApiCode.PARAMETER_EXCEPTION.getMsg());
        }

        try {
            BookVO bookVO = bookService.searchByBookId(bookId);
            return ApiResult.success(bookVO);
        } catch (Exception e) {
            log.error("BookController queryBook fail, param:{}, exception:{}", bookId, e);
            return ApiResult.fail(ApiCode.BOOK_SEARCH_FAIL.getMsg());
        }
    }

    @PostMapping("/batchQueryBooks")
    @ApiOperation("批量查询图书")
    public ApiResult<List<BookVO>> batchQueryBooks(@RequestParam List<Long> bookIds) {

        if (bookIds == null) {
            log.warn("BookController batchQueryBooks params is null!");
            return ApiResult.fail(ApiCode.PARAMETER_EXCEPTION.getMsg());
        }

        try {
            List<BookVO> bookVOs = bookService.searchByBookIds(bookIds);
            return ApiResult.success(bookVOs);
        } catch (Exception e) {
            log.error("BookController batchQueryBooks fail, param:{}, exception:{}", bookIds, e);
            return ApiResult.fail(ApiCode.BOOK_SEARCH_FAIL.getMsg());
        }
    }

    @PostMapping("/getAllBooks")
    @ApiOperation("查询所有图书")
    public ApiResult<List<BookVO>> getAllBooks() {

        try {
            List<BookVO> bookVOs = bookService.getAllBooks();
            return ApiResult.success(bookVOs);
        } catch (Exception e) {
            log.error("BookController getAllBooks fail, exception:{}", e);
            return ApiResult.fail(ApiCode.BOOK_SEARCH_FAIL.getMsg());
        }
    }

    @PostMapping("/fuzzQuery")
    @ApiOperation("根据名字模糊查询")
    public ApiResult<List<BookVO>> fuzzQuery(@RequestParam String name) {

        try {
            List<BookVO> bookVOs = bookService.searchByName(name);
            return ApiResult.success(bookVOs);
        } catch (Exception e) {
            log.error("BookController fuzzQuery fail, exception:{}", e);
            return ApiResult.fail(ApiCode.BOOK_SEARCH_FAIL.getMsg());
        }
    }

    @PostMapping("/deleteBook")
    @ApiOperation("根据Id删除图书")
    public ApiResult deleteBook(@RequestParam Long bookId) {

        try {
            bookService.deleteById(bookId);
            return ApiResult.success();
        } catch (Exception e) {
            log.error("BookController deleteBook fail, exception:{}", e);
            return ApiResult.fail(ApiCode.BOOK_DELETE_FAIL.getMsg());
        }
    }

    @PostMapping("/updateBook")
    @ApiOperation("更改图书信息")
    public ApiResult updateBook(@RequestBody UpdateBookDTO updateBookDTO) {

        try {
            bookService.updateByBook(updateBookDTO);
            return ApiResult.success();
        } catch (Exception e) {
            log.error("BookController updateBook fail, exception:{}", e);
            return ApiResult.fail(ApiCode.BOOK_UPDATE_FAIL.getMsg());
        }
    }


}
