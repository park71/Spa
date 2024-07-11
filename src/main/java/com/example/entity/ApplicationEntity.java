package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="application_table") //게시글 신청 DB
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity board;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "applied_at", nullable = false)
    private LocalDateTime appliedAt;

}
