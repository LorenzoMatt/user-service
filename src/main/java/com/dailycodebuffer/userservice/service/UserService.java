package com.dailycodebuffer.userservice.service;

import com.dailycodebuffer.userservice.VO.Department;
import com.dailycodebuffer.userservice.VO.ResponseTemplateVO;
import com.dailycodebuffer.userservice.entity.User;
import com.dailycodebuffer.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("inside saveUser method of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("inside getUserWithDepartment method of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);
        Department department = restTemplate.getForObject("http://localhost:9001/departments/" + user.getDepartmentId(),
                Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }

}
