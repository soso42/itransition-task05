package com.example.task05.service;

import com.example.task05.entity.Message;
import com.example.task05.entity.User;
import com.example.task05.model.MessageModel;
import com.example.task05.repository.MessageRepository;
import com.example.task05.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, UserServiceImpl userService) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void saveMessage(MessageModel messageModel) {

        User sender = userRepository.findUserByName(messageModel.getSender()).get();

        Optional<User> optReceiver = userRepository.findUserByName(messageModel.getReceiver());
        User receiver = optReceiver.orElseGet(() -> userService.saveUser(messageModel.getReceiver()));

        Message message= Message.builder()
                .sender(sender)
                .receiver(receiver)
                .title(messageModel.getTitle())
                .body(messageModel.getBody())
                .build();

        messageRepository.save(message);
    }

    @Override
    public List<Message> findAllMessagesByReceiver(String receiver) {
        Optional<User> optReceiver = userRepository.findUserByName(receiver);
        return optReceiver.isEmpty() ? new LinkedList<>() : messageRepository.findAllByReceiver(optReceiver.get());
    }

}
