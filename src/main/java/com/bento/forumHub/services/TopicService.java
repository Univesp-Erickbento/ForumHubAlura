package com.bento.forumHub.services;

import com.bento.forumHub.domain.entities.TopicEntity;
import com.bento.forumHub.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<TopicEntity> getAllTopics() {
        return topicRepository.findAll();
    }

    public Optional<TopicEntity> getTopic(Long id) {
        return topicRepository.findById(id);
    }

    public TopicEntity createTopic(TopicEntity topic) {
        return topicRepository.save(topic);
    }

    public TopicEntity updateTopic(Long id, TopicEntity topicDetails) {
        TopicEntity topic = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));
        topic.setTitle(topicDetails.getTitle());
        topic.setDescription(topicDetails.getDescription());
        return topicRepository.save(topic);
    }

    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
}

