package com.zhang.service;

import com.zhang.entity.Person;
import com.zhang.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 致远 on 2017/6/13 0013.
 */
@Service
public class ImageService {

    public String graphicsGeneration(Person person) throws FileNotFoundException,IOException {
        // 实际数据行数+标题+备注
        int totalrow = 3;
        int totalcol = 20;
        int imageWidth = 2420;
        int imageHeight = totalrow * 40 + 40;
        int rowheight = 40;
        int startHeight = 10;
        int startWidth = 10;
        int colwidth = ((imageWidth - 20) / totalcol);

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(new Color(220, 240, 240));

        // 画横线
        graphics.setColor(Color.black);
        for (int i = 1; i <= totalrow; i++) {
            graphics.drawLine(startWidth, startHeight + i * rowheight, imageWidth - startHeight, startHeight + i * rowheight);
        }
        // 画竖线
        for (int i = 0; i < totalcol; i++) {
            graphics.drawLine(startWidth + i * colwidth, startHeight + rowheight, startWidth + i * colwidth, imageHeight - 30);
        }
        // 末列
        graphics.drawLine(imageWidth - startWidth, startHeight + rowheight, imageWidth - startWidth, imageHeight - 30);
        // 末行
        //graphics.drawLine(startWidth, imageHeight - 90, imageWidth - 5, imageHeight - 90);

        // 设置字体
        Font font = new Font("华文楷体", Font.BOLD, 20);
        graphics.setFont(font);
        // 写标题
        String title = "杭州某月"+person.getName()+"工资条";
        graphics.drawString(title, imageWidth / 3 + startWidth, startHeight + rowheight - 10);

        font = new Font("华文楷体", Font.BOLD, 18);
        graphics.setFont(font);
        // 写入表头
        String[] headCells = {"部门", "姓名", "职位", "基本工资", "出勤天数", "实际基本工资", "上月提成", "团队提成", "考核", "社保", "公积金", "应纳税总额",
                "个税", "社保补贴", "其它补贴", "迟到|缺卡", "请假|扣款", "补上月工资", "未全勤", "工资总额"};
        for (int i = 0; i < headCells.length; i++) {
            graphics.drawString(headCells[i].toString(), startWidth + colwidth * i + 5, startHeight + rowheight * 2 - 10);
        }
        // 设置字体
        font = new Font("华文楷体", Font.PLAIN, 16);
        graphics.setFont(font);
        java.util.List<String> cellsValue = new ArrayList<>();
        cellsValue.add(person.getPart());
        cellsValue.add(person.getName());
        cellsValue.add(person.getPosition());
        cellsValue.add(person.getBaseSalary());
        cellsValue.add(person.getWorkDays());
        cellsValue.add(person.getActualBaseSalary());
        cellsValue.add(person.getPreMonthBonus());
        cellsValue.add(person.getTeamBonus());
        cellsValue.add(person.getAssessment());
        cellsValue.add(person.getSocialInsurance());
        cellsValue.add(person.getHouseFund());
        cellsValue.add(person.getTotalTaxSalary());
        cellsValue.add(person.getTax());
        cellsValue.add(person.getSocialInsuranceFund());
        cellsValue.add(person.getOtherFund());
        cellsValue.add(person.getLate());
        cellsValue.add(person.getLeave());
        cellsValue.add(person.getPreMonthFund());
        cellsValue.add(person.getNotFullWork());
        cellsValue.add(person.getSalary());
        // 写入内容
        for (int i = 0; i < cellsValue.size(); i++) {
            graphics.drawString(cellsValue.get(i), startWidth + colwidth * i + 5, startHeight + rowheight * 3 - 10);
        }

        font = new Font("华文楷体", Font.BOLD, 18);
        graphics.setFont(font);
        graphics.setColor(Color.RED);
        // 写备注
        String remark = "温馨提示：以上信息均为保密信息，员工之间不要互相打听、泄露，谢谢！。";
        graphics.drawString(remark, startWidth, imageHeight - 10);
        File file = ResourceUtils.getFile("classpath:static/");
        System.out.println(file.getAbsolutePath());
        ImageIO.write(image, "PNG", new File(file.getAbsolutePath()+"/"+person.getName()+".png"));
        return "http://localhost:8080/"+person.getName()+".png";
    }
}
