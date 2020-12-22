/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.service;

import com.avis.startit.simplepro.json.bean.UploadFileResponse;
import java.io.File;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

/**
 *
 * @author avistech
 */
@Log4j2
@Service
public class FileUploadService {

    @Value("${file.upload.path}")
    private String filePath;

    public UploadFileResponse storeMohFile(FilePart file, String musicFileName, String logId, String domainName) throws Exception {
        log.info("filePath: {}", filePath);
        String completeDirPath = filePath + musicFileName + "/";
        return storeFile(file, completeDirPath, file.filename(), logId);
    }
//

//    public UploadFileResponse storeAmdMsgFile(FilePart file, String fileName, String logId, String domainName) throws Exception {
//        String completeDirPath = filePath + Path.AMD_FOLDER.getValue() + "/";
//        return storeFile(file, completeDirPath, fileName, logId);
//    }
//    public UploadFileResponse storeDropCallFile(FilePart file, String campaignName, String queueName, String logId, String domainName) throws Exception {
//        Domain domain = domainService.findByName(domainName);
//        Campaign campaign = campaignService.findByName(campaignName, domain.getId());
//        String completeDirPath = getSoundFilePath() + Path.DROP_CALL_FOLDER.getValue() + campaign.getId() + "_" + domain.getId() + "/" + queueName + "/";
//        return storeFile(file, completeDirPath, file.filename(), logId);
//    }
    public UploadFileResponse storeFile(FilePart file, String directory, String fileName, String logId) throws Exception {
        String type = "";
        MediaType mediaType = file.headers().getContentType();
        if (mediaType != null) {
            type = mediaType.getType();
            log.debug("type: {}", type);
        }

        File directoryPath = new File(directory);
        if (!directoryPath.exists()) {
            log.info("Creating Directory: {}", directory);
            directoryPath.mkdirs();
        }

        final String completeFilePath = directory + fileName;
        log.debug("completeFilePath: {}", completeFilePath);
        File uploadFile = new File(completeFilePath);
        try {
            if (!uploadFile.exists()) {
                log.info("Creating File: {}", uploadFile.getAbsolutePath());
                uploadFile.createNewFile();
            }
            file.transferTo(uploadFile);
        } catch (IOException e) {
            log.error("Error ", e);
            throw new Exception("error in saving file on server");
        }

        return new UploadFileResponse(fileName, "", type, file.headers().getContentLength(), logId);
    }

//    public void deleteFiles(JsonArray musicFileNames, Long domainId) {
//        for (int i = 0; i <= musicFileNames.size(); i++) {
//            String logId = UUID.randomUUID().toString().toUpperCase();
//            String musicFileName = musicFileNames.get(i).getAsString();
//            deleteFile(musicFileName, logId, domainId);
//        }
//    }
//    public void deleteFile(String musicFileName, String logId, Long domainId) {
//        final String completeDirPath = getSoundFilePath() + "moh/" + musicFileName + "_" + domainId;
//        File directoryPath = new File(completeDirPath);
//        log.debug("completeDirPath: {}", completeDirPath);
//
//        deleteFile(directoryPath);
//    }
//    private void deleteFile(File file) {
//        //to end the recursive loop
//        if (!file.exists()) {
//            return;
//        }
//
//        //if directory, go inside and call recursively
//        if (file.isDirectory()) {
//            for (File f : file.listFiles()) {
//                //call recursively
//                deleteFile(f);
//            }
//        }
//        //call delete to delete files and empty directory
//        file.delete();
//        log.debug("Deleted file/folder:  {}", file.getAbsolutePath());
//    }
//    private String getSoundFilePath() {
//        String soundFilePath = "/var/lib/asterisk/";
//        SystemParameter systemParameter = systemParameterRepository.findByName("SOUNDS_FILE_PATH");
//        if (systemParameter != null && systemParameter.getValue() != null && !systemParameter.getValue().trim().isEmpty()) {
//            soundFilePath = systemParameter.getValue();
//            if (!soundFilePath.endsWith("/")) {
//                soundFilePath += "/";
//            }
//        } else {
//            log.warn("system parameter for SOUNDS_FILE_PATH not defined so use default");
//        }
//
//        return soundFilePath;
//    }
}
