package com.example.admin;

import com.example.common.entity.role.RoleVO;
import com.example.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseServiceApplicationTests
{

    @Autowired
    RoleService roleService;

    @Test
    public void contextLoads()
    {
        RoleVO roleWithResources = roleService.getRoleWithResources("1");
        log.info("role info -> {}", roleWithResources);
    }

}
