package HW4;

import HW2.Part3.Cal;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/4/9 21:19
 */
public class TestDemo {

    public static void main(String[] args) throws Exception {
        System.out.println(CalendarUtil.solarToLunar("20180301"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018,8,1,0,0);
        System.out.println(CalendarUtil.getDateOfMonth(calendar));
        JFrame jFrame  = new JFrame();
        jFrame.setLayout(new FlowLayout());
        jFrame.setBounds(100,100,300,300);
        JButton jButton = new JButton("你好");
        jButton.setForeground(Color.GREEN);
        jFrame.add(jButton,FlowLayout.LEFT);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
//    public static void main(String[] args)
//    {
//        //创建框架
//        JFrame myFrame=new JFrame("图画");
//        //myFrame.setLocation(200, 300);//第1参数表示离左屏幕边框距离，第2参数表示离屏幕上边框距离
//        myFrame.setSize(600, 400);
//        myFrame.setResizable(true);
//        myFrame.setDefaultCloseOperation(3);
//        //创建按钮
//        JButton blackButton,whiltButton,otherButton;
//        blackButton=new JButton("黑色");
//        whiltButton=new JButton("白色");
//        otherButton=new JButton("自定义");
//        //设置背景颜色、按钮颜色
//        JPanel jp=new JPanel();
//        jp.add(blackButton);
//        jp.add(whiltButton);
//        jp.add(otherButton);
//        myFrame.add(jp);
//        jp.setBackground(Color.GREEN);
//        blackButton.setForeground(Color.BLACK);
//        whiltButton.setForeground(Color.YELLOW);
//        otherButton.setForeground(Color.BLUE);
//        myFrame.setVisible(true);
//    }
}
