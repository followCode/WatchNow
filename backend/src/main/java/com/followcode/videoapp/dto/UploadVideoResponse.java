package com.followcode.videoapp.dto;

import java.util.Set;

import com.followcode.videoapp.model.VideoStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadVideoResponse {
	private String videoId;
	private String videoUrl;
	
}
