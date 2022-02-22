package com.laioffer.onlineorder.controller;

import com.laioffer.onlineorder.entity.Customer;
import com.laioffer.onlineorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;



@Controller
public class SignUpController { //定义rest api，url
    //负责接收请求，交给logic tier

    @Autowired
    private CustomerService customerService;  // dependency injection

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED) //返回一个response
    public void signUp(@RequestBody Customer customer) {
        customerService.signUp(customer); // 接收到请求 返回给service

    }
}

