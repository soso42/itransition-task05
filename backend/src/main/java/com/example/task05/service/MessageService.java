package com.example.task05.service;

import com.example.task05.entity.Message;
import com.example.task05.model.MessageModel;

import java.util.List;


public interface MessageService {
    void saveMessage(MessageModel messageModel);
    List<Message> findAllMessagesByReceiver(String receiver);
}
