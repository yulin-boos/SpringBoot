package edu.imnc.javaweb.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.imnc.javaweb.springboot.dto.RegisterDTO;
import edu.imnc.javaweb.springboot.entity.User;

public interface UserService extends IService<User> {
    int userRegister(RegisterDTO registerDTO);

    int userLogin(User user);

    int userEditPW(User user);
}
