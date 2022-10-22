package com.peaqock.aml.service.excel.impl;

import com.peaqock.aml.service.excel.OfficeValidatorService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class OfficeValidatorServiceImpl implements OfficeValidatorService {

    @Override
    public boolean isValid(MultipartFile uploadedImage) {
        if (!isOfficeContentType(uploadedImage.getContentType()))
            return false;

        return isValidOfficeFile(uploadedImage);
    }

    @Override
    public boolean isOfficeContentType(String contentType) {
        return isExcelFile(contentType);
    }

    private boolean isExcelFile(String contentType) {
        return contentType.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                || contentType.equalsIgnoreCase("application/vnd.ms-excel");
    }

    boolean isValidOfficeFile(MultipartFile multipartFile) {
        boolean result = true;
        try {
            if (isExcelFile(Objects.requireNonNull(multipartFile.getContentType())))
                new XSSFWorkbook(multipartFile.getInputStream());
        } catch (IOException e) {
            result = false;
        }
        return result;
    }
}
