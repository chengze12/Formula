package com.chengze.service;

import com.chengze.domain.Image;
import com.chengze.repository.ImageRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.amazonaws.services.sagemaker.model.TrainingInputMode.File;

@Service
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

    public Image findById(Long id){
        return imageRepository.findById(id).get();
    }


//    public Image saveFakeImage(MultipartFile multipartFile)throws ServiceException{
//        if(multipartFile==null || multipartFile.isEmpty()) throw new ServiceException("File must not be null!");
//        String extension = FilenmaeUtils.
//                String homeDir=
//                File localFile= new File(homeDir)
//            try{
//                multipartFile.transferTo(localFile);
//            }
//
//    }

}
