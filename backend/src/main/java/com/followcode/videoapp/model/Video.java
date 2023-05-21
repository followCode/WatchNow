package com.followcode.videoapp.model;

import java.util.Set;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document(value = "Video")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
    private AtomicInteger likes = new AtomicInteger(0);
    private AtomicInteger disLikes = new AtomicInteger(0);
    private AtomicInteger viewCount = new AtomicInteger(0);
    private List<Comment> commentList = new CopyOnWriteArrayList<>();

    public void incrementLikes() {
        likes.incrementAndGet();
    }

    public void decrementLikes() {
        likes.decrementAndGet();
    }

    public void incrementDisLikes() {
        disLikes.incrementAndGet();
    }

    public void decrementDisLikes() {
        disLikes.decrementAndGet();
    }

    public void incrementViewCount() {
        viewCount.incrementAndGet();
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
    }
}