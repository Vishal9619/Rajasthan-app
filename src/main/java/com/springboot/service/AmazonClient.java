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

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    private void printExcel(MultipartFile readExcelDataFile) throws IOException
    {
    	XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
				    
		    for(int i=0;i<worksheet.getPhysicalNumberOfRows()-1 ;i++) {
		        XSSFRow row = worksheet.getRow(i);
		        System.out.println(row.getCell(0).getStringCellValue());
		        System.out.println(row.getCell(1).getStringCellValue());
		      }
    }
    private void upoadFileTos3bucket(String fileName, File file) {
      this.s3client.putObject(new PutObjectRequest(bucketName,fileName,file));//.withCannedAcl(CannedAccessControlList.PublicRead));
   }

   public String uploadFile(MultipartFile[] files)
   { String fileUrl="";
     try {  

          for(MultipartFile multipartFile : files)
             {
              File file = convertMultiPartToFile(multipartFile);
              String fileName = generateFileName(multipartFile);
              fileUrl = endpointUrl + "/" +bucketName + "/" + fileName;
              upoadFileTos3bucket(fileName,file);
              printExcel(multipartFile);
              file.delete();
            }
         } catch(Exception e) {
            e.printStackTrace();
         }
      return fileUrl;
     }
}