package com.ujuit.quant.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 
/**
 * @Description 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年3月13日
 * @company 有据信息技术有限公司
 */
public class GenerateRandomImage {

    
    private Random random = new Random();
    private String randString = "023456789ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghjklmnopqrstuvwxyz";//随机产生的字符串
    
    private int width = 80;//图片宽
    private int height = 40;//图片高
    private int lineSize = 60;//干扰线数量
    private int stringNum = 4;//随机产生字符数量
   
    /**获得字体
     * @return
     * @date 2017年3月13日
     * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
     */
    private Font getFont(){
        return new Font("Fixedsys",Font.CENTER_BASELINE,18);
    }
 
    /**获得颜色
     * @param fc
     * @param bc
     * @return
     * @date 2017年3月13日
     * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
     */
    private Color getRandColor(int fc,int bc){
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }
    /***  
     * @return 随机返一个指定区间的数字 
     */  
    private int getIntRandom(int start,int end)  
    {   if(end<start)  
        {  
          int t=end;  
          end=start;  
          start=t;  
        }  
        int i=start+(int) (Math.random()*(end-start));  
        return i;  
    } 
    /** 
     * 由随机产生的方块来作为干扰背景 
     */  
    private void setSquareBackGround(Graphics g,int width,int height,int count){  
        // 随机产生干扰线条  
        for (int i=0;i<getIntRandom(count, count+2);i++) {   
            int x1 =getIntRandom(0,(int)(width*0.3));   
            int y1 =getIntRandom(0,(int)(height*0.3));   
            int x2 =getIntRandom((int)(width*0.5),width);   
            int y2 =getIntRandom((int)(height*0.55),height);    
            g.setColor(getColor(100));  
            int w=x2-x1;  
            int h=y2-y1;  
            if(w<0) w=-w;  
            if(h<0) h=-h;  
            g.drawRect(x1, y1, w, h);  
            g.setColor(getColor(25));  
            g.fillRect(x1, y1, w, h);  
        }   
    }  
    
    
  /*** 随机返回一种颜色,透明度0~255 0表示全透 
   * @return 随机返回一种颜色 
   * @param alpha 透明度0~255 0表示全透 
   */  
  private Color getColor(int alpha)  
  {  
      int R=(int) (Math.random()*255);  
      int G=(int) (Math.random()*255);  
      int B=(int) (Math.random()*255);  
  return new Color(R,G,B,alpha);  
  } 
    
    /**生成随机图片
     * @param request
     * @param response
     * @date 2017年3月13日
     * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
     */
    public String getRandcode(HttpServletRequest request,
            HttpServletResponse response ) {
         //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        g.setColor(getRandColor(110, 133));
        
        //画随机干扰框  
        setSquareBackGround(g,width,height,3);    
      //画干扰点  
        createRandomPoint(width, height,100,g,100);
        for(int i=0;i<=lineSize;i++){
            drowLine(g);
        }
        //绘制随机字符
        String randomString = "";
        for(int i=1;i<=stringNum;i++){
            randomString=drowString(g,randomString,i);
        }
        System.out.println("图形验证码"+randomString);
    
        g.dispose();
        try {
    		response.setHeader("Cache-Control", "no-store");
    		response.setHeader("Pragma", "no-cache");
    		response.setDateHeader("Expires", 0L);
    		response.setContentType("image/jpeg");
            ImageIO.write(image, "JPEG", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomString;
    }
    
  /** 
   * 随机产生干扰点 
   * @param width 
   * @param height 
   * @param many -生产个数
   * @param g 
   * @param alpha 透明度0~255 0表示全透 
   */  
  private void createRandomPoint(int width,int height,int many,Graphics g,int alpha)  
  {  // 随机产生干扰点  
      for (int i=0;i<many;i++) {  
          int x = random.nextInt(width);   
          int y = random.nextInt(height);   
          g.setColor(getColor(alpha));  
          g.drawOval(x,y,random.nextInt(3),random.nextInt(3));   
      }   
  }
    /**绘制字符串
     * @param g
     * @param randomString
     * @param i
     * @return
     * @date 2017年3月13日
     * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
     */
    private String drowString(Graphics g,String randomString,int i){
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString +=rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13*i, 24);
        return randomString;
    }
 
    /**绘制干扰线
     * @param g
     * @date 2017年3月13日
     * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
     */
    private void drowLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x+xl, y+yl);
    }
   
    /**获取随机的字符
     * @param num
     * @return
     * @date 2017年3月13日
     * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
     */
    public String getRandomString(int num){
        return String.valueOf(randString.charAt(num));
    }
}