package com.miracle.adminservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InquiryType inquiryType;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String questionContent;

    private Long userId;
    private Long companyId;
    private Long postId;
    private LocalDateTime answerDate;

    @Column(columnDefinition = "TEXT")
    private String answerContent;

    private boolean answerStatus;
}