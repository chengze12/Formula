package com.chengze.service;

import com.chengze.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
@WebAppConfiguration
@ContextConfiguration(classes ={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class LamStorageServiceTest {
    @Autowired
    private LamStorageService lamStorageService;
    @Test
    public void uploadObjectTest(){
        File testFile = new File("/Users/wangchengze/.m2/settings.xml");
        lamStorageService.uploadObject("formula-dev", testFile);
    }


}
