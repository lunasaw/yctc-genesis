package edu.yctc.common.utils.file;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class FileUtilsAlter {
    /**
     * 判断文件夹是否存在 不存在则创建文件夹
     *
     * @param dirPath 文件夹路径
     * @return 是否成功
     */
    static public Boolean isDirExists(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return dir.mkdirs();
        }
        return true;
    }

    /**
     * 读取图片
     *
     * @param img 图片
     * @return 图片字节
     */
    public static byte[] fileToByte(File img) {
        byte[] bytes = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "png", outputStream);
            bytes = outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 下载http文件
     *
     * @param url 文件地址
     * @param dir 存储目录
     * @param fileName 存储文件名
     * @return 下载后的文件存储路径
     */
    public static void downloadHttpUrl(String url, String dir, String fileName) throws IOException {

        URL urlPath = new URL(url);
        FileUtils.copyURLToFile(urlPath, new File(dir + fileName));

    }

    /**
     * 删除文件
     * 
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean deleteServerFile(String filePath, String fileName) {
        boolean delete_flag = false;
        File file = new File(filePath + fileName);
        delete_flag = file.exists() && file.isFile() && file.delete();
        return delete_flag;
    }

    /**
     * 获取http文件大小
     *
     * @param path 待下载的文件地址
     * @return
     * @throws IOException
     */
    public static long getHttpFileLenth(String path) throws IOException {
        long length = 0;
        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        length = urlConnection.getContentLengthLong();
        urlConnection.disconnect();
        return length;
    }

}
