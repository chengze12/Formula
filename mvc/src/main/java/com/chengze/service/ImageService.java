package com.chengze.service;

import com.amazonaws.services.s3.model.S3Object;
import com.chengze.domain.Image;
import com.chengze.repository.ImageRepository;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class ImageService {

    @Autowired
    public ImageRepository imageRepository;

    @Autowired
    private LamStorageService lamStorageService;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public Image save(Image image){
        return imageRepository.save(image);
    }

    public List<Image> findAll(){
        logger.info("total number of array is:" + imageRepository.findAll().size());
        return imageRepository.findAll();
    }

    public Image findById(Long id){
        return imageRepository.findById(id).get();
    }


    public Image saveFakeImage(MultipartFile multipartFile) throws ServiceException{
        if(multipartFile==null || multipartFile.isEmpty()) throw new ServiceException("File must not be null!");
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                String homeDir=System.getProperty("") !=null ? System.getProperty("") : "/tmp/";
                File localFile= new File(homeDir+ multipartFile.getOriginalFilename());
            try{
                multipartFile.transferTo(localFile);
                Image image= new Image();
                String s3key= image.getUuid()+ "." +extension;
                lamStorageService.uploadObject(s3key, localFile);
                S3Object s3Object= lamStorageService.getObject(s3key);
                image.seturl(lamStorageService.getObjectURL(s3key));
                image.setExtention(extension);
                return image;
            }catch(IOException io) {
                logger.warn("can't find image file");
            }
            return null;
        }

    }


