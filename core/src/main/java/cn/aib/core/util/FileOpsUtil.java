/*
 * FileOpsUtil.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.core.util;

import org.joda.time.DateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 文件操作相关
 * @author shiqi
 * Created by shiqi on 2017/10/27.
 */
public class FileOpsUtil {
    public static String DEFAULT_PATH_FOR_IN = "in";
    public static String DEFAULT_PATH_FOR_OUT = "out";
    public static String SEPARATOR_INT_PATH = "\\";
    public static String SEPARATOR_IN_FILE_NAME = "-";

    /**
     * 获取文件
     * @param dirName 目录
     * @param fileName 文件名
     * @return File 文件
     */
    public static File retrieveFile(final String dirName, final String fileName) {
        return new File(dirName + SEPARATOR_INT_PATH + fileName);
    }

    public static File retrieveFile(final String fileName) {
        return retrieveFile(DEFAULT_PATH_FOR_IN, fileName);
    }

    /**
     * 创建文件
     * @param dirName 目录
     * @param fileName 文件名
     * @param sufWithDate 是否以日期时间结尾(yyyyMMddHHmmss)
     * @param append 是否使用append形式
     * @throws IOException
     */
    public static File createFileInDirectory(final String dirName,
            final String fileName, final boolean sufWithDate, final boolean append)
            throws IOException {
        // 创建目录
        final File dir = new File(dirName);
        if (!dir.exists()) {
            System.out.println("[FileOpsUtil-createFileInDirectory]:"
                    + "创建目录：" + dir.getAbsolutePath());
            dir.mkdirs();
        }
        // 创建文件
        final File file = new File(dir, fileName.replace("|", "")
                + (sufWithDate ? SEPARATOR_IN_FILE_NAME + (new DateTime(new Date())).toString(
                "yyyyMMddHHmmss") : ""));
        System.out.println("[FileOpsUtil-createFileInDirectory]:"
                + "创建文件：" + file.getAbsolutePath());
        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    public static File createFileInDirectory(final String dirName,
            final String fileName) throws IOException {
        return createFileInDirectory(dirName, fileName, false, false);
    }

    public static File createFileInDirectory(final String fileName)
            throws IOException {
        return createFileInDirectory(DEFAULT_PATH_FOR_OUT, fileName, false, false);
    }

    /**
     * 写文件
     */
    public static void writeToFile(final File file, final InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(file);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }

    public static void writeToFile(final String dirName, final String fileName,
            final InputStream input) throws IOException {
        writeToFile(createFileInDirectory(dirName, fileName), input);
    }

    /**
     * 文件重命名
     */
    public static void renameFile(final File file, final String targetName) {
        file.renameTo(new File(file.getParent() + SEPARATOR_INT_PATH + targetName));
    }

    public static void main(String[] args) {
        final File file = new File("D:\\project\\out\\第四卷002-希望有转角.mp3");
        renameFile(file, "test");
    }
}
