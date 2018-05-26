package HW4;

import HW2.Part1.DateUtil;
import HW4.model.PIMAppointment;
import HW4.service.PIMTodoService;
import HW4.service.impl.EntityServiceImpl;
import HW4.service.impl.PIMTodoServiceImpl;
import com.sun.org.apache.bcel.internal.generic.FADD;
import oracle.jvm.hotspot.jfr.JFR;
import sun.util.resources.cldr.aa.CalendarData_aa_DJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/4/5 16:55
 */
public class CalendarGUI {

    private static  final String PREURL = "/Users/mac/Downloads/JavaCode/HomeWork/src/main/java/HW4/images/pre.png";
    private static  final String NEXTURL = "/Users/mac/Downloads/JavaCode/HomeWork/src/main/java/HW4/images/next.png";

    private static Calendar calendar = Calendar.getInstance();
    private static int currYear = calendar.get(Calendar.YEAR);  //当前的年份
    private static int currMonth = calendar.get(Calendar.MONTH)+1;        //当前的月份

    private JButton[] jButtons = null;
    private JButton[] jButtonsAdapter = null;

    public EntityServiceImpl entityService = new EntityServiceImpl();
    public static void main(String[] args) {
        CalendarGUI calendarGUI = new CalendarGUI();
        calendar.set(currYear,currMonth-1,1,0,0);
        JFrame mainFrame = new JFrame("Calendar");
        JFrame mainFrameAdapter = new JFrame("Calendar");
        calendarGUI.init(mainFrame);
        calendarGUI.init(mainFrameAdapter);
        mainFrame.setLayout(new BorderLayout());
        mainFrameAdapter.setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.CYAN);
        jPanel.setSize(200,50);
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton preButton = new JButton(new ImageIcon(PREURL));
        JButton nextButton = new JButton(new ImageIcon(NEXTURL));
        JTextField jTextField = new JTextField(currYear+"年"+currMonth+"月");
        jTextField.setHorizontalAlignment(JTextField.CENTER);   //设置文字居中显示
        jTextField.setPreferredSize(new Dimension(100,26));
        jPanel.add(preButton);
        jPanel.add(nextButton);
        jPanel.add(jTextField);
        //添加一个信息管理的按钮
        String[] items = new String[]{"信息管理","pimTodo","pimNote","pimAppointment","pimContact","new"};
        JComboBox jComboBox = new JComboBox(items);

//        JButton pimManager = new JButton("信息管理");
//        jPanel.add(pimManager);
        jPanel.add(jComboBox);


        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()== ItemEvent.SELECTED)
                {
                    String itemName = (String)e.getItem();
                    if (itemName.equalsIgnoreCase("信息管理"))
                    {
//                        jFrame.setVisible(false);
                    }
                    else if (itemName.equalsIgnoreCase("pimTodo"))
                    {
                        new PersonalItemManager(itemName).showPimtodo();
                    }else if (itemName.equalsIgnoreCase("pimNote"))
                    {
                        new PersonalItemManager(itemName).showPimNote();
                    }else if (itemName.equalsIgnoreCase("pimAppointment"))
                    {
                        new PersonalItemManager(itemName).showPimAppointment();
                    }else if(itemName.equalsIgnoreCase("pimContact"))
                    {
                        new PersonalItemManager(itemName).showPimContact();
                    }else if (itemName.equalsIgnoreCase("new"))
                    {
                        JFrame jFrame = ItemUtil.createItem();
                        jFrame.setLayout(new FlowLayout());
                        JPanel jPanel1 = new JPanel(new BorderLayout());
                        JLabel jLabel = new JLabel("请输入item的名称:");
                        JTextField jTextField1 = new JTextField();
                        jPanel1.add(jLabel,BorderLayout.CENTER);
                        jPanel1.add(jTextField1,BorderLayout.SOUTH);
                        JButton jButton = new JButton("点击添加item属性");
                        jFrame.add(jPanel1);
                        jFrame.add(jButton);


                        /**
                         * jButton添加监听事件
                         *
                         */
                        jButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                               String value =  JOptionPane.showInputDialog("请输入属性的个数");
                                int count = Integer.parseInt(value);
                                JFrame jFrame1  = new JFrame("属性添加");
                                jFrame1.setVisible(true);
                                jFrame1.setBounds(310,310,300,300);
                                jFrame1.setLayout(new FlowLayout());
                                for (int i=0;i<count;i++)
                                {
                                    JPanel temp = new JPanel(new BorderLayout());
                                    JLabel jLabel1 = new JLabel("请输入属性"+(i+1)+"的名称");
                                    JTextField jTextField2 = new JTextField();
                                    temp.add(jLabel1,BorderLayout.CENTER);
                                    temp.add(jTextField2,BorderLayout.SOUTH);
                                    jFrame1.add(temp);
                                }
                            }
                        });
                    }
                }
            }
        });
        //添加事件监听按钮
//        pimManager.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFrame jFrame = new JFrame("信息管理");
//                jFrame.setBounds(300,200,400,400);
//                jFrame.setVisible(true);
//                    new PersonalItemManager(jFrame).init();
//            }
//        });
        calendarGUI.jButtons = new JButton[35];
        calendarGUI.jButtonsAdapter = new JButton[42];
        JPanel datePanel = calendarGUI.dayNamePanelAdapter(35,calendarGUI.jButtons);
        JPanel datePanelAdapter = calendarGUI.dayNamePanelAdapter(42,calendarGUI.jButtonsAdapter);
        if (CalendarUtil.isAdapter(calendar))
        {
            mainFrame.setVisible(false);
            mainFrameAdapter.add(jPanel,BorderLayout.NORTH);
            mainFrameAdapter.add(datePanelAdapter,BorderLayout.CENTER);
        }else{
            mainFrameAdapter.setVisible(false);
            mainFrame.add(jPanel,BorderLayout.NORTH);
            mainFrame.add(datePanel,BorderLayout.CENTER);
        }
        /**
         * preButton添加事件
         */
        preButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currMonth = CalendarUtil.getMonthOfYear(calendar);
                currYear = CalendarUtil.getYear(calendar);
                  if (currMonth!=1)
                  {
                      currMonth = currMonth-1;
                      calendar.set(currYear,currMonth,calendar.get(Calendar.DATE));
                  }else {
                      currMonth = 11;
                      currYear = currYear-1;
                      calendar.set(currYear,currMonth,calendar.get(Calendar.DATE));
                  }
                  jTextField.setText(currYear+"年"+(currMonth+1)+"月");

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                int days = calendar.getActualMaximum(Calendar.DATE);
                /**
                 * 更改日期
                 */
                if (CalendarUtil.isAdapter(calendar))
                {
                    mainFrame.setVisible(false);
                    mainFrameAdapter.setVisible(true);
                    mainFrameAdapter.add(jPanel,BorderLayout.NORTH);
                    mainFrameAdapter.add(datePanelAdapter,BorderLayout.CENTER);
                    calendarGUI.changeButtonText(calendarGUI.jButtonsAdapter,dayOfWeek,days);
                }else{
                    mainFrameAdapter.setVisible(false);
                    mainFrame.setVisible(true);
                    mainFrame.add(jPanel,BorderLayout.NORTH);
                    mainFrame.add(datePanel,BorderLayout.CENTER);
                    calendarGUI.changeButtonText(calendarGUI.jButtons,dayOfWeek,days);
                }

            }
        });

        /**
         * nextButton添加事件
         */
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currMonth = CalendarUtil.getMonthOfYear(calendar);
                currYear = CalendarUtil.getYear(calendar);
                if (currMonth!=12)
                {
                    currMonth = currMonth+1;
                    calendar.set(currYear,currMonth,calendar.get(Calendar.DATE));
                }else {
                    currMonth = 0;
                    currYear = currYear+1;
                    calendar.set(currYear,currMonth,calendar.get(Calendar.DATE));
                }
                jTextField.setText(calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月");


                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                int days = calendar.getActualMaximum(Calendar.DATE);

                /**
                 * 更改日期
                 */
                if (CalendarUtil.isAdapter(calendar))
                {
                    mainFrame.setVisible(false);
                    mainFrameAdapter.setVisible(true);
                    mainFrameAdapter.add(jPanel,BorderLayout.NORTH);
                    mainFrameAdapter.add(datePanelAdapter,BorderLayout.CENTER);
                    calendarGUI.changeButtonText(calendarGUI.jButtonsAdapter,dayOfWeek,days);
                }else{
                    mainFrameAdapter.setVisible(false);
                    mainFrame.setVisible(true);
                    mainFrame.add(jPanel,BorderLayout.NORTH);

                    mainFrame.add(datePanel,BorderLayout.CENTER);
                    calendarGUI.changeButtonText(calendarGUI.jButtons,dayOfWeek,days);
                }
            }
        });


        /**
         * 给每个button添加点击事件
         *
         */
        for (int i=0;i<calendarGUI.jButtons.length;i++)
        {
            calendarGUI.jButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendarGUI.responseJbutton(e,calendarGUI);
                }
            });
        }
        for (int j=0;j<calendarGUI.jButtonsAdapter.length;j++)
        {
            calendarGUI.jButtonsAdapter[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendarGUI.responseJbutton(e,calendarGUI);
                }
            });
        }
    }

    /**
     * 初始化
     * @param jFrame
     */
    public  void init(JFrame jFrame)
    {
        jFrame.setBounds(100,150,1200,500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
         * 测试菜单栏
         */
        //        JMenuBar jMenuBar = new JMenuBar();
//        JMenu jMenu = new JMenu("item");
//        JMenuItem jMenuItem1,jMenuItem2;
//        jMenuItem1 = new JMenuItem("todoItem");
//        jMenuItem2 = new JMenuItem("noteItem");
//        jMenu.add(jMenuItem1);
//        jMenu.add(jMenuItem2);
//        jMenuBar.add(jMenu);
//        jFrame.setJMenuBar(jMenuBar);

    }

    /**
     * 更改JButton的文本内容
     * @param jButtons
     */
    public  void changeButtonText(JButton[] jButtons,int dayOfWeek,int days)
    {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int step = 1;
        for(int i=0;i<jButtons.length;i++)
        {
            if (i>=dayOfWeek-1&&step<=days){
                JLabel jLabel = (JLabel) jButtons[i].getComponents()[0];
                String month = currMonth>10?(currMonth+1)+"":"0"+(currMonth+1);
                String d = day>=10?day+"":"0"+day;
                String solarDate = calendar.get(Calendar.YEAR)+month+d;
                /**
                 * 查询数据库，看当天是否有item
                 */
                jLabel.setText(currMonth+1+"月"+day+"日"+"\t\t"+CalendarUtil.solarToLunar(solarDate));
                jLabel.setFont(new Font("Dialog",1,12));
                jLabel.setForeground(Color.MAGENTA);
                jButtons[i].add(jLabel);
                day++;
                step++;
            }
            else if (i<dayOfWeek)
            {
                /**
                 * 获得上一个月的天数
                 */
                JLabel jLabel = (JLabel) jButtons[i].getComponents()[0];
                int count = CalendarUtil.getDaysOfPreMonth(calendar);
                int start = count-dayOfWeek+i;
                 jLabel.setText("");
                jButtons[i].setForeground(Color.GRAY);
                jButtons[i].add(jLabel);
            }else if (day>days)
            {
                JLabel jLabel = (JLabel) jButtons[i].getComponents()[0];
                jLabel.setText("");
                jButtons[i].add(jLabel);
                jButtons[i].setForeground(Color.GRAY);
            }
        }
    }

    public JPanel dayNamePanelAdapter(int buttonCounts,JButton[] buttons)
    {
        JPanel dayNamePanel = new JPanel();  //内容面板
        dayNamePanel.setSize(1000,300);
        dayNamePanel.setLayout(new BorderLayout(2,2));
        JPanel jPanelUp = new JPanel();
        jPanelUp.setLayout(new GridLayout(1,7,2,2));
        JPanel jPanelDown = new JPanel();
        jPanelDown.setLayout(new GridLayout(buttonCounts/7,7,2,2));
        JLabel[] jLabels = new JLabel[7];
        jLabels[0] = new JLabel("星期日",SwingConstants.CENTER);
        jLabels[1] = new JLabel("星期一",SwingConstants.CENTER);
        jLabels[2] = new JLabel("星期二",SwingConstants.CENTER);
        jLabels[3] = new JLabel("星期三",SwingConstants.CENTER);
        jLabels[4] = new JLabel("星期四",SwingConstants.CENTER);
        jLabels[5] = new JLabel("星期五",SwingConstants.CENTER);
        jLabels[6] = new JLabel("星期六",SwingConstants.CENTER);
        for (int i=0;i<7;i++)
        {
            jPanelUp.add(jLabels[i]);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int days = calendar.getActualMaximum(Calendar.DATE);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int step = 1;
        for(int i=0;i<buttons.length;i++)
        {
            buttons[i] = new JButton();
            if (i>=dayOfWeek-1&&step<=days){

                String month = currMonth>10?currMonth+"":"0"+currMonth;
                String d = day>10?day+"":"0"+day;
                JLabel jLabel = new JLabel(currMonth+"月"+day+"日"+"\t\t"+CalendarUtil.solarToLunar(calendar.get(Calendar.YEAR)+month+d));
                jLabel.setFont(new Font("Dialog",1,12));
                if(currMonth==(new Date().getMonth()+1)&&day==new Date().getDate())
                {
                    jLabel.setForeground(Color.red);
                }else{
                    jLabel.setForeground(Color.MAGENTA);
                }
                buttons[i].add(jLabel);
                Date date = DateUtil.fromStringToDate(month+"/"+d+"/"+calendar.get(Calendar.YEAR));
                day++;
                step++;
            }else if (i<dayOfWeek)
            {
                /**
                 * 获得上一个月的天数
                 */
                int count = CalendarUtil.getDaysOfPreMonth(calendar);
                int start = count-i;
                JLabel jLabeltemp = new JLabel("");
                buttons[i].setForeground(Color.GRAY);
                buttons[i].add(jLabeltemp);
            }else if (day>days)
            {
                JLabel jLabeltemp = new JLabel("");
                buttons[i].add(jLabeltemp);
                buttons[i].setForeground(Color.GRAY);
            }

        }
        for (int i=0;i<buttons.length;i++)
        {
            jPanelDown.add(buttons[i]);
        }
        dayNamePanel.add(jPanelUp,BorderLayout.NORTH);
        dayNamePanel.add(jPanelDown,BorderLayout.CENTER);
        dayNamePanel.setBackground(Color.PINK);

        return dayNamePanel;
    }

    /**
     * 相应button的点击事件
     */
    public void responseJbutton(ActionEvent e,CalendarGUI calendarGUI)
    {
        JButton jButton = (JButton) e.getSource();
        JFrame jFrame = new JFrame("Item");
        jFrame.setVisible(true);
        jFrame.setBounds(300,150,400,400);
        jFrame.setLayout(new BorderLayout());
        JPanel jPanel1 = new JPanel();
        JTextArea jTextArea = new JTextArea();
        jTextArea.setPreferredSize(new Dimension(300,50));
        jTextArea.append(calendarGUI.addTextToButton(jButton));
        jPanel1.add(jTextArea);
        JPanel jPanel2 = new JPanel(new FlowLayout());
        JButton editButton = new JButton("Edit");
        JButton addButton = new JButton("Add");
        jPanel2.add(editButton);
        jPanel2.add(addButton);
        jFrame.add(jPanel1,BorderLayout.CENTER);
        jFrame.add(jPanel2,BorderLayout.SOUTH);


        /**
         * 给addButton添加监听器
         *
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame1 = new JFrame();
                JFrameUtil.initFrame(jFrame1,320,320,300,300);
                jFrame1.setLayout(new BorderLayout());
                JPanel jPanel = new JPanel();
                String items[] = new String[]{"pimtodo","pimAppointment"};
                JComboBox jComboBox = new JComboBox(items);
                jPanel.add(jComboBox);
                JPanel contentJpnal = new JPanel(new BorderLayout());
                JLabel jLabel = new JLabel("请输出item's content");
                JTextArea jTextArea1 = new JTextArea(3,10);
                contentJpnal.add(jLabel,BorderLayout.NORTH);
                contentJpnal.add(jTextArea1,BorderLayout.CENTER);

                //提交面板
                JPanel submitJpanel = new JPanel();
                JButton jButton1 = new JButton("SUBMIT");
                submitJpanel.add(jButton1);
                jFrame1.add(jPanel,BorderLayout.NORTH);
                jFrame1.add(contentJpnal,BorderLayout.CENTER);
                jFrame1.add(submitJpanel,BorderLayout.SOUTH);
            }
        });
    }
    /**
     * 给button弹出的jframe添加文本
     * @param jButton
     * @return
     */
    public String addTextToButton(JButton jButton) {
        String string = ((JLabel) jButton.getComponents()[0]).getText();
        //对数据进行分割，首先根据\t\t分割，得到日期
        String text = string.split("\t\t")[0];
        //根据中文进行分割，得到日期的数字
        String[] strings = text.split("[一-龥]");
        String month = null;
        String day = null;
        if (text==null||text.equals(""))
        {
            return "";
        }
        if (Integer.parseInt(strings[0]) < 10) {
            month = 0 + strings[0];
        }else{
            month = strings[0];
        }
        if (Integer.parseInt(strings[1]) < 10) {
            day = 0 + strings[1];
        }else{
            day = strings[1];
        }
        String date = month + "/" + day + "/" + calendar.get(Calendar.YEAR);

        System.out.println(date);
        List<List> lists = entityService.findEntityByDate(DateUtil.fromStringToDate(date));

        StringBuilder stringBuilder = new StringBuilder();
        if (lists.size()>=1)
        {
            List<Object> objects = lists.get(0);
            stringBuilder.append(objects.size()!=0?"pimTodo: \n":"");
            for (Object o : objects) {
                HW4.model.PIMTodo pimTodo = (HW4.model.PIMTodo) o;
                stringBuilder.append(pimTodo.toString() + "\n");
            }
        }
       if (lists.size()>=2)
       {
           List<Object> objects1= lists.get(1);
           //说明是哪个item
           stringBuilder.append(objects1.size()!=0?"pimAppointment:\n":"");
           for (Object o:objects1)
           {
               PIMAppointment pimAppointment = (PIMAppointment)o;
               stringBuilder.append(pimAppointment.toString()+"\n");
           }
       }

        if (stringBuilder.toString().equals(""))
        {
            return "There are no items";
        }else{
            return stringBuilder.toString();
        }
    }

}
