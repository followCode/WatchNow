package com.followcode.videoapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.followcode.videoapp.dto.UploadVideoResponse;
import com.followcode.videoapp.dto.VideoDto;
import com.followcode.videoapp.model.Video;
import com.followcode.videoapp.repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoService {
	
	private final S3Service s3Service;
	private final VideoRepository videoRepository;
	
	public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
		String videoUrl = s3Service.uploadFile(multipartFile);
		
		var video = new Video();
		video.setVideoUrl(videoUrl);
		
		var savedVideo = videoRepository.save(video);
		
		return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
		
	}
	
	private Video getVideoById(String videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> 
                	new IllegalArgumentException("Cannot find video with id : " + videoId));
    }

	public VideoDto editVideo(VideoDto videoDto) {
		var savedVideo = this.getVideoById(videoDto.getId());
        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setTags(videoDto.getTags());
        savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        savedVideo.setVideoStatus(videoDto.getVideoStatus());

        videoRepository.save(savedVideo);
        return videoDto;
	}

	public String uploadThumbnail(MultipartFile file, String videoId) {
		var savedVideo = this.getVideoById(videoId);
		
		String thumbnailUrl = s3Service.uploadFile(file);
		
		savedVideo.setThumbnailUrl(thumbnailUrl);
		
		videoRepository.save(savedVideo);
		
		return thumbnailUrl;
		
	}
	
	
}
