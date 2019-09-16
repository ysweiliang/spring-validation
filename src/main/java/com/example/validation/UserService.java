package com.example.validation;

import com.alibaba.fastjson.JSONObject;
import com.example.validation.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author yuans
 * @create 2019-09-16-16:02
 */
@Service
public class UserService {

    public JSONObject getUserMessage(User user) {
        JSONObject userData = (JSONObject) JSONObject.toJSON(user);
        userData.put("resultcode", 0);
        return userData;
    }
}
