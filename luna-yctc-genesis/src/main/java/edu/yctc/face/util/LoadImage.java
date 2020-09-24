package edu.yctc.face.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片加载工具类
 * 
 * @author xiaotao
 */
public class LoadImage {

    /** 日志 */
    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");

    /** 缓冲区大小 */
    static final int BUFFERSIZE = 1024;

    /**
     * 把图片文件转换成字节数组
     * 
     * @param f 图片路径
     * @return 图片的字节数组 文件不存在返回null 转换有问题返回null
     */
    public static byte[] getBytesFromFile(File f) {
        /** 文件不存在返回null */
        if (null == f) {
            LOG.error("LoadImage File is null");
            return null;
        }

        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n = 0;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            LOG.error("LoadImage IO error, f={}, exception={}", f, e);
        }

        /** 转换有问题返回null */
        return null;
    }

    /**
     * 把BufferedImage图片转换成字节数组
     * 
     * @param javaImage 图片
     * @return 图片的字节数组 图片不存在返回null 转换有问题返回null
     */
    public static byte[] getBytesFromBufferedImage(BufferedImage javaImage) {
        /** 图片不存在返回null */
        if (null == javaImage) {
            LOG.error("LoadImage BufferedImage is null");
            return null;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageInByte = null;
        try {
            ImageIO.write(javaImage, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            LOG.error("LoadImage IO error, javaImage={}, IOException={}", javaImage, e);
        }
        /** 转换有问题返回null */
        return null;
    }

    /**
     * 根据URL加载图片转化为字节数组
     * 
     * @param destUrl 目的URL链接
     * @return 图片的字节数组
     */
    static byte[] getBytesFromUrl(String destUrl) {
        BufferedInputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(BUFFERSIZE);
        HttpURLConnection httpUrl = null;
        URL url = null;
        int size = 0;
        byte[] buffer = new byte[BUFFERSIZE];
        try {
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection)url.openConnection();
            httpUrl.connect();
            inputStream = new BufferedInputStream(httpUrl.getInputStream());
            while ((size = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, size);
            }
        } catch (IOException e) {
            LOG.error("LoadImage IO error, destUrl={}, exception={}", destUrl, e);
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                httpUrl.disconnect();
            } catch (IOException e) {
                LOG.error("LoadImage IO error, destUrl={}, exception={}", destUrl, e);
            }
        }
        return outputStream.toByteArray();
    }

}
