package com.peaqock.aml.service.excel;

import com.peaqock.aml.dto.ExcelResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ExcelService {

    ExcelResult parse(MultipartFile file) throws IOException;

    ExcelResult parse(InputStream stream) throws IOException;

    byte[] toExcel(List<Map<String, Object>> values, String sheetName) throws Exception;
}
