package edu.yctc.face.util;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模型使用工具类
 * 
 * @author xiaotao
 */
public class Model {

    /** 日志 */
    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");

    /** txt文件名字 */
    private final static String resultPathName = "C:\\src\\img\\vgg\\result.txt";

    /** pythin处理的图片名字 */
    private final static String imgPathName = "C:\\src\\img\\vgg\\text.jpg";

    /**
     * 检测听课状态
     * 
     * @param imgPath
     * 图片地址
     * @return 状态 （1:认真听课 2:玩手机 3:睡觉 4:发呆 0:vgg判断出错）
     */
    public static int dealPicture(String imgPath) {
        switch (invocationModel(imgPath)) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 1;
            case 3:
                return 4;
            default:
                LOG.error("vgg error");
                return 0;
        }
    }

    /**
     * 调用模型识别
     * 
     * @param imgPath
     * 图片地址
     * @return 状态 （0:玩手机 1:睡觉 2:认真听课 3:发呆）
     */
    private static int invocationModel(String imgPath) {
        /** 修改图片名字让python处理 */
        File srcImg = new File(imgPath);
        File dstImg = new File(imgPathName);
        srcImg.renameTo(dstImg);

        /** 存放结果的文件 */
        File txtFile = new File(resultPathName);
        String lineTxt = null;
        /** 读取txt文件 */
        while (true) {
            if (txtFile.exists()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    LOG.error("invocationModel sleep error,Exception={}", e1);
                }

                try {
                    InputStreamReader read = new InputStreamReader(new FileInputStream(txtFile));
                    BufferedReader bufferedReader = new BufferedReader(read);
                    lineTxt = bufferedReader.readLine();

                    if (lineTxt == null) {
                        LOG.error("result.txt is empty!");
                        read.close();
                        txtFile.delete();
                        return -1;
                    }

                    read.close();
                    txtFile.delete();
                } catch (IOException e) {
                    LOG.error("result.txt read error!");
                }
                return Integer.parseInt(lineTxt);
            }
        }
    }

}
