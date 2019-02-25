package com.springboot.controller;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.springboot.service.AmazonClient;

@Controller
public class HandleController {

   private AmazonClient amazonClient;
   @Autowired
      HandleController(AmazonClient amazonClient) {
            this.amazonClient = amazonClient;
      }

   @RequestMapping("/")
   public String index() {
      return "index";
   }

   @RequestMapping(value="/upload",method=RequestMethod.POST)
   public String uploadExcelFiles(@RequestParam(value="fileName") MultipartFile[] files, Model model) throws IllegalStateException, IOException {
	         int sz=0;
	         //String saveDirectory = "/home/ubuntu/Desktop/";
	         List<String> fileNames = new ArrayList<String>();

	         this.amazonClient.uploadFile(files);
	         for(MultipartFile mp : files)
	             {   String filename=mp.getOriginalFilename();
		             //mp.transferTo(new File(saveDirectory + filename));
		             fileNames.add(filename);
		             ++sz;
	            }      
	         model.addAttribute("sz", sz);
	         model.addAttribute("files",fileNames);
	         return "uploadedFile";
      }

   }
