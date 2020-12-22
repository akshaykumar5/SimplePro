/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro.json.bean;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 *
 * @author akshay
 */
@Data
public class UploadFileResponse {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    @Expose(serialize = false, deserialize = false)
    private String logId;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, String logId) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.logId = logId;
    }
}
