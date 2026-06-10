package com.duoc.inscripciones.service;

import com.duoc.inscripciones.model.Asset;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface AwsService {

    String getS3FileContent(String bucketName, String fileName) throws IOException;

    List<Asset> getS3Files(String bucketName)throws IOException;

    byte[] downloadFile(String bucketName, String fileName) throws IOException;

    void moveObject(String bucketName, String fileKey, String destinationFileKey);

    void deleteObject(String bucketName, String fileKey);

    String uploadFile(String bucketName, String filePath, MultipartFile file);

}
