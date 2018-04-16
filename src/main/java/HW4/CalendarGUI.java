package HW4;

import HW2.Part3.Cal;
import com.sun.org.apache.bcel.internal.generic.FADD;
import sun.util.resources.cldr.aa.CalendarData_aa_DJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Set;

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
//        mainFrame.add(jPanel,BorderLayout.NORTH);
//        mainFrameAdapter.add(jPanel,BorderLayout.NORTH);
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
                    mainFrameAdapter.setVisible(true);
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
        dayNamePanel.setSize(1000,50);
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
                jLabel.setForeground(Color.MAGENTA);
                buttons[i].add(jLabel);
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

}
