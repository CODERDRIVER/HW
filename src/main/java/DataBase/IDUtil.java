package DataBase;

import java.util.Random;

/**
 * @Description id生成工具
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/13 14:36
 */
public class IDUtil {


    public static long generateId()
    {
        long milis = System.currentTimeMillis();
        return milis;
    }
}
