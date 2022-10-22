package com.peaqock.aml.service.filemanager;

import com.peaqock.aml.dto.FileDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface FileManagerService {

    FileDto storeFile(MultipartFile file, String fileName);

    FileDto storeFile(byte[] file, String fileNmae);

    List<FileDto> storeMultiFiles(MultipartFile[] files);


    String getPreSignedDownloadUrl(String fileName);

    ByteArrayOutputStream downloadFile(String keyName);

    Resource loadFile(String fileKey);

    Resource loadFileFromDir(String fileId, String fileKey);

    Object deleteFile(String fileKey);
}
