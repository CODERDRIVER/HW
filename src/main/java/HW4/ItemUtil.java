package HW4;

import javax.swing.*;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/19 23:58
 */
public class ItemUtil {

    public static  JFrame createItem()
    {
        JFrame jFrame = new JFrame("New Item");
        jFrame.setVisible(true);
        jFrame.setBounds(300,200,300,300);
        return jFrame;
    }
}
