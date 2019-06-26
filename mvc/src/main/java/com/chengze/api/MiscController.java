package com.chengze.api;

import com.chengze.domain.Image;
import com.chengze.service.ImageService;
import com.chengze.service.MessageSQSService;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value= {"/api/misc" })
public class MiscController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MessageSQSService messageSQSService;

    @Autowired
    ImageService imageService;

    //http://localhost:8080/api/misc/picture
    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public Map<String, String > uploadPicture(@RequestParam(value = "pic")MultipartFile picture){
        Map<String, String> result = new HashMap<>(1);
        try{
            Image image = imageService.saveFakeImage(picture);
            result.put("s3_url", image.getUrl());
        }catch (ServiceException e){
            logger.error("error on saving recod", e);
        }
        return result;
    }

//    //http://localhost:8080/api/misc/picture
//    @RequestMapping(value = "/picture", method = RequestMethod.POST)
//    public Map<String, String > uploadPicture(@RequestParam(value = "pic")MultipartFile picture){
//        logger.debug(picture.getOriginalFilename());
//        return new HashMap<>();
//    }

    //http://localhost:8080/api/misc/email
    @RequestMapping(value = "/email", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendEmail(@RequestParam(value = "userId") Long userid){
        messageSQSService.sendMessage(String.valueOf(userid),5);
    }
}
