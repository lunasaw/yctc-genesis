package edu.yctc.face.util;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取文件名字工具类
 * 
 * @author xiaotao
 */
public class GetFoldFileNames {

    /** 日志 */
    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");

    /**
     * 获得文件夹下所有文件绝对路径
     * 
     * @param fileName 存放文件绝对路径的容器
     * @param filePath 文件夹路径
     */
    public static void getFilePath(List<String> fileName, String filePath) {
        File folder = new File(filePath);
        /** 判断文件夹是否存在 */
        if (folder.exists()) {
            /** 文件夹列表 */
            LinkedList<File> fileList = new LinkedList<File>();
            /** 文件夹下文件数组 */
            File[] files = folder.listFiles();
            for (File file : files) {
                /** 如果是文件夹就加入文件夹列表 */
                if (file.isDirectory()) {
                    fileList.add(file);
                } else {
                    try {
                        /** 是文件就加入存放文件路径的容器 */
                        fileName.add(file.getCanonicalPath());
                    } catch (IOException e) {
                        LOG.error("getFilePath IO error, filename={}, exception={}", fileName, e);
                    }
                }
            }
            File tempFile;
            while (!fileList.isEmpty()) {
                /** 文件夹列表不为空 */
                tempFile = fileList.removeFirst();
                files = tempFile.listFiles();
                for (File file : files) {
                    if (file.isDirectory()) {
                        fileList.add(file);
                    } else {
                        try {
                            fileName.add(file.getCanonicalPath());
                        } catch (IOException e) {
                            LOG.error("getFilePath IO error, filename={}, exception={}", fileName, e);
                        }
                    }
                }
            }
        } else {
            /** 文件不存在 */
            LOG.error("getFilePath IO error," + filePath + " not exists");
        }
    }

    /**
     * 获得文件夹下所有文件名字
     * 
     * @param fileName 存放字符串的容器
     * @param filePath 文件夹路径
     */
    public static void getFileName(List<String> fileName, String filePath) {
        File folder = new File(filePath);
        /** 判断文件夹是否存在 */
        if (folder.exists()) {
            /** 文件夹列表 */
            LinkedList<File> fileList = new LinkedList<File>();
            /** 文件夹下文件数组 */
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    /** 如果是文件夹就加入文件夹列表 */
                    fileList.add(file);
                } else {
                    /** 是文件就加入存放文件名字的容器 */
                    fileName.add(file.getName());
                }
            }
            File tempFile;
            /** 文件夹列表不为空 */
            while (!fileList.isEmpty()) {
                tempFile = fileList.removeFirst();
                files = tempFile.listFiles();
                for (File file : files) {
                    if (file.isDirectory()) {
                        fileList.add(file);
                    } else {
                        fileName.add(file.getName());
                    }
                }
            }
        } else {
            /** 文件不存在 */
            LOG.error("getFileName IO error" + filePath + " not exists");
        }
    }
}
