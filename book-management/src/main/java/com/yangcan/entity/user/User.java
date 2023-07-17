package com.yangcan.entity.user;

import com.yangcan.dto.RegisterUserDTO;
import com.yangcan.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_base", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;

    private String email;

    private Date creatTime;

    private Date updateTime;

    private Boolean valid;

    public static User genUser(RegisterUserDTO registerUserDTO) {
        User user = User.builder()
                .email(registerUserDTO.getEmail())
                .idNumber(registerUserDTO.getIdNumber())
                .password(registerUserDTO.getPassword())
                .username(registerUserDTO.getUsername())
                .email(registerUserDTO.getEmail())
                .phone(registerUserDTO.getPhone())
                .sex(registerUserDTO.getSex())
                .valid(Boolean.TRUE)
                .build();
        return user;
    }

}