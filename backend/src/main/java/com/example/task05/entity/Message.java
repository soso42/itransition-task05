package com.example.task05.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "title")
    private String title;

    @Column(name = "body", length = 10000)
    private String body;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User receiver;
}
