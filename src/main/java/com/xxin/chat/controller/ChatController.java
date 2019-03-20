package com.xxin.chat.controller;

import com.xxin.chat.util.ResponseResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.JstlView;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @PostMapping("/send")
    public ResponseResult sendTo(@RequestParam("message")String message){
        ResponseResult result = new ResponseResult();

        return result;
    }
    @GetMapping("/test")
    public ModelAndView test(){
        ModelAndView view = new ModelAndView("test");
        return view;
    }
}
