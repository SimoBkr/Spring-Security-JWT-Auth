package com.peaqock.aml.service.excel.impl;

import com.peaqock.aml.dto.ExcelResult;
import com.peaqock.aml.dto.ExcelSheet;
import com.peaqock.aml.service.excel.ExcelService;
import com.peaqock.aml.service.excel.OfficeValidatorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    private final OfficeValidatorService officeValidatorService;

    @Override
    public ExcelResult parse(MultipartFile file) throws IOException {
        if (!officeValidatorService.isValid(file))
            throw new RuntimeException("Excel cannot open the file because the file format or file extension is not valid");
        return parse(file.getInputStream());
    }

    @Override
    public ExcelResult parse(InputStream stream) throws IOException {
        var workbook = new XSSFWorkbook(stream);
        var excelResult = new ExcelResult();
        excelResult.setSheetSize(workbook.getNumberOfSheets());
        int totalRow = 0;
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            var excelSheet = new ExcelSheet();
            var sheet = workbook.getSheetAt(sheetIndex);
            excelSheet.setName(sheet.getSheetName());
            final var rowCount = sheet.getLastRowNum();
            excelSheet.setRowCount(rowCount);
            final var headerList = new ArrayList<String>();

            var firstRow = sheet.getRow(0);
            firstRow.forEach(
                    cell -> headerList.add(cell.getRichStringCellValue().getString().trim())
            );

            for (int i = 1; i <= rowCount; i++) {
                var row = sheet.getRow(i);
                if (row == null)
                    continue;
                var rowMap = new HashMap<String, Object>();
                var headerIndex = new AtomicInteger();
                row.forEach(cell -> {
                    rowMap.put(headerList.get(headerIndex.get()), getCellValue(cell));
                    headerIndex.getAndIncrement();
                });
                totalRow++;
                excelSheet.getContent().add(rowMap);
            }
            excelResult.getSheets().add(excelSheet);
        }
        excelResult.setTotalRow(totalRow + excelResult.getSheetSize());
        workbook.close();
        return excelResult;
    }


    @Override
    public byte[] toExcel(List<Map<String, Object>> values, String sheetName) throws Exception {
        if (CollectionUtils.isEmpty(values))
            throw new Exception("Data map can not be null or empty");
        var out = new ByteArrayOutputStream();
        var workbook = new XSSFWorkbook();

        var headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 18);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        var sheet = workbook.createSheet(sheetName);

        var headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerFont.setColor(IndexedColors.WHITE.index);
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        var headerRow = sheet.createRow(0);
        var headerSet = values.get(0).keySet();
        headerCellStyle.setFont(headerFont);

        var i = new AtomicInteger();
        headerSet.forEach(s -> {
            var cell = headerRow.createCell(i.get());
            cell.setCellValue(s);
            cell.setCellStyle(headerCellStyle);
            i.getAndIncrement();
        });

        var rowNum = new AtomicInteger();
        rowNum.set(1);
        values.forEach(stringObjectMap -> {
            var row = sheet.createRow(rowNum.getAndIncrement());
            var cellNum = new AtomicInteger(0);
            headerSet.forEach(s -> {
                Object value = stringObjectMap.get(s);
                setCellValue(row, cellNum, value);
            });
            cellNum.set(0);
        });

        for (int x = 0; x < headerSet.size(); x++) {
            sheet.autoSizeColumn(x);
        }

        workbook.write(out);
        workbook.close();
        return out.toByteArray();
    }

    private void setCellValue(Row row, AtomicInteger cellNum, Object value) {
        if (value instanceof Integer)
            row.createCell(cellNum.getAndIncrement())
                    .setCellValue((Integer) value);
        if (value instanceof Double)
            row.createCell(cellNum.getAndIncrement())
                    .setCellValue((Double) value);
        if (value instanceof String)
            row.createCell(cellNum.getAndIncrement())
                    .setCellValue((String) value);
        if (value instanceof Float)
            row.createCell(cellNum.getAndIncrement())
                    .setCellValue((Float) value);
        if (value instanceof Date)
            row.createCell(cellNum.getAndIncrement())
                    .setCellValue((Date) value);
    }


    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC: {
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            }
            case FORMULA:
                return cell.getCellFormula();
            default:
                return StringUtils.EMPTY;
        }
    }
}
