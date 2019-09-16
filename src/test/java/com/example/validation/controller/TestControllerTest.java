package com.example.validation.controller;

import com.alibaba.fastjson.JSON;
import com.example.validation.ValidationApplication;
import com.example.validation.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ValidationApplication.class)
@WebAppConfiguration
public class TestControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        //单个类,项目拦截器无效
//      mvc = MockMvcBuilders.standaloneSetup(new ProductController()).build();
        //项目拦截器有效
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void validateTest() throws  Exception {
        User param = new User();
        param.setName("");
        param.setAge(16);
        param.setMobile("15251805999");
        param.setEmail("15251805999@163.com");
        //调用接口，传入添加的用户参数
        RequestBuilder request = MockMvcRequestBuilders.post("/test")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(param));

        MvcResult mvcResult = mockMvc.perform(request).andReturn() ;
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
    }
}