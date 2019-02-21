package com.springboot.controller;

import com.springboot.service.AmazonClient;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

//import java.xml.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


//@Controller


@Controller
// @RequestMapping("/")
public class HandleController {
   private AmazonClient amazonClient;
   @Autowired
      HandleController(AmazonClient amazonClient) {
            this.amazonClient = amazonClient;
           System.out.println("HandleController constructer");
      }

   @RequestMapping("/index")
   public String index() {
      System.out.println("Inside index");
      return "index";
   }

   
   
   

   
   
  // System.out.println("out of index");
   @RequestMapping(value="/hello",method=RequestMethod.POST)
   public String sayHello(@RequestParam(value="fileName") MultipartFile files, Model model) throws IllegalStateException, IOException {

         System.out.println("Inside /hello1");
         int sz=2;//files.size();
         String saveDirectory = "/home/ubuntu/Desktop/";
         List<String> fileNames = new ArrayList<String>();
         fileNames.add("fer");
         System.out.println("Inside /hello2");
         // for(MultipartFile multipartFile : files) 
         //   {
         
             //HandleController h = new HandleController(amazonClient);
             this.amazonClient.uploadFile(files);//multipartFile);
             System.out.println("uploaded to S3");
             // String filename=multipartFile.getOriginalFilename();
             // multipartFile.transferTo(new File(saveDirectory + filename));
             // fileNames.add(filename);
            // }
            System.out.println("Inside /hello3");
         model.addAttribute("sz", sz);
         model.addAttribute("files",fileNames);
   	
         return "hello";
      }

   }
