package HW2.Part3;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description Write a java program named cal (the main() should be in a class named "cal") that will print out to standard output the calendar for any month.
 * Your program should look at the command line arguments for a numeric month and year, and print the calendar for that month in the format displayed below.
 * If there are no command line arguments, or if the command line arguments are not a valid month and year, your program must print the calendar for the current month
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/30 16:42
 */
@SuppressWarnings("all")
public class Cal {

    public static void main(String[] args) {
        int month = Integer.parseInt(args[0]);
        int year = Integer.parseInt(args[1]);
        Date date = null;
        if(month<=0||year<1900)
        {
            date = new Date();
        }

        //每月的天数,默认是平年
        int daysOfMonth[] = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        String eNameOfMonth[] = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};

        //计算是平年还是闰年
        if(CalUtil.isLeapYear(year))
        {
            //如果是闰年，二月加一天
            daysOfMonth[1]++;
        }
        /**
         * 判断该月的第一天是周几
         *
         */
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month-1,1,0,0,0);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Su\tMo\tTu\tWe\tTh\tFr\tSa");
        int j,num=1;
        for(j=0;j<dayOfWeek-1;j++)
        {
            System.out.print(" \t");
        }
        int i=0;
        while(i<daysOfMonth[dayOfMonth-1])
        {
            if(j==7)
            {
                System.out.println();
                j=0;
            }
            System.out.print((num++)+"\t");
            i++;
            j++;
        }
    }
}
