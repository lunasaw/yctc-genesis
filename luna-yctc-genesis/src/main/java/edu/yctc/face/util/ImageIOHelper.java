package edu.yctc.face.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import com.sun.media.imageio.plugins.tiff.TIFFImageWriteParam;

/**
 * 类说明 ：创建临时图片文件防止损坏初始文件
 */
public class ImageIOHelper {

    //设置语言
    private Locale locale=Locale.CHINESE;

    //自定义语言构造的方法
    public ImageIOHelper(Locale locale){
        this.locale=locale;
    }
    //默认构造器Locale.CHINESE
    public ImageIOHelper(){
    }


    /**
     * 创建临时图片文件防止损坏初始文件
     * @param imageFile
     * @param imageFormat like png,jps .etc
     * @return TempFile of Image
     */
    public File createImage(File imageFile, String imageFormat) throws IOException {

        //读取图片文件
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(imageFormat);
        ImageReader reader = readers.next();
        //获取文件流
        ImageInputStream iis = ImageIO.createImageInputStream(imageFile);
        reader.setInput(iis);
        IIOMetadata streamMetadata = reader.getStreamMetadata();

        //设置writeParam
        TIFFImageWriteParam tiffWriteParam = new TIFFImageWriteParam(Locale.CHINESE);
        tiffWriteParam.setCompressionMode(ImageWriteParam.MODE_DISABLED); //设置可否压缩
        //获得tiffWriter和设置output
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("tiff");
        ImageWriter writer = writers.next();


        BufferedImage bi = reader.read(0);
        IIOImage image = new IIOImage(bi,null,reader.getImageMetadata(0));
        File tempFile = tempImageFile(imageFile);
        ImageOutputStream ios = ImageIO.createImageOutputStream(tempFile);

        writer.setOutput(ios);
        writer.write(streamMetadata, image, tiffWriteParam);

        ios.close();
        iis.close();
        writer.dispose();
        reader.dispose();

        return tempFile;
    }

    /**
     * 给tempfile添加后缀
     * @param imageFile
     * @throws IOException
     */
    private File tempImageFile(File imageFile) throws IOException {
        String path = imageFile.getPath();
        StringBuffer strB = new StringBuffer(path);
        strB.insert(path.lastIndexOf('.'),"_text_recognize_temp");
        String s=strB.toString().replaceFirst("(?<=//.)(//w+)$", "tif");
        Runtime.getRuntime().exec("attrib "+"\""+s+"\""+" +H"); //设置文件隐藏
        return new File(strB.toString());
    }
}