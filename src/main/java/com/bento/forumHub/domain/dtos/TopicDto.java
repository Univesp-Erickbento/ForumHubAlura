package com.bento.forumHub.domain.dtos;
import com.bento.forumHub.domain.entities.ReplyEntity;
import com.bento.forumHub.domain.entities.TopicEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record TopicDto(Long id, String title, String description, LocalDateTime createdAt, List<ReplyDto> replies) {

    public static TopicDto fromEntity(TopicEntity entity) {
        List<ReplyDto> replyDtos = entity.getReplies() != null ?
                entity.getReplies().stream().map(ReplyDto::fromEntity).collect(Collectors.toList()) : null;
        return new TopicDto(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getCreatedAt(), replyDtos);
    }

    public TopicEntity toEntity() {
        TopicEntity entity = new TopicEntity();
        entity.setId(id);
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setCreatedAt(createdAt);
        return entity;
    }
}