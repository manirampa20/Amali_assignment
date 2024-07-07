package io.javaproject.springboot.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<EntityModel<Topic>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return topics.stream()
                .map(topic -> EntityModel.of(topic,
                        linkTo(methodOn(TopicController.class).getTopic(topic.getId())).withSelfRel(),
                        linkTo(methodOn(TopicController.class).getAllTopics()).withRel("topics")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EntityModel<Topic> getTopic(@PathVariable String id) {
        Optional<Topic> topic = topicService.getTopic(id);
        if (topic.isPresent()) {
            return EntityModel.of(topic.get(),
                    linkTo(methodOn(TopicController.class).getTopic(id)).withSelfRel(),
                    linkTo(methodOn(TopicController.class).getAllTopics()).withRel("topics"));
        } else {
            // Handle this appropriately in a real application
            return EntityModel.of(new Topic());
        }
    }

    @PostMapping
    public ResponseEntity<?> addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
        return ResponseEntity.created(linkTo(methodOn(TopicController.class).getTopic(topic.getId())).toUri()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@RequestBody Topic topic, @PathVariable String id) {
        topicService.updateTopic(id, topic);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable String id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sort/{algorithm}")
    public List<EntityModel<Topic>> sortTopics(@PathVariable String algorithm) {
        List<Topic> sortedTopics = topicService.sortTopics(algorithm);
        return sortedTopics.stream()
                .map(topic -> EntityModel.of(topic,
                        linkTo(methodOn(TopicController.class).getTopic(topic.getId())).withSelfRel(),
                        linkTo(methodOn(TopicController.class).getAllTopics()).withRel("topics")))
                .collect(Collectors.toList());
    }
}
