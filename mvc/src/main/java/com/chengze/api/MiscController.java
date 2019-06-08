package com.chengze.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class MiscController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public Map<String, String > uploadPicture(@RequestParam(value = "pic")MultipartFile picture){
        logger.debug(picture.getOriginalFilename());
        return new HashMap<>();
    }
}
