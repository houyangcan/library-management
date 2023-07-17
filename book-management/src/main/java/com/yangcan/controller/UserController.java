package com.yangcan.controller;

import com.yangcan.common.api.ApiCode;
import com.yangcan.common.api.ApiResult;
import com.yangcan.dto.RegisterUserDTO;
import com.yangcan.dto.LoginUserDTO;
import com.yangcan.service.UserService;
import com.yangcan.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Api(value = "用户登录控制接口", tags = {"用户登录控制接口"})
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    private final static String SESSION_KEY = "user";

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ApiResult<UserVO> login(HttpServletRequest request, @RequestBody LoginUserDTO userDto) {

        //1、密码加密
        String password = userDto.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2、校验信息
        UserVO userVO = userService.queryByUsername(userDto.getUsername());
        if (userVO == null) {
            return ApiResult.fail(ApiCode.USER_UNREGISTER.getCode(), ApiCode.USER_UNREGISTER.getMsg());
        }
        if (!userVO.getPassword().equals(password)) {
            return ApiResult.fail(ApiCode.LOGIN_EXCEPTION.getCode(), ApiCode.LOGIN_EXCEPTION.getMsg());
        }

        //3、将用户信息存入session
        request.getSession().setAttribute(SESSION_KEY, userVO.getId());
        return ApiResult.success(ApiCode.SUCCESS.getMsg(), userVO);
    }

    @PostMapping("/logout")
    @ApiOperation("用户退出")
    public ApiResult logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_KEY);
        return ApiResult.success(ApiCode.SUCCESS.getMsg());

    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ApiResult register(@RequestBody RegisterUserDTO registerUserDTO) {

        // 判断是否重复注册
        if (userService.queryByUsername(registerUserDTO.getUsername()) != null) {
            return ApiResult.fail(ApiCode.USER_ACCOUNT_REGISTERED.getCode(), ApiCode.USER_ACCOUNT_REGISTERED.getMsg());
        }
        registerUserDTO.setPassword(DigestUtils.md5DigestAsHex(registerUserDTO.getPassword().getBytes()));
        userService.register(registerUserDTO);
        return ApiResult.success(ApiCode.SUCCESS.getMsg());
    }
}
