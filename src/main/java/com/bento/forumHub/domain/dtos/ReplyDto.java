package com.bento.forumHub.domain.dtos;

import com.bento.forumHub.domain.entities.ReplyEntity;

import java.time.LocalDateTime;

public record ReplyDto(Long id, String content, LocalDateTime createdAt, Long topicId) {

    public static ReplyDto fromEntity(ReplyEntity entity) {
        return new ReplyDto(
                entity.getId(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getTopic() != null ? entity.getTopic().getId() : null
        );
    }

    public ReplyEntity toEntity() {
        ReplyEntity reply = new ReplyEntity();
        reply.setId(id);
        reply.setContent(content);
        reply.setCreatedAt(createdAt);
        return reply;
    }
}
