package com.peaqock.aml.service.excel;

import org.springframework.web.multipart.MultipartFile;

public interface OfficeValidatorService {

    boolean isValid(MultipartFile uploadedImage);

    boolean isOfficeContentType(String contentType);
}
