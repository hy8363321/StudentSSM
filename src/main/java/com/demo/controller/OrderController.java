package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.pojo.Order;
import com.demo.service.OrderSerivce;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderSerivce orderSerivce;


    @GetMapping("/getAll")
    public String getAll() {
        List<Order> list = orderSerivce.selectAll();
        System.out.println(list);
        System.out.println("-----------------------------------");
        String order = JSONObject.toJSONString(list);
        System.out.println(order);
        return order;
    }

    @PostMapping("/addOrder")
    public void addOrder(@RequestBody Order order) {
        orderSerivce.insert(order);
    }

    @GetMapping("/getAllById/{id}")
    public String getAllById(@PathVariable Long id) {
        String order = JSONObject.toJSONString(orderSerivce.selectByPrimaryKey(id));
        return order;
    }

    @GetMapping("/deleteById/{id}")
    public int deleteById(@PathVariable Long id) {
        return orderSerivce.deleteByPrimaryKey(id);
    }

    @RequestMapping("/excel")
    public String excel(){
        return "excel";
    }
    @RequestMapping("/export")
    @ResponseBody
    public String export(@RequestParam String fileName) {
        String PATH = "D://workbook/";
        List<Order> list = orderSerivce.selectAll();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生信息表");
        Row row = sheet.createRow(0);
        row.setHeight((short) (22.50 * 20));
        String[] str = {
                "序号", "审批单号", "社团名", "社团规模", "大学", "地区", "社团类型", "社团注册人", "社团负责人", "社团指导老师",
                "注册人手机号码", "负责人手机号码", "注册人学号", "负责人学号", "注册时间", "申请时间", "文档提交状态", "注册状态"};
        for (int i = 0; i < str.length; i++) {
            row.createCell(i).setCellValue(str[i]);
        }
        if (list == null) {
            return "error";
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(list.get(i).getId());
            row.createCell(1).setCellValue(list.get(i).getApproverNumber());
            row.createCell(2).setCellValue(list.get(i).getClubName());
            //转成String类型
            String clubScale = clubScale(list.get(i).getClubScale());
            row.createCell(3).setCellValue(clubScale);
            row.createCell(4).setCellValue(list.get(i).getCollege());
            row.createCell(5).setCellValue(list.get(i).getArea());
            row.createCell(6).setCellValue(list.get(i).getClubTypes());
            row.createCell(7).setCellValue(list.get(i).getClubRegistrant());
            row.createCell(8).setCellValue(list.get(i).getClubPrincipal());
            row.createCell(9).setCellValue(list.get(i).getClubAdviser());
            row.createCell(10).setCellValue(list.get(i).getRegistrantTelephone());
            row.createCell(11).setCellValue(list.get(i).getPrincipalTelephone());
            row.createCell(12).setCellValue(list.get(i).getStudentId().toString());
            row.createCell(13).setCellValue(list.get(i).getPrincipalNumber().toString());
            row.createCell(14).setCellValue(formatDate(list.get(i).getRegistrantTime()));
            row.createCell(15).setCellValue(formatDate(list.get(i).getApplicationTime()));
            row.createCell(16).setCellValue(documentStatus(list.get(i).getDocumentStatus()));
            row.createCell(17).setCellValue(registerStatus(list.get(i).getRegisterStatus()));
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        for (int i = 0; i < list.size() + 1; i++) {
            sheet.autoSizeColumn(i);
        }
        try {
            FileOutputStream file = new FileOutputStream(PATH + fileName+".xls");
            workbook.write(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "succeed";
    }

    /*
    时间类型转Sring
     */
    public String formatDate(Date time){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return date.format(time);
    }

    /*
    社团规模转String
     */
    public String clubScale(int number) {
        switch (number) {
            case 0:
                return "4-50人";
            case 1:
                return "50-100人";
            case 2:
                return "100-200人";
            case 3:
                return "200人以上";
            default:
                return "";
        }

    }

    /*
    状态转String
     */
    public String documentStatus(int num) {
        switch (num) {
            case 0:
                return "提交";
            case 1:
                return "未提交";
            default:
                return "";
        }

    }

    /*
      状态转String
       */
    public String registerStatus(int num) {
        switch (num) {
            case 0:
                return "审核中";
            case 1:
                return "通过";
            case 2:
                return "未通过";
            default:
                return "";
        }
    }
}
