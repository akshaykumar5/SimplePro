/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.controller;

import com.avis.startit.simplepro.json.bean.TenantContext;
import com.avis.startit.simplepro.json.bean.UploadFileResponse;
import com.avis.startit.simplepro.service.FileUploadService;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javax.activation.MimetypesFileTypeMap;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 *
 * @author avistech
 */
@Log4j2
@CrossOrigin
@RestController
@RequestMapping(path = "/api")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(path = "/lead/upload/long", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Object> fileUploadWithNumber(@RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "file", required = true) MultipartFile file) {

        return Mono.just(new UploadFileResponse("", "", "", id, ""));
    }

    @PostMapping(path = "/lead/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Object> uploadLeadUpload(@RequestParam("file") FilePart file, @RequestParam("id") Long id) throws Exception {
        String logId = UUID.randomUUID().toString().toUpperCase();
        String domainName = TenantContext.getCurrentTenant();
        log.info("id: {} and domainName:{}", id, domainName);
//        fileUploadService.validate(file, id, domainName);

        UploadFileResponse uploadFileResponse = fileUploadService.storeFile(file, "/tmp/", "testing", logId);
        return Mono.just(uploadFileResponse);
    }

    @GetMapping(path = "/lead/download/{id}")
    public ResponseEntity<Resource> downloadLeadFile(@RequestHeader(value = AUTHORIZATION, required = true) String authString, @PathVariable Long id) throws Exception {
        if (authString == null) {
            log.error("Api Key can't be null or blank : {}", id);
            throw new Exception("Unauthorised request : " + authString);
        }

        if (id == null) {
            log.error("File id can't be null or blank : {}", id);
            throw new Exception("Lead File id can't be null or blank : " + id);
        }

        File file = new File("/home/akshay/Desktop/photo_2020-04-06_00-27-17.jpg");
        if (file.exists()) {
            log.info("File found");
            //get the mimetype
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            String mimeType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM).getType();
            log.info("MediaTypeFactoryMime : {}", mimeType);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(resource);
        } else {
            log.info("File not found");
            throw new Exception("File not found");
        }

    }

}
