package com.bento.forumHub.repositories;

import com.bento.forumHub.domain.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
}
