package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class CommonRect {

    /** 矩形框左上角像素点的纵坐标 */
    private int top;

    /** 矩形框左上角像素点的横坐标 */
    private int left;

    /** 矩形框的宽度 */
    private int width;

    /** 矩形框的高度 */
    private int height;

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "{" + "\"top\":" + top + ", \"left\":" + left + ", \"width\":" + width + ", \"height\":" + height + '}';
    }
}
