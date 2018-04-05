package Optional;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description 计算器GUI
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/4/4 13:19
 */
public class CalGUI {

    public  static  StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Caculator");
        jFrame.setBounds(500,200,250,300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //点击叉号即退出该线程
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(200,50));    //设置Jpanel的大小
        jPanel2.setPreferredSize(new Dimension(250,250));
        jPanel1.setBackground(Color.GREEN);
        jPanel1.setLayout(new BorderLayout());
        JTextField jTextField = new JTextField("0");
        jPanel1.add(jTextField,BorderLayout.CENTER);  //设置JTextField居中显示
        jPanel2.setBackground(Color.cyan);
        jPanel2.setLayout(new GridLayout(4,4,2,2));

        JButton[] jButtons = new JButton[16];
        /**
         * 布局数字
         */

        int i;
       for(i=0;i<10;i++)
       {
            jButtons[i] = new JButton(i+"");
       }
       jButtons[i++] = new JButton("+");
       jButtons[i++] = new JButton("-");
       jButtons[i++] = new JButton("*");
        jButtons[i++] = new JButton("/");
        jButtons[i++] = new JButton("=");
        jButtons[i++] = new JButton("CLR");
       for(int j=7;j<=9;j++)
       {
           jPanel2.add(jButtons[j]);
       }
       jPanel2.add(jButtons[10]);
        for(int j=4;j<=6;j++)
        {
            jPanel2.add(jButtons[j]);
        }
        jPanel2.add(jButtons[11]);
        for(int j=1;j<=3;j++)
        {
            jPanel2.add(jButtons[j]);
        }
        jPanel2.add(jButtons[12]);
        jPanel2.add(jButtons[0]);
        jPanel2.add(jButtons[15]);
        jPanel2.add(jButtons[14]);
        jPanel2.add(jButtons[13]);
        for(int j=0;j<14;j++)
        {
            jButtons[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton jButton = (JButton)e.getSource();
                    jButton.setBackground(Color.GRAY);
                    stringBuilder.append(jButton.getText());
                    jTextField.setText(stringBuilder.toString());
                }
            });
        }

        /**
         * 等号的监听器
         */
        jButtons[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField.setText(getResult(stringBuilder.toString())+"".trim());
                stringBuilder = new StringBuilder();
            }
        });

        /**
         * CLR清除键的监听器
         */
        jButtons[15].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField.setText("0");
                stringBuilder = new StringBuilder();
            }
        });
        jFrame.setLayout(new BorderLayout());
       jFrame.add(jPanel1,BorderLayout.NORTH);
       jFrame.add(jPanel2,BorderLayout.CENTER);
       jFrame.setVisible(true);
    }
    public static  int getResult(String string)
    {
        Stack<Character> stack = new Stack<>(); // 用来存储符号
        List<String> list = new ArrayList<>();  //用来存储后缀表达式
        for(int i=0;i<string.length();i++)
        {
            String temp = "";
            while(i<string.length()&&string.charAt(i)>='0'&&string.charAt(i)<='9')
            {
                temp =temp+string.charAt(i);
                i++;
            }
            if(!temp.equals(""))
            {
                list.add(temp);
                --i;
            }else{
                //说明为符号
                if(stack.isEmpty())
                {
                    stack.push(string.charAt(i));
                }else if(string.charAt(i)=='+'||string.charAt(i)=='-'||string.charAt(i)=='*'||string.charAt(i)=='/')
                {
                    //判断当前字符与栈顶字符的优先级
                    if(isUpperCharacter(string.charAt(i),stack.peek()))
                    {
                        stack.push(string.charAt(i));
                    }else{
                        while(!stack.isEmpty()&&!isUpperCharacter(string.charAt(i),stack.peek()))
                        {
                            list.add(stack.pop()+"");
                        }
                    }
                }
            }


        }
        while(!stack.isEmpty())
        {
            list.add(stack.pop()+"");
        }
        Stack<Integer> stack1 = new Stack<>();
        for(int j=0;j<list.size();j++)
        {
            String s = list.get(j);
            if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/"))
            {
                int n1 = stack1.pop();
                int n2 = stack1.pop();
                char[] chars = list.get(j).toCharArray();
                stack1.push(operate(n1,n2,chars[0]));
            }else{
                stack1.push(Integer.parseInt(s));
            }
        }

        return stack1.pop();
    }

    /**
     *
     * @param ch1 string字符串中的第i个字符
     * @param peek  栈顶的字符
     * @return
     */
    public static boolean isUpperCharacter(char ch1,char peek)
    {
        if((ch1=='+'||ch1=='-')&&(peek=='+'||peek=='-'))
        {
            return false;
        }
        else if((ch1=='*'||ch1=='/')&&(peek=='+'||peek=='-'))
        {
            return true;
        }else if((ch1=='*'||ch1=='/')&&(peek=='*'||peek=='/'))
        {
            return false;
        }else if((ch1=='+'||ch1=='-')&&(peek=='+'||peek=='-'))
        {
            return false;
        }
        return false;
    }

    /**
     * 对两个数进行  ch操作
     * @param n1
     * @param n2
     * @param ch
     * @return
     */
    public static  int operate(int n1,int n2,char ch)
    {
        switch (ch)
        {
            case '+':return n1+n2;
            case '-':return n1-n2;
            case '*':return n1*n2;
            case '/':
                if(n2==0){
                    System.out.println("除数不能为0");
                    break;
                }else{
                    return n1/n2;
                }
                default:return 0;
        }
        return 0;
    }
}