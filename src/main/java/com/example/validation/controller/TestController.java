package com.example.validation.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.validation.service.UserService;
import com.example.validation.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuans
 * @create 2019-09-16-14:53
 */
@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public JSONObject validateTest(@RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //遍历所有错误
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                logger.info("msg:{}", fieldError.getDefaultMessage());
            }

            //为了避免大量的校验在前端堆积,影响用户体验，只返回一个错误提示
            JSONObject errorMsg = new JSONObject();
            FieldError error = bindingResult.getFieldErrors().get(0);
            errorMsg.put("msg", error.getDefaultMessage());
            errorMsg.put("resultcode", 1);
            return errorMsg;
        } else {
            return userService.getUserMessage(user);
        }

    }
}
