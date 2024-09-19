package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.Attachment;
import com.test.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseEntity<Attachment> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("cardId") Long id) {
        try {
            Attachment attachment = attachmentService.saveAttachment(file, id);
            return new ResponseEntity<>(attachment, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}

