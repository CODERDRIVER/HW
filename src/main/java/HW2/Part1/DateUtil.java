package HW2.Part1;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 日期转换工具类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/25 16:13
 */
public class DateUtil {

    public static Date fromStringToDate(String str)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            dateFormat.setLenient(false);
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            System.out.println("日期格式不正确");
        }
        return date;
    }
    public static String fromDateToString(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date);
    }
    //验证日期格式
    public static boolean validateDate(String str)
    {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            //设置为不严格
            dateFormat.setLenient(false);
            dateFormat.parse(str);
            return true;
        } catch (ParseException e) {
            System.out.println("日期格式不正确");
            return false;
        }
    }

    /**
     * date转TimeStamp
     * @param date
     * @return
     */
    public static Timestamp dateToTimeStamp(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String string = simpleDateFormat.format(date);
        return Timestamp.valueOf(string);
    }

}
