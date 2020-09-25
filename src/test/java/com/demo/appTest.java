package com.demo;

import com.demo.service.TestService;
import com.demo.util.FtpUtil;
import com.demo.util.RedisUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class appTest {

    @Autowired
    private TestService testService;

    @Autowired
    private RedisUtil redis;
    private static String PATH = "D://workbook/";

    @Test
    public void test() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public void ExcelWriteTest() throws Exception {
//        1.创建工作簿
        Workbook workbook = new HSSFWorkbook();
//        2.创建工作表
        Sheet sheet = workbook.createSheet("人员");
//        3.创建一行
        Row row = sheet.createRow(0);
//        4.创建一个单元格
        Cell cell = row.createCell(0);
//        5.写入数据
        cell.setCellValue("唐三");
//        6.生成一张表（IO）流
        FileOutputStream file = new FileOutputStream(PATH + "唐三.xls");
//        7.输出
        workbook.write(file);
//        8.关闭
        file.close();
        System.out.println("生成完成");
    }

    @Test
    public void ExcelWrite65536() throws Exception {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("over");
        FileOutputStream outputStream = new FileOutputStream(PATH + "65536.xls");
        workbook.write(outputStream);
        outputStream.close();
        System.out.println("生成完成");
    }

    @Test
    public void ExcelReport() throws Exception {
        new FileOutputStream("D://workbook/唐三.xls");
    }

    @Test
    public void importTest() {
        com.demo.pojo.Test test1 = new com.demo.pojo.Test("qwer", "root", new Date());
        com.demo.pojo.Test test2 = new com.demo.pojo.Test("asdf", "sadf", new Date());
        com.demo.pojo.Test test3 = new com.demo.pojo.Test("zxcv", "adfgdaf", new Date());
        List<com.demo.pojo.Test> list = new ArrayList<com.demo.pojo.Test>();
        list.add(test1);
        list.add(test2);
        list.add(test3);
        testService.importTest(list);
    }

    @Autowired
    public FtpUtil ftpUtil;

    @Test
    public void ftpTest() {
        ftpUtil.loginFtp();
    }

    @Test
    public void contextRedis() {
        redis.set("name","tangweimin");
        System.out.println(redis.get("name"));
    }
}
