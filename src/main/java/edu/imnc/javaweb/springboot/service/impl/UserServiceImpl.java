package edu.imnc.javaweb.springboot.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.imnc.javaweb.springboot.dto.RegisterDTO;
import edu.imnc.javaweb.springboot.entity.User;
import edu.imnc.javaweb.springboot.mapper.UserMapper;
import edu.imnc.javaweb.springboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public int userRegister(RegisterDTO registerDTO) {
        long count = query()
                .eq("username", registerDTO.getUsername())
                .count();
        if (count > 0) {
            return -1;
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(SecureUtil.md5(registerDTO.getPassword()));
        return save(user) ? 1 : 0;
    }

    @Override
    public int userLogin(User user) {
        User dbUser = query()
                .eq("username", user.getUsername())
                .one();
        if (dbUser == null) {
            return -1;
        }

        String password = SecureUtil.md5(user.getPassword());
        if (!dbUser.getPassword().equals(password)) {
            return -2;
        }

        user.setId(dbUser.getId());
        return 1;
    }

    @Override
    public int userEditPW(User user) {
        user.setPassword(SecureUtil.md5(user.getPassword()));
        return updateById(user) ? 1 : 0;
    }
}
