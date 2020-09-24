package edu.yctc.common.utils.file;

import java.io.*;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Utils {
    /**
     * @author 甘八十
     * 图片转化成base64字符串
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param imgPath
     * @return
     */

    public static String GetImageStr(String imgPath) {
        // 待处理的图片
        String imgFile = imgPath;
        InputStream in = null;
        byte[] data = null;
        // 返回Base64编码过的字节数组字符串
        String encode = null;
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            // 读取图片字节数组
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = encoder.encode(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return encode;
    }

    @SuppressWarnings("finally")
    public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        // 图像数据为空
        if (imgData == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }
}