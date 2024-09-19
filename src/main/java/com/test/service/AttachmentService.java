package com.test.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.Attachment;
import com.test.model.Card;
import com.test.repository.AttachmentRepository;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    public Attachment saveAttachment(MultipartFile file, Long cardId) throws IOException {
        Attachment attachment = new Attachment();
        attachment.setFilename(file.getOriginalFilename());
        attachment.setFiletype(file.getContentType());
        attachment.setFilesize(file.getSize());
        attachment.setCard(new Card());  // Set the card reference
        // Save file logic goes here
        return attachmentRepository.save(attachment);
    }
}
