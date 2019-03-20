package com.xxin.chat.repository;

import com.xxin.chat.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    @Query("select content from Notice")
    public List<Notice> getNotice();
}
