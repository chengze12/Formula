package com.chengze.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;

@Service
public class LamStorageService {

    private String clientRegion="us-east-1";
    public void uploadObject(String bucketName, File file){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        // Upload a file as a new object with ContentType and title specified.
        PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file);
        s3Client.putObject(request);
    }

    private String bucket;

    public String getObjectURL(String bucket, String key){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new ProfileCredentialsProvider())
                .build();
        String url= s3Client.getUrl(bucket, key).toString();
        return url;
    }

    public Object getObject(String key) {
        if (key == null) {
            return null;
        }else{
            return getObject(bucket,key);
        }
    }

    public Object getObject(String bucket, String key){

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new ProfileCredentialsProvider())
                .build();
        Object object= s3Client.getObject(bucket, key);
        return object;
    }
}
