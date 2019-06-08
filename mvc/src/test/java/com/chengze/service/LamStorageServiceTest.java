package com.chengze.service;

import com.amazonaws.services.s3.AmazonS3;
import com.chengze.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;

@WebAppConfiguration
@ContextConfiguration(classes ={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class LamStorageServiceTest {
    @Autowired
    private LamStorageService lamStorageService;
    @Autowired
    private AmazonS3 s3Client;
    @Test
    public void uploadObjectTest(){
//        AmazonS3 s3Fake= Mockito.mock(AmazonS3.class);///memory0
        File testFile = new File("laowang.png");
        lamStorageService.uploadObject("formula-dev", testFile);
        Mockito.verify(s3Client, Mockito.times(1)).putObject(any());
    }

    @Test
    public void getObjectUrlTest(){
        String key="fakekey";
        lamStorageService.getObjectURL("formula-dev", key);
        Mockito.verify(s3Client, Mockito.times(1)).putObject(any());
    }
}
