package HW4;

import javax.swing.*;

/**
 * @Description jframe的工具类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/20 15:08
 */
public class JFrameUtil {


    public static void initFrame(JFrame jframe,int x,int y,int width,int height)
    {
        jframe.setVisible(true);
        jframe.setBounds(x,y,width,height);
    }
}
