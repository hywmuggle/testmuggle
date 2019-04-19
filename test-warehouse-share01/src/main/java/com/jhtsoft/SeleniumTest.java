package com.jhtsoft;

import com.jhtsoft.util.YZMUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @ClassName: SeleniumTest
 * @Describe: 自动化测试:自动打开浏览器登陆进入新营销平台
 * @Author: houyingwei
 * @Date: 2019/4/12
 **/
public class SeleniumTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");//chromedriver服务地址
        WebDriver driver =new ChromeDriver(); //新建一个WebDriver 的对象，但是new 的是FirefoxDriver的驱动
        driver.get("http://118.31.237.82/system/login/login.jsp");//打开指定的网站
        driver.manage().window().maximize();//设置浏览器最大化
        try {
            /**
             * 准备网页登陆信息——验证码
             */
            //获取验证码图片到本地
            WebElement ele = driver.findElement(By.xpath("//*[@id='checkCodeImg']"));//找到验证码元素位置
            File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshotAs);
            Point point = ele.getLocation();
            int eleWidth = ele.getSize().getWidth();    //将验证码拿到本地
            int eleHeight = ele.getSize().getHeight();
            BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
            File screenshotLocation = new File("D:\\javaImg\\123.jpg"); //给验证码命名之后下次自己就替换掉了
            ImageIO.write(eleScreenshot,"jpg",screenshotLocation);
            //使用若快打码解析验证码图片
            String s = YZMUtil.createByPost("15083101130", "RKhyw2019", "1040", "90", "126559", "7572cfc839b74b70a5d7b7bb4553fe0f", "D:\\javaImg\\123.jpg");
            String s11 = s.substring(0,4);//截取前四位的验证码
            System.out.println("验证码:" + s11);
            /**
             *登陆操作
             */
            driver.findElement(By.id("username")).sendKeys("test33");//输入账号
            driver.findElement(By.id("password")).sendKeys("123456");//输入密码
            driver.findElement(By.id("checkCode")).sendKeys(s11);//输入验证码
            driver.findElement(By.id("loginBtn")).click();//点击登陆按钮
            Thread.sleep(3000);//等待三秒
            driver.findElement(By.xpath("//*[@id='bs_bannerleft']/div[1]/div[2]")).click();//点击按钮
            /**
             * 操作爬取数据
             */

//            String text = driver.findElement(By.xpath("//*[@id='scrollContent']/table/tbody/tr[2]/td[2]")).getText();
//            System.out.println("文本信息:" + text);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //driver.quit();//退出浏览器
    }

/*    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");//chromedriver服务地址
        WebDriver driver =new ChromeDriver(); //新建一个WebDriver 的对象，但是new 的是FirefoxDriver的驱动
        driver.get("http://www.baidu.com");//打开指定的网站
        driver.manage().window().maximize();//设置浏览器最大化
        driver.findElement(By.id("kw")).sendKeys(new  String[] {"hello侯迎伟"});//找到kw元素的id，然后输入hello
        driver.findElement(By.id("su")).click(); //点击按扭
        try {
            *//**
             * WebDriver自带了一个智能等待的方法。
             dr.manage().timeouts().implicitlyWait(arg0, arg1）；
             Arg0：等待的时间长度，int 类型 ；
             Arg1：等待时间的单位 TimeUnit.SECONDS 一般用秒作为单位。
             *//*
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        *//**
         * dr.quit()和dr.close()都可以退出浏览器,简单的说一下两者的区别：第一个close，
         * 如果打开了多个页面是关不干净的，它只关闭当前的一个页面。第二个quit，
         * 是退出了所有Webdriver所有的窗口，退的非常干净，所以推荐使用quit最为一个case退出的方法。
         *//*
        //driver.quit();//退出浏览器

//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.baidu.com/");
//        driver.findElement(By.id("kw")).sendKeys("selenium java");
//        driver.findElement(By.id("su")).click();
//        driver.quit();

    }*/
}
