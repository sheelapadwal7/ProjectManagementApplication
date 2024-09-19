package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
