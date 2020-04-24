package com.etoak.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.etoak.bean.Email;
import com.etoak.bean.User;
import com.etoak.mapper.UserMapper;
import com.etoak.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    JmsTemplate jmsTemplate;
    @Override
    public int addUser(User user) {
        //加密
        String password = user.getPassword();
        password = DigestUtils.md5Hex(password);
        user.setPassword(password);
        //自增结果返回到user对象的id属性
        int addResult=userMapper.addUser(user);

        log.info("add  User.id -{},add-{}",user.getId(),addResult);
        //发送jms消息
        jmsTemplate.send("email",session -> {
            Email email=new Email();
            email.setSubject("用户激活邮件");
            email.setReceiver(user.getEmail());
            email.setContent("请点击激活 : http://localhost:8080/boot/user/active/"+user.getId());
            return session.createTextMessage(JSONObject.toJSONString(email));
        });
        return  addResult;
    }
}
