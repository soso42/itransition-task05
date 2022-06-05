package com.example.task05.controller;

import com.example.task05.model.MessageListModel;
import com.example.task05.model.MessageModel;
import com.example.task05.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageServiceImpl messageService;

    @Autowired
    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public ResponseEntity<String> saveMessage(@RequestBody MessageModel messageModel) {
        messageService.saveMessage(messageModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/messages")
    public ResponseEntity<MessageListModel> getMessageListByUser(@RequestParam String userName) {
        MessageListModel messages = new MessageListModel();
        messages.setMessages(messageService.findAllMessagesByReceiver(userName));
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
