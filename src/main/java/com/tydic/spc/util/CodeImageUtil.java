package com.tydic.spc.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码
 * User: chengjie
 * Date: 12-1-4
 * Time: 上午10:06
 */
public class CodeImageUtil {

    /**
     * 生成随即图片
     * @param request
     * @param response
     * @return 随机数
     * @throws java.io.IOException
     */
    public static String codeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //创建图像
        BufferedImage image = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
        //绘画
        Graphics graphics = image.getGraphics();
        //定义随机数
        Random random = new Random();
        //定义图像背景色
        Color color = new Color(240, 240, 240);
        graphics.setColor(color);
        graphics.fillRect(0, 0, 68, 22);
        StringBuffer sb = new StringBuffer();
        //定义随机数
        char[] chars = "0123456789".toCharArray();
        int index, len = chars.length;
        for (int i = 0; i < 4; i++) {
            index = random.nextInt(len);
            graphics.setColor(new Color(random.nextInt(88), random.nextInt(188), random.nextInt(255)));
            graphics.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));// 输出的字体和大小
            graphics.drawString("" + chars[index], (i * 15) + 3, 18);//写什么数字，在图片的什么位置画
            sb.append(chars[index]);
        }
        ImageIO.write(image, "JPG", response.getOutputStream());
        return sb.toString();
    }
}
