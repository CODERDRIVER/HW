package HW4;

import HW2.Part1.DateUtil;
import HW2.Part1.PIMContact;
import HW2.Part1.PIMNote;
import HW2.Part1.PersonDetail;
import HW4.model.PIMAppointment;
import HW4.model.PIMTodo;
import HW4.service.PIMAppointmentService;
import HW4.service.PIMNoteService;
import HW4.service.PIMTodoService;
import HW4.service.impl.PIMAppointmentServiceImpl;
import HW4.service.impl.PIMNoteServiceImpl;
import HW4.service.impl.PIMTodoServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/19 23:58
 */
public class ItemUtil {

    public static PIMTodoService pimTodoService = new PIMTodoServiceImpl();
    public static PIMNoteService pimNoteService = new PIMNoteServiceImpl();
    public static PIMAppointmentService pimAppointmentService = new PIMAppointmentServiceImpl();

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
        addButtonPanelOfDate(jButton,0);
    }

    /**
     * 给Appointement添加一项
     */
    public static  void addAppointmentButtonResponse(JButton jButton)
    {
        addButtonPanelOfDate(jButton,1);
    }

    /**
     *给note添加一项
     * @param jButton
     */
    public  static  void addNoteButtonResponse(JButton jButton)
    {
        jButton.addActionListener(event->{
            JFrame jFrame = new JFrame( "Note");
            jFrame.setBounds(300, 200, 600, 400);
            jFrame.setLayout(null);
            JPanel[] jPanels = new JPanel[3];
            jPanels[0] = fillFrame("请输入拥有者的名称：", 10, 10);
            jPanels[1] = fillFrame("请输入item的priority: ", 10, 60);
            jPanels[2] = fillFrame("请输入item的内容: ", 10, 110);

            for (int i = 0; i < jPanels.length; i++) {
                jFrame.add(jPanels[i]);
            }
            JButton jButton1 = new JButton("提交");
            jButton1.setBounds(270, 250, 100, 50);
            jButton1.setForeground(Color.PINK);
            jFrame.add(jButton1);
            jFrame.setVisible(true);

            //给提交添加监听事件，将内容提交到数据库中
            jButton1.addActionListener(e -> {
                PIMTodo pimTodo = new PIMTodo();
                HW4.model.PIMNote pimNote = new HW4.model.PIMNote();
                pimNote.setOwer(((JTextField) jPanels[0].getComponents()[1]).getText());
                pimNote.setPriority(((JTextField) jPanels[1].getComponents()[1]).getText());
                pimNote.setNote(((JTextField)jPanels[2].getComponents()[1]).getText());
                //保存到数据库中
                pimNoteService.addPIMNote(pimNote);
                int status = JOptionPane.showConfirmDialog(jFrame, "保存成功");
                //关闭该frame
                jFrame.dispose();
            });
        });
    }

    /**
     * 给Contact添加一项
     * @param jButton
     */
    public static void addContactButtonResponse(JButton jButton)
    {
        jButton.addActionListener(event->{
            JFrame jFrame = new JFrame( "Note");
            jFrame.setBounds(300, 200, 600, 400);
            jFrame.setLayout(null);
            JPanel[] jPanels = new JPanel[5];
            jPanels[0] = fillFrame("请输入拥有者的名称：", 10, 10);
            jPanels[1] = fillFrame("请输入item的priority: ", 10, 60);
            jPanels[2] = fillFrame("请输入联系人的姓: ", 10, 110);
            jPanels[3] = fillFrame("请输入联系人的名: ", 10, 160);
            jPanels[4] = fillFrame("请输入联系人的邮箱: ", 10, 210);

            for (int i = 0; i < jPanels.length; i++) {
                jFrame.add(jPanels[i]);
            }
            JButton jButton1 = new JButton("提交");
            jButton1.setBounds(270, 250, 100, 50);
            jButton1.setForeground(Color.PINK);
            jFrame.add(jButton1);
            jFrame.setVisible(true);

            //给提交添加监听事件，将内容提交到数据库中
            jButton1.addActionListener(e -> {
                HW4.model.PIMContact pimContact = new HW4.model.PIMContact();
                PersonDetail personDetail = new PersonDetail();
                pimContact.setOwer(((JTextField) jPanels[0].getComponents()[1]).getText());
                pimContact.setPriority(((JTextField) jPanels[1].getComponents()[1]).getText());
                personDetail.setFirstName(((JTextField) jPanels[2].getComponents()[1]).getText());
                personDetail.setLastName(((JTextField) jPanels[3].getComponents()[1]).getText());
                personDetail.setEmailAddress(((JTextField) jPanels[4].getComponents()[1]).getText());
                Set set = new HashSet();
                set.add(personDetail);
                pimContact.setSet(set);
                //保存到数据库中
                int status = JOptionPane.showConfirmDialog(jFrame, "保存成功");
                //关闭该frame
                jFrame.dispose();
            });
        });
    }

    /**
     * flag:0-->表示todo
     * flag:1--->表示appointment
     * @param jButton
     */
    public static void addButtonPanelOfDate(JButton jButton,int flag) {
        jButton.addActionListener(event->{
            JFrame jFrame = new JFrame(flag == 0 ? "todo" : "Appointment");
            jFrame.setBounds(300, 200, 600, 400);
            jFrame.setLayout(null);
            JPanel[] jPanels = new JPanel[4];
            jPanels[0] = fillFrame("请输入拥有者的名称：  ", 10, 10);
            jPanels[1] = fillFrame("请输入item的priority:   ", 10, 60);
            jPanels[2] = fillFrame("请输入item的内容:    ", 10, 110);
            jPanels[3] = fillFrame("请输入item的日期(MM/dd/yyyy): ", 10, 160);

            for (int i = 0; i < jPanels.length; i++) {
                jFrame.add(jPanels[i]);
            }
            JButton jButton1 = new JButton("提交");
            jButton1.setBounds(270, 250, 100, 50);
            jButton1.setForeground(Color.MAGENTA);
            jFrame.add(jButton1);
            jFrame.setVisible(true);

            //给提交添加监听事件，将内容提交到数据库中
            jButton1.addActionListener(e -> {
                PIMTodo pimTodo = new PIMTodo();
                PIMAppointment pimAppointment = new PIMAppointment();
                if (flag == 0) {
                    pimTodo.setOwer(((JTextField) jPanels[0].getComponents()[1]).getText());
                    pimTodo.setPriority(((JTextField) jPanels[1].getComponents()[1]).getText());
                    pimTodo.setTodoItem(((JTextField) jPanels[2].getComponents()[1]).getText());
                    pimTodo.setDate(DateUtil.fromStringToDate(((JTextField) jPanels[3].getComponents()[1]).getText()));
                    //保存到数据库中
                    pimTodoService.addPIMTodo(pimTodo);
                    int status = JOptionPane.showConfirmDialog(jFrame, "保存成功");

                    //关闭该frame
                    jFrame.dispose();
                } else {
                    pimAppointment.setOwer(((JTextField) jPanels[0].getComponents()[1]).getText());
                    pimAppointment.setPriority(((JTextField) jPanels[1].getComponents()[1]).getText());
                    pimAppointment.setDiscription(((JTextField) jPanels[2].getComponents()[1]).getText());
                    pimAppointment.setDate(DateUtil.fromStringToDate(((JTextField) jPanels[3].getComponents()[1]).getText()));
                    //保存到数据库中
                }
            });
        });

    }

    /**
     * 创建一系列的Jpanel,用来填充JFrame
     * @param inputText
     * @returny
     */
    public static JPanel fillFrame(String inputText,int x,int y) {
        JLabel jable1 = new JLabel(inputText);
        JTextField jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(130, 30));
        JPanel jPanel = new JPanel();
        jPanel.setBounds(x, y, 600, 50);
        jPanel.setBackground(Color.getHSBColor(0,255,255));
        jPanel.add(jable1, BorderLayout.WEST);
        jPanel.add(jTextField, BorderLayout.CENTER);
        return jPanel;
    }

    public static void repsonseAddButton(String message)
    {
        JFrame jFrame = new JFrame(message);
        jFrame.setBounds(300, 200, 600, 400);
        jFrame.setLayout(null);
        JPanel[] jPanels = new JPanel[4];
        jPanels[0] = fillFrame("请输入拥有者的名称：", 10, 10);
        jPanels[1] = fillFrame("请输入item的priority: ", 10, 60);
        jPanels[2] = fillFrame("请输入item的内容: ", 10, 110);
        jPanels[3] = fillFrame("请输入item的日期(MM/dd/yyyy): ", 10, 160);

        for (int i = 0; i < jPanels.length; i++) {
            jFrame.add(jPanels[i]);
        }
        JButton jButton1 = new JButton("提交");
        jButton1.setBounds(270, 250, 100, 50);
        jButton1.setForeground(Color.PINK);
        jFrame.add(jButton1);
        jFrame.setVisible(true);

        //给提交添加监听事件，将内容提交到数据库中
        jButton1.addActionListener(e -> {
            PIMTodo pimTodo = new PIMTodo();
            PIMAppointment pimAppointment = new PIMAppointment();
            if (message.equalsIgnoreCase("pimtodo")) {
                pimTodo.setOwer(((JTextField) jPanels[0].getComponents()[1]).getText());
                pimTodo.setPriority(((JTextField) jPanels[1].getComponents()[1]).getText());
                pimTodo.setTodoItem(((JTextField) jPanels[2].getComponents()[1]).getText());
                pimTodo.setDate(DateUtil.fromStringToDate(((JTextField) jPanels[3].getComponents()[1]).getText()));
                //保存到数据库中
                pimTodoService.addPIMTodo(pimTodo);
                int status = JOptionPane.showConfirmDialog(jFrame, "保存成功");

                //关闭该frame
                jFrame.dispose();
            } else {
                pimAppointment.setOwer(((JTextField) jPanels[0].getComponents()[1]).getText());
                pimAppointment.setPriority(((JTextField) jPanels[1].getComponents()[1]).getText());
                pimAppointment.setDiscription(((JTextField) jPanels[2].getComponents()[1]).getText());
                pimAppointment.setDate(DateUtil.fromStringToDate(((JTextField) jPanels[3].getComponents()[1]).getText()));
                //保存到数据库中
                pimAppointmentService.addPIMAppointment(pimAppointment);
                JOptionPane.showConfirmDialog(jFrame,"保存成功");
            }
        });
    }
}
