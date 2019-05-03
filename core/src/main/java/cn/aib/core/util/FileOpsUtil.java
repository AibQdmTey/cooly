/*
 * FileOpsUtil.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.core.util;

import org.joda.time.DateTime;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 文件操作相关
 * @author shiqi
 * Created by shiqi on 2017/10/27.
 */
public class FileOpsUtil {
    public static String DEFAULT_PATH_FOR_IN = "in";
    public static String DEFAULT_PATH_FOR_OUT = "out";
    public static String SEPARATOR_INT_PATH = "/";
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
     * 创建文件 并 获取写入流
     * @param dirName 目录
     * @param fileName 文件名
     * @param sufWithDate 是否以日期时间结尾(yyyyMMddHHmmss)
     * @param append 是否使用append形式
     * @return BufferedWriter 写入流
     * @throws IOException
     */
    public static BufferedWriter retrieveFileWriterInDirectory(final String dirName,
            final String fileName, final boolean sufWithDate, final boolean append)
            throws IOException {
        // 创建目录
        final File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 创建文件
        final File file = new File(dir, fileName + (sufWithDate ?
                SEPARATOR_IN_FILE_NAME + (new DateTime(new Date())).toString("yyyyMMddHHmmss")
                : ""));
        if (!file.exists()) {
            file.createNewFile();
        }

        return new BufferedWriter(new FileWriter(file, append));
    }

    public static BufferedWriter retrieveFileWriterInOut(final String dirName,
            final String fileName) throws IOException {
        return retrieveFileWriterInDirectory(DEFAULT_PATH_FOR_OUT + SEPARATOR_INT_PATH + dirName,
                fileName, true, false);
    }

    public static BufferedWriter retrieveFileWriterInOut(final String fileName)
            throws IOException {
        return retrieveFileWriterInDirectory(DEFAULT_PATH_FOR_OUT, fileName, true, false);
    }
}
