package edu.yctc.face.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * 截图工具类
 * 
 * @author xiaotao
 */
public class PicCut {

    /**
     * 裁剪部分图片并保存
     * 
     * @param x 需要裁剪的左上角横坐标
     * @param y 需要裁剪的左上角纵坐标
     * @param width 需要裁剪的宽度
     * @param height 需要裁剪的高度
     * @param srcpath 需要裁剪的图片的路径
     * @param subpath 保存路径
     * @throws IOException 文件异常
     */
    public static void cut(int x, int y, int width, int height, String srcpath, String subpath) throws IOException {
        FileInputStream is = null;
        ImageInputStream iis = null;
        try {
            /** 读取原始图片 */
            is = new FileInputStream(srcpath);
            /** ImageReader声称能够解码指定格式 */
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = it.next();
            /** 获取图片流 */
            iis = ImageIO.createImageInputStream(is);
            /** 将iis标记为true（只向前搜索）意味着包含在输入源中的图像将只按顺序读取 */
            reader.setInput(iis, true);
            /** 指定如何在输入时从 Java Image I/O框架的上下文中的流转换一幅图像或一组图像 */
            ImageReadParam param = reader.getDefaultReadParam();
            /** 定义空间中的一个区域 */
            Rectangle rect = new Rectangle(x, y, width, height);
            /** 提供一个 BufferedImage，将其用作解码像素数据的目标 */
            param.setSourceRegion(rect);
            /** 读取索引imageIndex指定的对象 */
            BufferedImage bi = reader.read(0, param);
            /** 保存新图片 */
            ImageIO.write(bi, "jpg", new File(subpath));
        } finally {
            if (is != null) {
                is.close();
            }
            if (iis != null) {
                iis.close();
            }
        }
    }
}
