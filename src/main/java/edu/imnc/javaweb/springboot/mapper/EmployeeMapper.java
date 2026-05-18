package edu.imnc.javaweb.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import edu.imnc.javaweb.springboot.entity.Employee;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
