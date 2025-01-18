package com.bento.forumHub.repositories;

import com.bento.forumHub.domain.entities.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
}
