package com.zhang.service;

import com.zhang.entity.Person;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 致远 on 2017/6/13 0013.
 */
@Service
public class ExcelService {

    /*
    public static void main(String[] args) {
        try {
            File file = ResourceUtils.getFile("classpath:excel/201705.xlsx");
            System.out.println(file.getAbsolutePath());
            InputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    System.out.println(cell.getStringCellValue());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    */
    public List<Person> getPersons(String date){
        List<Person> persons = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile("classpath:excel/"+date+".xlsx");
            System.out.println(file.getAbsolutePath());
            InputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    System.out.println(cell.getStringCellValue());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return persons;
    }
}
