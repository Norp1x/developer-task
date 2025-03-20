package com.search.service.controller;

import com.search.service.entity.TxtFile;
import com.search.service.repository.TxtFileRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/txt")
public class TxtDownloadController {

    private final TxtFileRepository repository;

    public TxtDownloadController(TxtFileRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String id) {
        TxtFile txtFile = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("File not found"));

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + txtFile.getFileName() + "\"")
            .body(txtFile.getFileContent().getData());
    }
}
