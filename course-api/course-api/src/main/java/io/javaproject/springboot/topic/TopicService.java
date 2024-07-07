package io.javaproject.springboot.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    private SortingService sortingService = new SortingService();

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopic(String id) {
        return topicRepository.findById(id);
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void updateTopic(String id, Topic topic) {
        topicRepository.save(topic);
    }

    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }

    public List<Topic> sortTopics(String algorithm) {
        List<Topic> topics = getAllTopics();
        switch (algorithm.toLowerCase()) {
            case "heap":
                return sortingService.heapSort(topics);
            case "quick":
                return sortingService.quickSort(topics);
            case "merge":
                return sortingService.mergeSort(topics);
            case "radix":
                return sortingService.radixSort(topics);
            case "bucket":
                return sortingService.bucketSort(topics);
            default:
                throw new IllegalArgumentException("Unknown sorting algorithm: " + algorithm);
        }
    }
}
