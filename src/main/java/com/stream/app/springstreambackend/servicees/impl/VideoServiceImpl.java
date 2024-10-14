package com.stream.app.springstreambackend.servicees.impl;

import com.stream.app.springstreambackend.entities.Video;
import com.stream.app.springstreambackend.repositories.VideoRepository;
import com.stream.app.springstreambackend.servicees.VideoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    @Value("${files.video}")
    String DIR;

    @PostConstruct
    public void init() {
        File file = new File(DIR);
        if(!file.exists()){
            file.mkdir();
            System.out.println("Folder Created:");
        }
        else{
            System.out.println("Folder Exists Already");
        }
    }

    @Override
    public Video save(Video video, MultipartFile file) {

        try {

            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();

            String cleanFileName = StringUtils.cleanPath(filename);
            String cleanFolder = StringUtils.cleanPath(DIR);

            Path path = Paths.get(cleanFolder, cleanFileName);
            System.out.println(path);
            System.out.println(contentType);

            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            video.setContentType(contentType);
            video.setFilePath(path.toString());

            return videoRepository.save(video);

        }
        catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

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

}
