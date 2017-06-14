package com.zhang.service;

import com.zhang.entity.Person;
import com.zhang.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 致远 on 2017/6/13 0013.
 */
@Service
public class ExcelService {

    public List<Person> getPersons(String date) throws FileNotFoundException,IOException{
        List<Person> persons = new ArrayList<>();
        File file = ResourceUtils.getFile("classpath:excel/" + date + ".xlsx");
        InputStream inputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 2; i < sheet.getLastRowNum() - 1; i++) {
            Row row = sheet.getRow(i);
            if (StringUtil.isEmpty(StringUtil.toStringNotNull(row))) {
                continue;
            }
            String email = getCellValue(row.getCell(20));
            if (StringUtil.isEmpty(email)) {
                continue;
            }
            Person person = new Person();
            person.setEmail(email);
            person.setName(getCellValue(row.getCell(2)));//姓名
            String part = getCellValue(row.getCell(0));//部门
            if (StringUtil.isEmpty(part)) {
                person.setPart("杭州");
            } else {
                person.setPart(part);
            }
            person.setPosition(getCellValue(row.getCell(1)));//职位
            person.setBaseSalary(getCellValue(row.getCell(3)));//基本工资
            person.setWorkDays(getCellValue(row.getCell(4)));//出勤天数
            person.setActualBaseSalary(getCellValue(row.getCell(5))); //实发基本工资
            person.setPreMonthBonus(getCellValue(row.getCell(6)));//上月提成
            person.setTeamBonus(getCellValue(row.getCell(7)));//团队提成
            person.setAssessment(getCellValue(row.getCell(8)));//考核
            person.setSocialInsurance(getCellValue(row.getCell(9)));//社保
            person.setHouseFund(getCellValue(row.getCell(10)));//公积金
            person.setTotalTaxSalary(getCellValue(row.getCell(11)));//应纳税工资总额
            person.setTax(getCellValue(row.getCell(12)));//个税
            person.setSocialInsuranceFund(getCellValue(row.getCell(13)));//社保补贴
            person.setOtherFund(getCellValue(row.getCell(14)));//其他补贴
            person.setLate(getCellValue(row.getCell(15)));//迟到/未打卡
            person.setLeave(getCellValue(row.getCell(16)));//请假/扣款
            person.setPreMonthFund(getCellValue(row.getCell(17)));//补上月工资
            person.setNotFullWork(getCellValue(row.getCell(18))); // 未全勤
            person.setSalary(getCellValue(row.getCell(19))); //工资总额
            persons.add(person);
        }
        return persons;
    }

    private String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            CellType type = cell.getCellTypeEnum();
            if (type.equals(CellType.FORMULA)) {
                try {
                    value = String.valueOf(cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    value = String.valueOf(cell.getRichStringCellValue());
                }
            } else if (type.equals(CellType.NUMERIC)) {
                value = String.valueOf(cell.getNumericCellValue());
            } else if (type.equals(CellType.STRING)) {
                value = String.valueOf(cell.getRichStringCellValue());
            }
        }
        return value;
    }
}
