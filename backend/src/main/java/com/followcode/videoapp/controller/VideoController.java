package com.followcode.videoapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.followcode.videoapp.dto.UploadVideoResponse;
import com.followcode.videoapp.dto.VideoDto;
import com.followcode.videoapp.service.VideoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@CrossOrigin
@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {
	
	private final VideoService videoService;
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public UploadVideoResponse uploadVideo(@RequestParam("file") MultipartFile file) {
		return videoService.uploadVideo(file);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto) {
		return videoService.editVideo(videoDto);
	}
	
	@PostMapping("/thumbnail")
	@ResponseStatus(HttpStatus.CREATED)
	public String uploadThumbnail(@RequestParam("file") MultipartFile file, 
									@RequestParam("videoId") String videoId) {
		return videoService.uploadThumbnail(file, videoId);
	}

}
