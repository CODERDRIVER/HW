package HW3;

import java.util.Date;

/**
 * @Description 工具类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/22 16:05
 */
@SuppressWarnings("all")
public class PIMCollectionUtil {

    //判断日期是否相等
    public static boolean isDateEqual(Date src,Date dest)
    {
        if (src.getYear()==dest.getYear()&&src.getMonth()==dest.getMonth()&&src.getDate()==dest.getDate())
        {
            return true;
        }
        return false;
    }
}
