package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.Student;
import com.demo.service.StudentService;
import com.github.pagehelper.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    public String getAll() {
        return JSONObject.toJSONString(studentService.selectAll());
    }

    @PostMapping("/addStudent")
    public void addStudent(Student student) {
        studentService.insert(student);
    }

    @PostMapping("/getAllById/{id}")
    public Student getAllById(@PathVariable Long id) {
        return studentService.selectByPrimaryKey(id);
    }

    @PostMapping("/check/{userNumber}/{password}")
    public String checkByNameAndPassword(Model model, @PathVariable Long userNumber, @PathVariable String password) {
        Student student = studentService.checkUser(userNumber, password);
        System.out.println("登陆");
        if (student != null) {
            model.addAttribute("student", student);
            return "index";
        }
        return "error";
    }

    @PostMapping("/updateById")
    @ResponseBody
    public int updateById(Student student) {
        System.out.println(student.getStudentPassword());
        return studentService.updateByPrimaryKey(student);
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/import")
    public String importInfo(@RequestParam(value = "filename") MultipartFile file, Model model) throws Exception {
        String message = "";
        String fName = file.getOriginalFilename().trim();
        String fileName = fName.substring(fName.lastIndexOf("\\") + 1);
        System.out.println(fileName);
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            message = "上传文件不符合标准格式";
            model.addAttribute("message", message);
            return "error";
        }
        FileInputStream is = (FileInputStream) file.getInputStream();
        Workbook workbook = null;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            workbook = new XSSFWorkbook(is);
        } else {
            workbook = new HSSFWorkbook(is);
        }
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        int celNum = row.getPhysicalNumberOfCells();
        String[] title = {"学号", "密码", "姓名", "性别", "学生身份证", "学院", "系", "专业", "手机号码", "入学时间", "毕业状态"};
        Cell cell = null;
        for (int i = 0; i < celNum; i++) {
            String cellTitle = getCellFormatValue(row.getCell(i));
            System.out.print(cellTitle + "|");
            if (StringUtil.isEmpty(cellTitle) || cellTitle.equals("")) {
                message = "标题title为空";
                model.addAttribute("message", message);
                return "error";
            }
            if (!cellTitle.equals(title[i])) {
                message = "标题与导入规范不相符" + "原因在：第" + i + "列，标题内容为：" + title[i] + " 单元格内容为：" + cellTitle;
                model.addAttribute("message", message);
                return "error";
            }
        }
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < 11; j++) {
                getCellFormatValue(row.getCell(j));
            }
        }
        return "succeed";
    }

    private String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        cellvalue = sdf.format(date);
                        System.out.println(cellvalue + "：数字+时间");
                    } else {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cellvalue = cell.toString();
                        System.out.println(cellvalue + "：数字");
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellvalue = cell.getRichStringCellValue().getString();
                    System.out.println(cellvalue + ":字符串");
                    break;
                case Cell.CELL_TYPE_ERROR:
                    System.out.println("数据类型错误");
                default:
                    cellvalue = "未知类型";
                    break;
            }

        } else {
            return "";
        }
        return cellvalue;
    }
}


