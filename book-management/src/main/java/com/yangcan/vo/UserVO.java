package com.yangcan.vo;

import com.yangcan.entity.user.User;
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
@ApiModel("用户详细信息")
public class UserVO {

    @ApiModelProperty("Id")
    private Long id;

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

    public static UserVO genUserVO(User user) {
        UserVO userVO = UserVO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .idNumber(user.getIdNumber())
                .password(user.getPassword())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .sex(user.getSex())
                .build();
        return userVO;
    }
}
