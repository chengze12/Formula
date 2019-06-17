package com.chengze.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;

@Service
public class LamStorageService {

    private AmazonS3 s3Client;

    private String bucket;

    public LamStorageService(@Autowired AmazonS3 s3){
        this.s3Client=s3;
    }
    public void uploadObject(String bucketName, File file){

        // Upload a file as a new object with ContentType and title specified.
        PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file);
        s3Client.putObject(request);
    }


    public String getObjectURL(String key){

        URL url= s3Client.getUrl(bucket, key);
        String geturl= url.toString();
        return geturl;
    }

    public S3Object getObject(String key) {
        if (key == null) {
            return null;
        }else{
            return getObject(key);
        }
    }
//
    public Object getObject(String bucket, String key){
        Object object= s3Client.getObject(bucket, key);
        return object;
    }
    //todo !!!!!
//    public void setS3Client(AmazonS3 amazonS3){
//            this.s3Client=amazonS3;
//    }
}
