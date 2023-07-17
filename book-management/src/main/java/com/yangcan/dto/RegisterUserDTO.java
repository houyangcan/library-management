package com.yangcan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户注册DTO")
public class RegisterUserDTO {

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("用户性别")
    private String sex;

    @ApiModelProperty("身份证")
    private String idNumber;

    @ApiModelProperty("邮箱")
    private String email;
}
