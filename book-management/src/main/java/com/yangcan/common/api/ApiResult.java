package com.yangcan.common.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -6510022886064903596L;

    private int code;

    private String msg;

    private boolean success;

    private T data;

    /**
     * Success api result.
     *
     * @param <T> the type parameter
     * @return the api result
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<T>(ApiCode.SUCCESS.getCode(), ApiCode.SUCCESS.getMsg(), Boolean.TRUE, null);
    }

    /**
     * Success api result.
     *
     * @param <T> the type parameter
     * @param message the message
     * @param data the data
     * @return the api result
     */
    public static <T> ApiResult<T> success(String message, T data) {
        return new ApiResult<T>(ApiCode.SUCCESS.getCode(), message, Boolean.TRUE, data);
    }

    /**
     * Success api result.
     *
     * @param <T> the type parameter
     * @param data the data
     * @return the api result
     */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<T>(ApiCode.SUCCESS.getCode(), ApiCode.SUCCESS.getMsg(), Boolean.TRUE, data);
    }
    /**
     * Success api result.
     *
     * @param <T> the type parameter
     * @param message the message
     * @return the api result
     */
    public static <T> ApiResult<T> success(String message) {
        return new ApiResult<T>(ApiCode.SUCCESS.getCode(), message, Boolean.TRUE, null);
    }

    /**
     * Fault api result.
     *
     * @param <T> the type parameter
     * @param message the message
     * @param data the data
     * @return the api result
     */
    public static <T> ApiResult<T> fail(String message, T data) {
        return new ApiResult<T>(ApiCode.FAIL.getCode(), message, Boolean.FALSE, data);
    }

    /**
     * Fault api result.
     *
     * @param message the message
     * @return the api result
     */
    public static <T> ApiResult<T> fail(String message) {
        return new ApiResult<T>(ApiCode.FAIL.getCode(), message, Boolean.FALSE,  null);
    }

    /**
     * Fault api result.
     *
     * @param code the code
     * @param message the message
     * @return the api result
     */
    public static <T> ApiResult<T> fail(int code, String message) {
        return new ApiResult<T>(code, message, Boolean.FALSE,  null);
    }

    /**
     * Instance api result.
     *
     * @param <T> the type parameter
     * @return the api result
     */
    public static <T> ApiResult<T> instance() {
        return new ApiResult<>();
    }
}
