package com.example.task05.model;

import com.example.task05.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {

    private String title;
    private String body;
    private String sender;
    private String receiver;

}
