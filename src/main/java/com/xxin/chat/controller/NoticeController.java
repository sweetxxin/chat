package com.xxin.chat.controller;

import com.xxin.chat.entity.Notice;
import com.xxin.chat.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeController {
    @Autowired
    NoticeRepository noticeRepository;

    @GetMapping("/notice")
    public List<Notice> getNotice() {
        return noticeRepository.getNotice();
    }
}
