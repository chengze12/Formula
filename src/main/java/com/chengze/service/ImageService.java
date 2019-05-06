package com.chengze.service;

import com.chengze.domain.Image;
import com.chengze.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImageService {

    @Autowired
    public ImageRepository imageRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public Image save(Image image){
        return imageRepository.save(image);
    }

    public List<Image> findAll(){
        logger.info("total number of array is:" + imageRepository.findAll().size());
        return imageRepository.findAll();
    }
}
