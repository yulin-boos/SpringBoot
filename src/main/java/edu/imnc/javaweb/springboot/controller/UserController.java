package edu.imnc.javaweb.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.imnc.javaweb.springboot.dto.LoginDTO;
import edu.imnc.javaweb.springboot.dto.RegisterDTO;
import edu.imnc.javaweb.springboot.entity.User;
import edu.imnc.javaweb.springboot.service.UserService;
import edu.imnc.javaweb.springboot.utils.ResponseJSON;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/myaccount")
    public ResponseJSON myaccount(HttpSession session) {
        Integer userID = (Integer) session.getAttribute("userID");
        User user = userService.getById(userID);
        return ResponseJSON.ok(user);
    }

    @PutMapping("/users/myaccount")
    public ResponseJSON editPW(@ModelAttribute User user, HttpSession session) {
        Integer userID = (Integer) session.getAttribute("userID");
        user.setId(userID);
        int result = userService.userEditPW(user);
        if (result == 1) {
            ResponseJSON responseJSON = ResponseJSON.ok(null);
            responseJSON.setMessage("修改成功");
            return responseJSON;
        }
        ResponseJSON responseJSON = ResponseJSON.error();
        responseJSON.setMessage("修改失败");
        return responseJSON;
    }

    @GetMapping("/logout")
    public ResponseJSON logout(HttpSession session) {
        session.invalidate();
        ResponseJSON responseJSON = ResponseJSON.ok(null);
        responseJSON.setMessage("退出成功");
        return responseJSON;
    }

    @PostMapping("/login")
    public ResponseJSON login(@ModelAttribute LoginDTO loginDTO, HttpSession session) {
        ResponseJSON responseJSON;
        if (loginDTO.getUsername() == null || loginDTO.getUsername().trim().isEmpty()
                || loginDTO.getPassword() == null || loginDTO.getPassword().trim().isEmpty()
                || loginDTO.getCaptcha() == null || loginDTO.getCaptcha().trim().isEmpty()) {
            responseJSON = ResponseJSON.error();
            responseJSON.setMessage("用户名、密码或验证码为空");
            return responseJSON;
        }

        String captcha = (String) session.getAttribute("captcha");
        if (captcha == null || !captcha.equalsIgnoreCase(loginDTO.getCaptcha())) {
            responseJSON = ResponseJSON.error();
            responseJSON.setMessage("验证码错误");
            return responseJSON;
        }

        User user = new User();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        int result = userService.userLogin(user);

        if (result == 1) {
            session.setAttribute("userID", user.getId());
            responseJSON = ResponseJSON.ok(null);
            responseJSON.setMessage("登录成功");
        } else if (result == -1) {
            responseJSON = ResponseJSON.error();
            responseJSON.setMessage("用户名不存在");
        } else {
            responseJSON = ResponseJSON.error();
            responseJSON.setMessage("密码错误");
        }
        return responseJSON;
    }

    @PostMapping("/register")
    public ResponseJSON register(@ModelAttribute RegisterDTO registerDTO) {
        ResponseJSON responseJSON;
        if (registerDTO.getUsername() == null || registerDTO.getUsername().trim().isEmpty()
                || registerDTO.getPassword() == null || registerDTO.getPassword().trim().isEmpty()
                || registerDTO.getRepassword() == null || registerDTO.getRepassword().trim().isEmpty()) {
            responseJSON = ResponseJSON.error();
            responseJSON.setMessage("用户名或密码为空");
            return responseJSON;
        }

        if (!registerDTO.getPassword().equals(registerDTO.getRepassword())) {
            responseJSON = ResponseJSON.error();
            responseJSON.setMessage("两次密码不一致");
            return responseJSON;
        }

        int result = userService.userRegister(registerDTO);
        if (result == 1) {
            responseJSON = ResponseJSON.ok(null);
            responseJSON.setMessage("注册成功");
        } else if (result == -1) {
            responseJSON = ResponseJSON.error();
            responseJSON.setMessage("用户名已存在");
        } else {
            responseJSON = ResponseJSON.error();
            responseJSON.setMessage("注册异常");
        }
        return responseJSON;
    }
}
