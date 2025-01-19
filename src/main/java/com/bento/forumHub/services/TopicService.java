package com.bento.forumHub.services;

import com.bento.forumHub.domain.entities.TopicEntity;
import com.bento.forumHub.domain.dtos.TopicDto;
import com.bento.forumHub.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Page<TopicDto> getAllTopics(Pageable pageable) {
        // Retorna uma página de TopicDto convertida a partir das entidades
        return topicRepository.findAll(pageable).map(TopicDto::fromEntity);
    }

    public Optional<TopicDto> getTopic(Long id) {
        return topicRepository.findById(id).map(TopicDto::fromEntity);
    }

    public TopicDto createTopic(TopicDto topicDto) {
        TopicEntity topicEntity = topicDto.toEntity();
        return TopicDto.fromEntity(topicRepository.save(topicEntity));
    }

    public TopicDto updateTopic(Long id, TopicDto topicDto) {
        TopicEntity topicEntity = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));
        topicEntity.setTitle(topicDto.title());
        topicEntity.setDescription(topicDto.description());
        return TopicDto.fromEntity(topicRepository.save(topicEntity));
    }

    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
}
