package HW4;

import HW2.Part3.Cal;

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
    }
}
