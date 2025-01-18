package com.bento.forumHub.controllers;

import com.bento.forumHub.domain.entities.TopicEntity;
import com.bento.forumHub.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<TopicEntity> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicEntity> getTopic(@PathVariable Long id) {
        Optional<TopicEntity> topic = topicService.getTopic(id);
        return topic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TopicEntity createTopic(@RequestBody TopicEntity topic) {
        return topicService.createTopic(topic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicEntity> updateTopic(@PathVariable Long id, @RequestBody TopicEntity topicDetails) {
        TopicEntity updatedTopic = topicService.updateTopic(id, topicDetails);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}

