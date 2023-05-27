package org.omsoft.controller;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.omsoft.constant.AppConstant;
import org.omsoft.entity.FileStorage;
import org.omsoft.entity.User;
import org.omsoft.service.FileService;
import org.omsoft.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.time.LocalDateTime;

@Controller
public class FileOptionController {

    @Autowired
    FileService fileService;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/upload")
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
        ModelAndView model = new ModelAndView("welcome");

        if (!file.isEmpty()) {
            try {
                // Save the file to disk
                String filename = file.getOriginalFilename();
                //file.transferTo(new File(filename));
                FileStorage file1 = new FileStorage();
                Session session = entityManager.unwrap(Session.class);
                file1.setFileContent(file.getBytes());
                file1.setFileName(file.getOriginalFilename());
                file1.setFileSize(file.getSize());
                file1.setFileType(file.getContentType());
                file1.setCreatedOn(LocalDateTime.now());
                file1.setModifiedOn(LocalDateTime.now());
                fileService.save(file1);
                model.addObject("message", "File uploaded successfully!");
            } catch (IOException e) {
                model.addObject("error_message", "Error while storing file into db, Error ="+e.getMessage());
            }
        } else {
            model.addObject("message", "No file received!");

        }
        return model;
    }
}
