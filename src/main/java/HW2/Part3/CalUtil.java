package HW2.Part3;

/**
 * @Description 工具类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/30 17:23
 */
public class CalUtil {

    /**
     * 判断是否是闰年
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year)
    {
        if(year%100==0)
        {
            if(year%400==0)
            {
                return true;
            }else {
                return false;
            }
        }else{
            if(year%4==0)
            {
                return true;
            }else{
                return false;
            }
        }
    }
}
