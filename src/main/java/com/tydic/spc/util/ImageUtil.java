package com.tydic.spc.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class ImageUtil {
    /**
     * 读取远程图片
     *
     * @param url 图片地址
     * @return BufferedImage 对象
     * @throws java.io.IOException IO异常
     */
    public static BufferedImage getBufferedImageFromUrl(String url) throws IOException {
        return ImageIO.read(new URL(url).openStream());
    }

    /**
     * 将图片写入输出流
     *
     * @param image  BufferedImage 对象
     * @param format 输出格式
     * @param output 输出流
     * @throws java.io.IOException IO异常
     */
    public static void write(BufferedImage image, String format, OutputStream output) throws IOException {
        ImageIO.write(image, format, output);
    }

    /**
     * 重定义图片的大小
     *
     * @param source     原图
     * @param destWidth  目标宽度
     * @param destHeight 目标高度
     * @return 新图
     */
    public static BufferedImage resize(BufferedImage source, int destWidth, int destHeight) {
        Image image = source.getScaledInstance(destWidth, destHeight, Image.SCALE_DEFAULT);
        BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);  //缩放图像
        Graphics2D g = tag.createGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0,0,destWidth,destHeight);
        g.drawImage(image, 0, 0, null); // 绘制缩小后的图
        g.dispose();

        return tag;
    }

}
