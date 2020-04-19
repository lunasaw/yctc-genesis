package edu.yctc.face.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdesktop.swingx.util.OS;

/**
 * @ClassName: OCR
 * @Description: TODO(OCR图像识别类)
 * @author: pengjunlin
 * @company: 上海势航网络科技有限公司
 * @date 2018-07-30
 */
public class OcrRecognizer {

    private final String LANG_OPTION = "-l"; // 英文字母小写l，并非数字1

    private final String EOL = System.getProperty("line.separator");

    private String tessPath = "D:\\Tesseract-OCR";

    // private String tessPath = new File("tesseract").getAbsolutePath();

    public String recognizeText(File imageFile, String imageFormat) throws Exception {
        File tempImage = ImageUtil.createImage(imageFile, imageFormat);
        File outputFile = new File(imageFile.getParentFile(), "output");
        StringBuffer strB = new StringBuffer();
        List<String> cmd = new ArrayList<String>();
        if (OS.isWindowsXP()) {
            cmd.add(tessPath + "//tesseract");
        } else if (OS.isLinux()) {
            cmd.add("tesseract");
        } else {
            cmd.add(tessPath + "//tesseract");
        }
        cmd.add("");
        cmd.add(outputFile.getName());
        cmd.add(LANG_OPTION);
        cmd.add("chi_sim");
        // cmd.add("eng");

        ProcessBuilder pb = new ProcessBuilder();
        // 需要单独配置下配置代码如下：
        Map<String, String> env = pb.environment(); // 获得进程的环境
        env.put("TESSDATA_PREFIX", "D:\\Tesseract-OCR");
        pb.directory(imageFile.getParentFile());

        cmd.set(1, tempImage.getName());
        pb.command(cmd);
        pb.redirectErrorStream(true);

        Process process = pb.start();
        // tesseract.exe 1.jpg 1 -l chi_sim
        int w = process.waitFor();

        // 删除临时正在工作文件
        tempImage.delete();

        if (w == 0) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(outputFile.getAbsolutePath()
                    + ".txt"),
                "UTF-8"));

            String str;
            while ((str = in.readLine()) != null) {
                strB.append(str).append(EOL);
            }
            in.close();
        } else {
            String msg;
            switch (w) {
                case 1:
                    msg = "Errors accessing files.There may be spaces in your image's filename.";
                    break;
                case 29:
                    msg = "Cannot recongnize the image or its selected region.";
                    break;
                case 31:
                    msg = "Unsupported image format.";
                    break;
                default:
                    msg = "Errors occurred.";
            }
            tempImage.delete();
            throw new RuntimeException(msg);
        }
        new File(outputFile.getAbsolutePath() + ".txt").delete();
        return strB.toString();
    }
}
