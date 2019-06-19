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
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@WebAppConfiguration
@ContextConfiguration(classes ={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class StorageServiceTest {
    @Autowired
    private StorageService storageService;
    @Autowired
    private AmazonS3 s3FakeClient;
    @Test
    public void uploadObjectTest(){
        File testFile = new File("laowang.png");
        storageService.uploadObject("formula-dev", testFile);
        Mockito.verify(s3FakeClient, Mockito.times(1)).putObject(any());
    }

    @Test
    public void getObjectUrlTest(){

        String key="fakekey";
        Mockito.when(s3FakeClient.getUrl(anyString(), anyString())).thenReturn(Mockito.mock(URL.class));
        storageService.getObjectURL( key);
        Mockito.verify(s3FakeClient, Mockito.times(1)).getUrl(anyString(),anyString());
    }
}
