package edu.imnc.javaweb.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.imnc.javaweb.springboot.entity.Employee;
import edu.imnc.javaweb.springboot.mapper.EmployeeMapper;
import edu.imnc.javaweb.springboot.utils.ResponseJSON;

@RestController
public class HelloController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/hello")
    public ResponseJSON hello() {
        List<Employee> list = employeeMapper.selectList(null);
        return ResponseJSON.ok(list);
    }
}
