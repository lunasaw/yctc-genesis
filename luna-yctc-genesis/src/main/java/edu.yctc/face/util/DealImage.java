package edu.yctc.face.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 图片处理工具类
 * 
 * @author xiaotao
 */
public class DealImage {

    /**
     * 框出图片中的人脸
     * 
     * @param filePath 图片路径
     * @throws Exception
     */
    public static void paint(String filePath) throws Exception {
        /** 存放图片中的人脸 */
        List<Face> faces = new ArrayList<>();
        faces.clear();
        MyFaceApi.myDetect(filePath, null, faces);

        /** 等待人脸检测完成 */
        WaitForDate.waitForDate(faces);

        /** 标记出人脸 */
        BufferedImage image = ImageIO.read(new FileInputStream(filePath));
        Graphics2D g = (Graphics2D)image.getGraphics();
        Graphics2D g2 = (Graphics2D)g;
        BasicStroke thinStroke = new BasicStroke(1.0f);
        BasicStroke fatStroke = new BasicStroke(6.0f);
        g2.setStroke(thinStroke);
        Color c = g.getColor();
        g.setColor(Color.RED);
        int bgWidth = 0, bgTop = 0, bgLeft = 0, bgHeight = 0;

        for (Face faceTemp : faces) {
            bgWidth = faceTemp.getFaceRectangle().getWidth();
            bgTop = faceTemp.getFaceRectangle().getTop();
            bgLeft = faceTemp.getFaceRectangle().getLeft();
            bgHeight = faceTemp.getFaceRectangle().getHeight();

            /** 美化：调整真实坐标 */
            bgLeft -= bgWidth / 6;
            bgTop -= bgHeight / 6;
            bgWidth += bgWidth / 3;
            bgHeight += bgHeight / 3;

            /** 画方框 */
            Rectangle2D rect = new Rectangle2D.Double(bgLeft, bgTop, bgWidth, bgHeight);
            g.draw(rect);
            /** 画方框的内切椭圆 */
            rect = new Rectangle2D.Double(bgLeft + bgWidth * 0.1, bgTop + bgHeight * 0.1, bgWidth * 0.8,
                bgHeight * 0.8);
            Ellipse2D ellipse = new Ellipse2D.Double();
            ellipse.setFrame(rect);
            g.draw(ellipse);

            /** 画四个角 */
            g2.setStroke(fatStroke);
            g.drawLine(bgLeft, bgTop, bgLeft, bgTop + bgWidth / 3);
            g.drawLine(bgLeft, bgTop, bgLeft + bgHeight / 3, bgTop);
            g.drawLine(bgLeft, bgTop + bgHeight, bgLeft, bgTop + bgHeight * 2 / 3);
            g.drawLine(bgLeft, bgTop + bgHeight, bgLeft + bgWidth / 3, bgTop + bgHeight);
            g.drawLine(bgLeft + bgWidth, bgTop, bgLeft + bgWidth, bgTop + bgWidth / 3);
            g.drawLine(bgLeft + bgWidth, bgTop, bgLeft + bgWidth * 2 / 3, bgTop);
            g.drawLine(bgLeft + bgWidth, bgTop + bgHeight, bgLeft + bgWidth, bgTop + bgHeight * 2 / 3);
            g.drawLine(bgLeft + bgWidth, bgTop + bgHeight, bgLeft + bgWidth * 2 / 3, bgTop + bgHeight);
            g2.setStroke(thinStroke);
        }

        g.setColor(c);

        /** 保存图片 */
        File file = new File(filePath);
        ImageIO.write(image, "jpg", file);
    }

    /**
     * 缩放图片
     * 
     * @param srcPath 图片路径
     * @param destPath 保存路径
     * @param width 处理后图片的宽
     * @param height 处理后图片的高
     * @throws Exception 文件打开保存异常
     */
    public static void zoomImage(String srcPath, String destPath, int width, int height) throws Exception {
        /** 打开文件 */
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        /** 读取图片 */
        BufferedImage bufImg = ImageIO.read(srcFile);
        /** 设置缩放目标图片模板 */
        Image itemp = bufImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        /** 获取缩放比例 */
        double wr = width * 1.0 / bufImg.getWidth();
        double hr = height * 1.0 / bufImg.getHeight();

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        itemp = ato.filter(bufImg, null);

        /** 写入缩减后的图片 */
        ImageIO.write((BufferedImage)itemp, destPath.substring(destPath.lastIndexOf(".") + 1), destFile);
    }
}
