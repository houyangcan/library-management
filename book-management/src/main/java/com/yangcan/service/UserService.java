package com.yangcan.service;

import com.yangcan.dto.RegisterUserDTO;
import com.yangcan.entity.user.User;
import com.yangcan.repository.UserRepository;
import com.yangcan.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserVO queryByUsername(String username) {

        User user = userRepository.findByUsernameAndValid(username, Boolean.TRUE);

        if (user == null) {
            log.warn("UserService queryByUsername result is null, username:{}", username);
            return null;
        }

        UserVO userVO = UserVO.genUserVO(user);

        return userVO;
    }

    public void register(RegisterUserDTO registerUserDTO) {
        User user = User.genUser(registerUserDTO);
        userRepository.save(user);
    }
}
