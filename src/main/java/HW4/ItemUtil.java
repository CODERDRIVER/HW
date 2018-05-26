package HW4;

import HW2.Part1.DateUtil;
import HW4.model.PIMTodo;
import HW4.service.PIMTodoService;
import HW4.service.impl.PIMTodoServiceImpl;

import javax.swing.*;
import java.awt.*;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/19 23:58
 */
public class ItemUtil {

    public static PIMTodoService pimTodoService = new PIMTodoServiceImpl();

    public static  JFrame createItem()
    {
        JFrame jFrame = new JFrame("New Item");
        jFrame.setVisible(true);
        jFrame.setBounds(300,200,300,300);
        return jFrame;
    }

    /**
     * 添加一个todoItem项
     */
    public static void addTodoButtonResponse(JButton jButton)
    {
        jButton.addActionListener(event->{
            JFrame jFrame = new JFrame("Todo");
            jFrame.setBounds(300,200,600,400);
            jFrame.setLayout(null);
            JPanel[] jPanels = new JPanel[4];
            jPanels[0]= fillFrame("请输入拥有者的名称：",10,10);
            jPanels[1] = fillFrame("请输入item的priority: ",10,60);
            jPanels[2] = fillFrame("请输入item的内容: ",10,110);
            jPanels[3] = fillFrame("请输入item的日期(MM/dd/yyyy): ",10,160);

            for(int i=0;i<jPanels.length;i++)
            {
                jFrame.add(jPanels[i]);
            }
            JButton jButton1 = new JButton("提交");
            jButton1.setBounds(270,250,100,50);
            jButton1.setForeground(Color.PINK);
            jFrame.add(jButton1);
            jFrame.setVisible(true);

            //给提交添加监听事件，将内容提交到数据库中
            jButton1.addActionListener(e->{
                PIMTodo pimTodo = new PIMTodo();
                pimTodo.setOwer(((JTextField)jPanels[0].getComponents()[1]).getText());
                pimTodo.setPriority(((JTextField)jPanels[1].getComponents()[1]).getText());
                pimTodo.setTodoItem(((JTextField)jPanels[2].getComponents()[1]).getText());
                pimTodo.setDate(DateUtil.fromStringToDate(((JTextField)jPanels[3].getComponents()[1]).getText()));
                //保存到数据库中
                pimTodoService.addPIMTodo(pimTodo);
                int status = JOptionPane.showConfirmDialog(jFrame,"保存成功");

                //关闭该frame
                jFrame.dispose();

            });
    });
    }

    /**
     * 创建一系列的Jpanel,用来填充JFrame
     * @param inputText
     * @returny
     */
    public static JPanel fillFrame(String inputText,int x,int y)
    {
        JLabel jable1 = new JLabel(inputText);
        JTextField jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(130,30));
        JPanel jPanel = new JPanel();
        jPanel.setBounds(x,y,600,50);
        jPanel.setBackground(Color.GREEN);
        jPanel.add(jable1,BorderLayout.WEST);
        jPanel.add(jTextField,BorderLayout.CENTER);
        return jPanel;
    }
}
