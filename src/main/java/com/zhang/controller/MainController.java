package com.zhang.controller;

import com.zhang.entity.Person;
import com.zhang.service.ExcelService;
import com.zhang.service.ImageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by 致远 on 2017/6/14 0014.
 */
@RestController
@RequestMapping(value = "/")
public class MainController {

    @Resource
    private ExcelService excelService;
    @Resource
    private ImageService imageService;

    @RequestMapping(value = "persons")
    public List<Person> getPersons(String date) {
        try {
            return excelService.getPersons(date);
        }catch (FileNotFoundException e){
            System.err.println("文件不存在！");
            e.printStackTrace();
        }catch (IOException ex){
            System.err.println("IO异常");
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "images")
    public String getImages(String date){
        try {
           List<Person> persons = excelService.getPersons(date);
           return imageService.graphicsGeneration(persons.get(0));
        }catch (FileNotFoundException e){
            System.err.println("文件不存在！");
            e.printStackTrace();
        }catch (IOException ex){
            System.err.println("IO异常");
            ex.printStackTrace();
        }
        return "test";
    }
}
