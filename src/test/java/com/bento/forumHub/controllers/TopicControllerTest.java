//package com.bento.forumHub.controllers;
//
//import com.bento.forumHub.domain.dtos.ReplyDto;
//import com.bento.forumHub.domain.dtos.TopicDto;
//import com.bento.forumHub.services.TopicService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(TopicController.class)
//class TopicControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    private TopicService topicService;
//
//    private static final LocalDateTime NOW = LocalDateTime.now();
//
//    @Test
//    void testGetAllTopics() throws Exception {
//        TopicDto topicDto = new TopicDto(1L, "Test Topic", "Test Description", NOW, null);
//        Page<TopicDto> mockPage = new PageImpl<>(List.of(topicDto));
//
//        when(topicService.getAllTopics(any(Pageable.class))).thenReturn(mockPage);
//
//        mockMvc.perform(get("/api/topics")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].id").value(1))
//                .andExpect(jsonPath("$.content[0].title").value("Test Topic"))
//                .andExpect(jsonPath("$.content[0].description").value("Test Description"));
//    }
//
//    @Test
//    void testGetTopic() throws Exception {
//        ReplyDto replyDto = new ReplyDto(1L, "Reply Content", NOW, 1l);
//        TopicDto topicDto = new TopicDto(1L, "Test Topic", "Test Description", NOW, List.of(replyDto));
//
//        when(topicService.getTopic(1L)).thenReturn(Optional.of(topicDto));
//
//        mockMvc.perform(get("/api/topics/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.title").value("Test Topic"))
//                .andExpect(jsonPath("$.description").value("Test Description"))
//                .andExpect(jsonPath("$.replies[0].id").value(1))
//                .andExpect(jsonPath("$.replies[0].content").value("Reply Content"));
//    }
//
//    @Test
//    void testGetTopicNotFound() throws Exception {
//        when(topicService.getTopic(1L)).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/api/topics/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testCreateTopic() throws Exception {
//        TopicDto inputTopic = new TopicDto(null, "New Topic", "New Description", null, null);
//        TopicDto createdTopic = new TopicDto(1L, "New Topic", "New Description", NOW, null);
//
//        when(topicService.createTopic(any(TopicDto.class))).thenReturn(createdTopic);
//
//        mockMvc.perform(post("/api/topics")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                    "title": "New Topic",
//                                    "description": "New Description"
//                                }
//                                """))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.title").value("New Topic"))
//                .andExpect(jsonPath("$.description").value("New Description"));
//    }
//
//    @Test
//    void testUpdateTopic() throws Exception {
//        TopicDto updatedTopic = new TopicDto(1L, "Updated Topic", "Updated Description", NOW, null);
//
//        when(topicService.updateTopic(eq(1L), any(TopicDto.class))).thenReturn(updatedTopic);
//
//        mockMvc.perform(put("/api/topics/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                    "title": "Updated Topic",
//                                    "description": "Updated Description"
//                                }
//                                """))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.title").value("Updated Topic"))
//                .andExpect(jsonPath("$.description").value("Updated Description"));
//    }
//
//    @Test
//    void testDeleteTopic() throws Exception {
//        doNothing().when(topicService).deleteTopic(1L);
//
//        mockMvc.perform(delete("/api/topics/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//}
