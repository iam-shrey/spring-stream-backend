package com.stream.app.springstreambackend.servicees.impl;

import com.stream.app.springstreambackend.entities.Video;
import com.stream.app.springstreambackend.servicees.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public Video get(String videoId) {
        return null;
    }

    @Override
    public Video getByTitle(String title) {
        return null;
    }

    @Override
    public List<Video> getAll() {
        return List.of();
    }

    @Override
    public Video save(Video video, MultipartFile file) {
        return null;
    }
}
