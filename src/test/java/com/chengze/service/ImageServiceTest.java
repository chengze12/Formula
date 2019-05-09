package com.chengze.service;

import com.chengze.config.AppConfig;
import com.chengze.domain.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes ={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("Unit")
public class ImageServiceTest {
    @Autowired
    private ImageService imageService;

    @Test
    @Transactional
    public void testFindAll(){
        Image im= new Image();
        im.setBack("back");
        im.setFront("front");
        im.setInterior("Interior");
//        c.setPrice("1000");
//        c.setModel("camry");
//        c.setYear("2011");
        imageService.save(im);

        List<Image> testImage = imageService.findAll();
        assertNotNull(testImage);
        assertEquals(im.getInterior(), testImage.get(0).getInterior());
    }
}
