package HW2;

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
        DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        Date date = null;
        try {
            dateFormat.setLenient(false);
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            System.out.println("日期格式不正确");
        }
        return date;
    }
    //验证日期格式
    public static boolean validateDate(String str)
    {
        DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
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
}
