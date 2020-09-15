package edu.yctc.face.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jdesktop.swingx.util.OS;

/**
 * 类说明：OCR工具类
 */
public class OCRUtil {
    private final String LANG_OPTION = "-l";  //英文字母小写l，并非阿拉伯数字1
    private final String EOL = System.getProperty("line.separator");
    private String tessPath = "D:\\Tesseract-OCR";//ocr的安装路径


    public OCRUtil(String tessPath,String transFileName){
        this.tessPath=tessPath;
    }

    //OCRUtil的构造方法,默认路径是"C://Program Files (x86)//Tesseract-OCR"
    public OCRUtil(){     }

    public String getTessPath() {
        return tessPath;
    }
    public void setTessPath(String tessPath) {
        this.tessPath = tessPath;
    }
    public String getLANG_OPTION() {
        return LANG_OPTION;
    }
    public String getEOL() {
        return EOL;
    }


    /**
     * @param 需要识别的文件
     * @param 文件的格式
     * @return 识别后的文字
     */
    public String recognizeText(File imageFile,String imageFormat)throws Exception{
        File tempImage = new ImageIOHelper().createImage(imageFile,imageFormat);
        return ocrImages(tempImage, imageFile);
    }

    //可以自定义语言
    public String recognizeText(File imageFile,String imageFormat,Locale locale)throws Exception{
        File tempImage = new ImageIOHelper(locale).createImage(imageFile,imageFormat);
        return ocrImages(tempImage, imageFile);
    }

    /**
     * @param 临时文件
     * @param 需要识别的文件
     * @return 识别后的内容
     * @throws IOException
     * @throws InterruptedException
     */
    private String ocrImages(File tempImage,File imageFile) throws IOException, InterruptedException{

        //设置输出文件的保存的文件目录,以及文件名
        File outputFile = new File(imageFile.getParentFile(),"test");
        StringBuffer strB = new StringBuffer();


        //设置命令行内容
        List<String> cmd = new ArrayList<String>();
        if(OS.isWindowsXP()){
            cmd.add(tessPath+"//tesseract");
        }else if(OS.isLinux()){
            cmd.add("tesseract");
        }else{
            cmd.add(tessPath+"//tesseract");
        }
        cmd.add("");
        cmd.add(outputFile.getName());
        cmd.add(LANG_OPTION);
        cmd.add("chi_sim");//中文包
        cmd.add("equ");//常用数学公式包
        cmd.add("eng");//英语包


        //创建操作系统进程
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(imageFile.getParentFile());//设置此进程生成器的工作目录   
        cmd.set(1, tempImage.getName());
        pb.command(cmd);//设置要执行的cmd命令 
        pb.redirectErrorStream(true);//设置后续子进程生成的错误输出都将与标准输出合并   

        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);

        Process process = pb.start();//开始执行，并返回进程实例 
        //最终执行命令为：tesseract 1.png test -l chi_sim+equ+eng

        int w = process.waitFor();
        tempImage.delete();//删除临时正在工作文件         
        if(w==0){ // 0代表正常退出
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile.getAbsolutePath()+".txt"),"UTF-8"));
            String str;
            while((str = in.readLine())!=null){
                strB.append(str).append(EOL);
            }
            in.close();

            long endTime = System.currentTimeMillis();
            System.out.println("结束时间：" + endTime);
            System.out.println("耗时：" + (endTime - startTime) + "毫秒");

        }else{
            String msg;
            switch(w){
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
        new File(outputFile.getAbsolutePath()+".txt");//.delete();    
        return strB.toString().replaceAll("\\s*", "");
    }
}