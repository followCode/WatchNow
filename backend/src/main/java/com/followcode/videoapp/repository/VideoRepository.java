package com.followcode.videoapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.followcode.videoapp.model.Video;

public interface VideoRepository extends MongoRepository<Video, String> {
}