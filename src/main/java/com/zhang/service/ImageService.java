package com.zhang.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by 致远 on 2017/6/13 0013.
 */
public class ImageService {

    public static void graphicsGeneration() throws Exception {
        // 实际数据行数+标题+备注
        int totalrow = 3;
        int totalcol = 20;
        int imageWidth = 2420;
        int imageHeight = totalrow * 40 + 40;
        int rowheight = 50;
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
        for (int i = 0; i < totalrow - 1; i++) {
            graphics.drawLine(startWidth, startHeight + (i + 1) * rowheight, imageWidth - 5,startHeight + (i + 1) * rowheight);
        }
        // 画竖线
        for (int i = 0; i < totalcol; i++) {
            graphics.drawLine(startWidth + i * colwidth, startHeight + rowheight, startWidth + i * colwidth,imageHeight - 50);
        }
        // 末列
        graphics.drawLine(imageWidth - 5, startHeight + rowheight, imageWidth - 5, imageHeight - 50);
        // 末行
        graphics.drawLine(startWidth, imageHeight - 90, imageWidth - 5, imageHeight - 90);

        // 设置字体
        Font font = new Font("华文楷体", Font.BOLD, 20);
        graphics.setFont(font);

        // 写标题
        String title = "这里是标题";
        graphics.drawString(title, imageWidth / 3 + startWidth, startHeight + rowheight - 10);

        font = new Font("华文楷体", Font.BOLD, 18);
        graphics.setFont(font);

        // 写入表头
        String[] headCells = { "部门", "姓名", "职位", "基本工资", "出勤天数", "实际基本工资", "上月提成", "团队提成", "考核", "社保", "公积金", "应纳税总额",
                "个税", "社保补贴", "其它补贴", "迟到|缺卡", "请假|扣款", "补上月工资", "未全勤", "工资总额" };
        for (int m = 0; m < headCells.length; m++) {
            graphics.drawString(headCells[m].toString(), startWidth + colwidth * m + 5,
                    startHeight + rowheight * 2 - 20);
        }

        // 设置字体
        font = new Font("华文楷体", Font.PLAIN, 16);
        graphics.setFont(font);
        String[] cellsValue = { "杭州", "张圣", "客服", "￥123", "55", "15", "￥123", "￥123", "￥123", "￥123", "￥123", "￥123",
                "￥123", "￥123", "￥123", "￥123", "￥123", "￥123", "￥123", "￥123", "￥123" };
        // 写入内容
        for (int n = 0; n < cellsValue.length; n++) {
            graphics.drawString(cellsValue[n], startWidth + colwidth * n + 5, startHeight + rowheight * 3 - 10);
        }

        font = new Font("华文楷体", Font.BOLD, 18);
        graphics.setFont(font);
        graphics.setColor(Color.RED);

        // 写备注
        String remark = "温馨提示：以上信息均为保密信息，员工之间不要互相打听、泄露，谢谢！。";
        graphics.drawString(remark, startWidth, imageHeight - 30);

        ImageIO.write(image, "PNG", new File("E:\\1.png"));
    }

    public static void main(String[] args) {
        try {
            graphicsGeneration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
