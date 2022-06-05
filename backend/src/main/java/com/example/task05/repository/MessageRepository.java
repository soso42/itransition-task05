package com.example.task05.repository;

import com.example.task05.entity.Message;
import com.example.task05.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByReceiver(User receiver);
}
