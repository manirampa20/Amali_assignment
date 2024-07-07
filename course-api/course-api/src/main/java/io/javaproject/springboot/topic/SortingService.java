
package io.javaproject.springboot.topic;

import java.util.*;

public class SortingService {

    public List<Topic> heapSort(List<Topic> topics) {
        // Implementation of Heap Sort
        PriorityQueue<Topic> heap = new PriorityQueue<>(Comparator.comparing(Topic::getName));
        heap.addAll(topics);
        List<Topic> sortedTopics = new ArrayList<>();
        while (!heap.isEmpty()) {
            sortedTopics.add(heap.poll());
        }
        return sortedTopics;
    }

    public List<Topic> quickSort(List<Topic> topics) {
        // Implementation of Quick Sort
        if (topics.size() <= 1) {
            return topics;
        }
        Topic pivot = topics.get(topics.size() / 2);
        List<Topic> lesser = new ArrayList<>();
        List<Topic> equal = new ArrayList<>();
        List<Topic> greater = new ArrayList<>();
        for (Topic topic : topics) {
            int compare = topic.getName().compareTo(pivot.getName());
            if (compare < 0) {
                lesser.add(topic);
            } else if (compare > 0) {
                greater.add(topic);
            } else {
                equal.add(topic);
            }
        }
        lesser = quickSort(lesser);
        greater = quickSort(greater);
        List<Topic> sortedTopics = new ArrayList<>(lesser);
        sortedTopics.addAll(equal);
        sortedTopics.addAll(greater);
        return sortedTopics;
    }

    public List<Topic> mergeSort(List<Topic> topics) {
        // Implementation of Merge Sort
        if (topics.size() <= 1) {
            return topics;
        }
        int mid = topics.size() / 2;
        List<Topic> left = mergeSort(topics.subList(0, mid));
        List<Topic> right = mergeSort(topics.subList(mid, topics.size()));
        return merge(left, right);
    }

    private List<Topic> merge(List<Topic> left, List<Topic> right) {
        List<Topic> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getName().compareTo(right.get(j).getName()) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));
        return result;
    }

    public List<Topic> radixSort(List<Topic> topics) {
        // Implementation of Radix Sort
        int maxLength = topics.stream().mapToInt(topic -> topic.getName().length()).max().orElse(0);
        List<Topic> sortedTopics = new ArrayList<>(topics);
        for (int exp = maxLength - 1; exp >= 0; exp--) {
            sortedTopics = countingSortByCharacter(sortedTopics, exp);
        }
        return sortedTopics;
    }

    private List<Topic> countingSortByCharacter(List<Topic> topics, int exp) {
        int[] count = new int[257];
        for (Topic topic : topics) {
            int charIndex = exp < topic.getName().length() ? topic.getName().charAt(exp) : 0;
            count[charIndex + 1]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        List<Topic> sortedTopics = new ArrayList<>(Collections.nCopies(topics.size(), null));
        for (Topic topic : topics) {
            int charIndex = exp < topic.getName().length() ? topic.getName().charAt(exp) : 0;
            sortedTopics.set(count[charIndex]++, topic);
        }
        return sortedTopics;
    }

    public List<Topic> bucketSort(List<Topic> topics) {
        // Implementation of Bucket Sort
        int bucketCount = (int) Math.sqrt(topics.size());
        List<List<Topic>> buckets = new ArrayList<>(Collections.nCopies(bucketCount, null));
        for (int i = 0; i < bucketCount; i++) {
            buckets.set(i, new ArrayList<>());
        }
        for (Topic topic : topics) {
            int bucketIndex = topic.getName().length() % bucketCount;
            buckets.get(bucketIndex).add(topic);
        }
        List<Topic> sortedTopics = new ArrayList<>();
        for (List<Topic> bucket : buckets) {
            sortedTopics.addAll(quickSort(bucket));
        }
        return sortedTopics;
    }
}
