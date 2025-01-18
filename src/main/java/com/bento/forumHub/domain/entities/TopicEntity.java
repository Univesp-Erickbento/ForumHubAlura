package com.bento.forumHub.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Topic")
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<ReplyEntity> replies;

    // Construtores
    public TopicEntity() {}

    public TopicEntity(Long id, String title, String description, LocalDateTime createdAt, List<ReplyEntity> replies) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.replies = replies;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ReplyEntity> getReplies() {
        return replies;
    }

    public void setReplies(List<ReplyEntity> replies) {
        this.replies = replies;
    }
}
