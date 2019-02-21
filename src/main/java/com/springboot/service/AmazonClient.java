package com.springboot.service;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class AmazonClient {
   
   private AmazonS3 s3client;

   @Value("${amazonProperties.endpointUrl}")
   private String endpointUrl;

   @Value("${amazonProperties.bucketName}")
   private String bucketName;

   @Value("${amazonProperties.accessKey}")
   private String accessKey;

   @Value("${amazonProperties.secretKey}")
   private String secretKey;

   @PostConstruct
   private void initializeAmazon() {
      AWSCredentials credentials = new BasicAWSCredentials(this.accessKey,this.secretKey);
      this.s3client=new AmazonS3Client(credentials);
   }

   private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void upoadFileTos3bucket(String fileName, File file) {
      System.out.println("Inside uploads3 bucket");
      s3client.putObject(new PutObjectRequest(bucketName,fileName,file));//.withCannedAcl(CannedAccessControlList.PublicRead));
      System.out.println("complete bucket work");
   }

   public String uploadFile(MultipartFile multipartFile)
   { String fileUrl="";
     try {
            System.out.println("Inside Upload");
            File file = convertMultiPartToFile(multipartFile);
            //System.out.println("After convert");
            String fileName = generateFileName(multipartFile);
            //System.out.println("file name generated");
            fileUrl = endpointUrl + "/" +bucketName + "/" + fileName;
            System.out.println("file URL generated");
            upoadFileTos3bucket(fileName,file);
            System.out.println("uploaded to S3 bucket");
            file.delete();
            System.out.println("Upload success");
         } catch(Exception e) {
          System.out.println("Inside excpetion");
            e.printStackTrace();
         }
      return fileUrl;
     }
}