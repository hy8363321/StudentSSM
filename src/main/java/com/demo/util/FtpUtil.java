package com.demo.util;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
public class FtpUtil {

    @Value("${ftp.host}")
    public String host;

    @Value("${ftp.username}")
    public String username;

    @Value("${ftp.password}")
    public String password;

    @Value("${ftp.port}")
    public int port;

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private static final Logger LOG = LoggerFactory.getLogger(FtpUtil.class);


    public FTPClient loginFtp() {

        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            // 设置缓存大小
            ftpClient.setBufferSize(1024 * 1024 * 4);
            // 设置连接超时时间
            ftpClient.setConnectTimeout(1000 * 30);
            //连接Ftp
            ftpClient.connect(host, port);
            // 登陆Ftp
            ftpClient.login(username, password);
            //设置编码格式
            ftpClient.setControlEncoding("UTF-8");
            //设置文件类型为二进制（如果从FTP下载或上传的文件是压缩文件的时候，不进行该设置可能会导致获取的压缩文件解压失败）
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 开启被动模式，否则文件上传不成功，也不报错
            ftpClient.enterLocalPassiveMode();
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                LOG.info("连接FTP失败，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                LOG.info("连接成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("登陆FTP失败，请检查FTP相关配置信息是否正确！" + e);
            return null;
        }
        return ftpClient;
    }

    /**
     * 从FTP下载文件到本地
     *
     * @param ftpClient     已经登陆成功的FTPClient
     * @param fileName      FTP上的目标文件路径+文件名称
     * @param localFilePath 下载到本地的文件路径
     * @param servicePath   服务器的上面文件的上层路径
     */
    public static String dwonFile(FTPClient ftpClient, String servicePath, String fileName, String localFilePath) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            ftpClient.enterLocalPassiveMode();
            //解决FTP乱码问题：由于FTP用的是ISO-8859-1，需要转好了才行
            String path = new String(servicePath.getBytes("GBK"), FTP.DEFAULT_CONTROL_ENCODING);
            String name = new String(fileName.getBytes("GBK"), FTP.DEFAULT_CONTROL_ENCODING);
            is = ftpClient.retrieveFileStream(path + name);
            fos = new FileOutputStream(new File(localFilePath + fileName));
            int i;
            byte[] bytes = new byte[1024];
            while ((i = is.read(bytes)) != -1) {
                fos.write(bytes, 0, i);
            }
            ftpClient.completePendingCommand();
            LOG.info("FTP文件下载成功");
        } catch (IOException e) {
            e.printStackTrace();
            LOG.info("FTP文件下载失败" + e);
        } finally {
            try {
                if (fos != null) fos.close();
                if (is != null) is.close();
            } catch (IOException e) {
                LOG.error("下载流关闭失败" + e);
            }

        }
        return localFilePath + fileName;
    }


    /**
     * 从FTP下载文件到本地
     *
     * @param ftpClient     已经登陆成功的FTPClient
     * @param fileName      FTP上的目标文件路径+文件名称
     * @param localFilePath 下载到本地的文件路径
     * @param servicePath   服务器的上面文件的上层路径
     */
    public static File downloadFile(FTPClient ftpClient, String servicePath, String fileName, String localFilePath) throws Exception {
        String name = dwonFile(ftpClient, servicePath, fileName, localFilePath);
        if (!name.equals("")) {
            return new File(fileName);
        } else {
            return null;
        }
    }

    /**
     * 上传文件
     *
     * @param serviceDec ftp服务保存地址
     * @param fileName   上传到ftp的文件名
     * @param original   待上传文件的名称（绝对地址） *
     * @return
     */
    public static boolean uploadFile(FTPClient ftpClient, String serviceDec, String fileName, String original) {
        LOG.info("开始上传文件");
        try (InputStream input = new FileInputStream(new File(original))) {
            return uploadFile(ftpClient, serviceDec, fileName, input);
        } catch (IOException e) {
            LOG.error("文件上传失败" + e);
        }
        return false;
    }

    /**
     * 上传文件
     *
     * @param serviceDec  ftp服务保存地址
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(FTPClient ftpClient, String serviceDec, String fileName, InputStream inputStream) {
        try {
            LOG.info("开始上传文件");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            String path = new String(serviceDec.getBytes("GBK"), FTP.DEFAULT_CONTROL_ENCODING);
            String name = new String(fileName.getBytes("GBK"), FTP.DEFAULT_CONTROL_ENCODING);
            createDirecroty(ftpClient, path);
            ftpClient.makeDirectory(path);
            ftpClient.changeWorkingDirectory(path);
            ftpClient.storeFile(name, inputStream);
            inputStream.close();
            ftpClient.logout();
            LOG.info("上传文件成功");
        } catch (Exception e) {
            LOG.error("上传文件失败" + e);
        } finally {
            try {
                if (ftpClient.isConnected())
                    ftpClient.disconnect();
                if (null != inputStream)
                    inputStream.close();
            } catch (IOException e) {
                LOG.error("上传文件失败" + e);
            }
        }
        return true;
    }

    //改变目录路径
    private static boolean changeWorkingDirectory(FTPClient ftpClient, String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                LOG.info("进入文件夹" + directory + " 成功！");

            } else {
                LOG.info("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    private static void createDirecroty(FTPClient ftpClient, String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";

        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(ftpClient, new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            do {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(ftpClient, path)) {
                    if (makeDirectory(ftpClient, subDirectory)) {
                        changeWorkingDirectory(ftpClient, subDirectory);
                    } else {
                        LOG.info("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(ftpClient, subDirectory);
                    }
                } else {
                    changeWorkingDirectory(ftpClient, subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
            } while (end > start);
        }
    }

    //判断ftp服务器文件是否存在
    private static boolean existFile(FTPClient ftpClient, String path) throws IOException {
        boolean flag = false;
        String ftpPath = new String(path.getBytes("GBK"), FTP.DEFAULT_CONTROL_ENCODING);
        FTPFile[] ftpFileArr = ftpClient.listFiles(ftpPath);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    //创建目录
    private static boolean makeDirectory(FTPClient ftpClient, String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                LOG.info("创建文件夹" + dir + " 成功！");

            } else {
                LOG.info("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static List<String> getFileNameList(FTPClient ftpClient, String ftpDirPath) {
        List<String> list = new ArrayList<String>();

        try {
            if (ftpDirPath.startsWith("/") && ftpDirPath.endsWith("/")) {
                // 通过提供的文件路径获取FTPFile对象列表
                FTPFile[] files = ftpClient.listFiles(ftpDirPath);
                // 遍历文件列表，打印出文件名称
                for (int i = 0; i < files.length; i++) {
                    FTPFile ftpFile = files[i];
                    // 此处只打印文件，未遍历子目录（如果需要遍历，加上递归逻辑即可）
                    if (ftpFile.isFile()) {
                        // log.info(ftpDirPath + ftpFile.getName());
                        String name = new String(ftpFile.getName().getBytes("utf-8"), FTP.DEFAULT_CONTROL_ENCODING);
                        System.out.println(name);
                        list.add(unicodeToUtf8(ftpFile.getName()));
                    }
                }
                LOG.info("当前FTP路径可用");
            } else {
                LOG.info("当前FTP路径不可用");
            }
        } catch (Exception e) {
            LOG.error("错误" + e);
        }
        return list;
    }

    /**
     * 获取到服务器文件夹里面最新创建的文件名称
     *
     * @param ftpDirPath 文件路径
     * @param ftpClient  ftp的连接
     * @return fileName
     */
    public static String getNewFile(FTPClient ftpClient, String ftpDirPath) throws Exception {
        if (ftpDirPath.startsWith("/") && ftpDirPath.endsWith("/")) {
            // 通过提供的文件路径获取FTPFile对象列表
            FTPFile[] files = ftpClient.listFiles(ftpDirPath);
            if (files == null) throw new Exception("文件数组为空");
            Arrays.sort(files, new Comparator<FTPFile>() {
                public int compare(FTPFile f1, FTPFile f2) {
                    return f1.getTimestamp().compareTo(f2.getTimestamp());
                }

                public boolean equals(Object obj) {
                    return true;
                }
            });
            return ftpDirPath + "/" + files[files.length - 1].getName();
        } else throw new Exception("文件夹路径错误！");
    }

    public static String unicodeToUtf8(String str) throws Exception {
        return new String(str.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        //文件下载测试
        FtpUtil ftpUtil = new FtpUtil();
        FTPClient ftpClient = ftpUtil.loginFtp();
        File file = downloadFile(ftpClient, "download/", "测试1.png", "D://pic/");
        //文件上传测试 ：注意ftp服务路径问题。上传与下载并不相同，尽管都是同级目录。可以自己尝试更换路径了解
        if (uploadFile(ftpClient, "/upload", "测试2.png", "D://pic/测试2.png")) {
            //文件上传只会覆盖，并不会判断
            LOG.info("上传成功");
        } else {
            LOG.info("上传失败");
        }
        //对文件存在是否做出判断
        //对目录判断同样有效
        if (existFile(ftpClient, "upload/测试1.png")) {
            LOG.info("文件存在");
        } else {
            LOG.info("文件不存在");
        }
        //获取目录列表下所有文件名 注意路径格式前后都要有”/“
        List<String> list = getFileNameList(ftpClient, "/upload/");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        String gbk = new String("这是GBK".getBytes("GBK"), FTP.DEFAULT_CONTROL_ENCODING);
        System.out.println(gbk);
        byte[] bytes = gbk.getBytes();
        String utf8 = new String(bytes, "utf-8");
        System.out.println(utf8);
    }
}
