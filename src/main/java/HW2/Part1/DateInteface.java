package HW2.Part1;

import java.util.Date;

/**
 * @Description 日期接口
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/25 16:32
 */
public interface DateInteface {

    //将string转换成日期
    public Date formatDate(String string);
    //将date转换成string
    public String formatString(Date date);

}
