package com.peaqock.aml.service.filemanager;


import com.peaqock.aml.config.FileManagerProp;
import com.peaqock.aml.dto.FileDto;
import com.peaqock.aml.exception.FileManagerException;
import com.peaqock.aml.exception.FunctionalErrorCode;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Component
@Service
public class FileManagerServiceImpl implements FileManagerService {

    private final static List<String> allowedMimeTypes = Arrays.asList(
            "image/", "video/", "text/csv",
            "application/vnd.", "application/json",
            "application/pdf", "application/msword"
    );

    private final Path rootLocation;

    @Autowired
    public FileManagerServiceImpl(FileManagerProp props) throws IOException {
        super();
        this.rootLocation = props.getFileStore().getFile().toPath();
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new FileManagerException("Could not initialize storage");
        }
    }

    @Override
    public FileDto storeFile(MultipartFile file, String fileName) {
        try {
            String fileType = file.getContentType();
            filterAllowedFile(Collections.singletonList(fileType));
            //String fileName = file.getOriginalFilename();
            final String fileKey = generateFileKey(fileName);
            Path targetLocation = rootLocation.resolve(fileKey);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return FileDto.builder()
                    .fileKey(fileKey)
                    .message("File stored successfully")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new FileManagerException(FunctionalErrorCode.INVALID_FILE.getMessageTemplate());
        }
        return FileDto.builder()
                .message("File not stored successfully")
                .build();
    }

    @Override
    public FileDto storeFile(byte[] file, String fileName) {
        try {
           /* String fileType = file.getContentType();
            filterAllowedFile(Collections.singletonList(fileType));
*/
            //String fileName = file.getOriginalFilename();
            final String fileKey = generateFileKey(fileName);

            Path targetLocation = rootLocation.resolve(fileKey);

            Files.copy(new ByteArrayInputStream(file), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return FileDto.builder()
                    .fileKey(fileKey)
                    .message("File stored successfully")
                    .build();
        } catch (Exception e) {
            throw new FileManagerException(FunctionalErrorCode.INVALID_FILE.getMessageTemplate());
        }
    }

    @Override
    public List<FileDto> storeMultiFiles(MultipartFile[] files) {
        try {
            final List<MultipartFile> multipartFiles = new ArrayList<>(Arrays.asList(files));
            filterAllowedFile(multipartFiles.stream().map(MultipartFile::getContentType)
                    .collect(Collectors.toList()));
            return multipartFiles.stream().map((MultipartFile file) -> storeFile(file, file.getOriginalFilename())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new FileManagerException(FunctionalErrorCode.INVALID_FILE.getMessageTemplate());
        }
    }

    @Override
    public String getPreSignedDownloadUrl(String fileName) {
        return "";
    }


    /**
     * Downloads file using amazon S3 client from S3 bucket
     *
     * @param keyName
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream downloadFile(String keyName) {
        System.out.println("------------------------------");
        return null;
    }

    @Override
    public Resource loadFile(String fileKey) {
        Path filePath = rootLocation.resolve(fileKey).normalize();
        System.out.println("file Path : " + filePath);
        System.out.println("fileKey Key : " + fileKey);

        return buildResource(filePath, fileKey);
    }

    @Override
    public Resource loadFileFromDir(String fileId, String fileKey) {
        Path file = rootLocation.resolve(fileId + "/" + fileKey);
        return buildResource(file, fileKey);
    }

    @Override
    public Object deleteFile(String fileKey) {
        try {
            final Path filePath = rootLocation.resolve(fileKey).normalize();
            Files.delete(filePath);
            Map<String, String> myMap = new HashMap<String, String>() {{
                put("status", "200");
                put("message", "File deleted successfully");
            }};
            return myMap;
        } catch (Exception e) {
            throw new FileManagerException(FunctionalErrorCode.INVALID_FILE.getMessageTemplate());
        }
    }

    private Resource buildResource(Path filePath, String fileKey) {
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable())
                return resource;
            else
                throw new FileManagerException("Could not read file: " + fileKey);
        } catch (MalformedURLException e) {
            throw new FileManagerException("Could not read file: " + fileKey);
        }
    }

    private String generateFileKey(String fileName) {
        final String fileExtension = FilenameUtils.getExtension(fileName);
        fileName = fileName.substring(0, fileName.lastIndexOf('.')).replaceAll("[^a-zA-Z0-9]+", "-").toLowerCase();
        return String.format("file-%s.%s", fileName, fileExtension);
    }

    private void filterAllowedFile(List<String> fileTypes) {
        fileTypes.forEach(fileType -> {
            Boolean isFileAllowed = false;
            for (String allowedMimeType : allowedMimeTypes) {
                if (fileType.startsWith(allowedMimeType)) {
                    isFileAllowed = true;
                    break;
                }
            }
            if (!isFileAllowed) throw new FileManagerException(FunctionalErrorCode.INVALID_FILE.getMessageTemplate());
        });
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
