package HW4;

import HW2.Part3.Cal;

import java.awt.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/4/9 16:21
 */
public class CalendarUtil {

    /**
     * 以下是15年的阴历信息
     * 每年总共有5个15位 总计20个bits
     * 用0x04bd8举例：
     *  最后四位,即8代表改年闰月的月份，为0则没有闰月
     *  前四位，即0，只有当改年有闰年的时候才有意义，即0表示闰月29天，1表示闰月30天
     *  中间呢2位，代表每个月的天数
     *
     *  阳历的1900年1月31日是阴历的1900年的正月初一
     */
    public static  final int MIN_YEAR = 1900;  //最小年份
    public static final  int MAX_YEAR = 2049;  //最大年份
    public static final String STARTOFDATE = "19000130";
    public  static  boolean isLeapYear;
    public static  final String CHINESENAME[] = {
            "初一","初二","初三","初四","初五","初六","初七","初八","初九","初十"
            ,"十一","十二","十三","十四","十五","十六","十七","十八","十九","二十"
            ,"二十一","二十二","二十三","二十四","二十五","二十六","二十七","二十八"
            ,"二十九","三十"
    };
    public static  final  String[] MONTHNAME={
            "正月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","腊月"
    };

    public final static int[] LUNAR_INFO = {
            0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,
            0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,
            0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,
            0x06566,0x0d4a0,0x0ea50,0x06e95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,
            0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,
            0x06ca0,0x0b550,0x15355,0x04da0,0x0a5d0,0x14573,0x052d0,0x0a9a8,0x0e950,0x06aa0,
            0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,
            0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b5a0,0x195a6,
            0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,
            0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x055c0,0x0ab60,0x096d5,0x092e0,
            0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,
            0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,
            0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,
            0x05aa0,0x076a3,0x096d0,0x04bd7,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,
            0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0
    };

    //平年每个月的天数
    public final static int[] DAYS = {
            31,28,31,30,31,30,31,31,30,31,30,31
    };


    /**
     * 获得该年的月份
     * @param calendar
     * @return
     */
    public static int getMonthOfYear(Calendar calendar)
    {
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获得当前年份
     * @param calendar
     * @return
     */
    public static int getYear(Calendar calendar)
    {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 根据阳历获得阴历
     * @param solarDate
     * @return
     * @throws Exception
     */
    public static String solarToLunar(String solarDate) {
        int i;
        int temp = 0;
        int lunarYear;
        int lunarMonth; //农历月份
        int lunarDay; //农历当月第几天
        boolean leapMonthFlag =false;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date myDate = null;
        Date startDate = null;
        try {
            myDate = formatter.parse(solarDate);
            startDate = formatter.parse(STARTOFDATE);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int offset = daysBetween(startDate,myDate);

        for (i = MIN_YEAR; i <= MAX_YEAR; i++){
            temp = getYearDays(i);  //求当年农历年天数
            if (offset - temp < 1){
                break;
            }else{
                offset -= temp;
            }
        }
        lunarYear = i;

        int leapMonth = getLeapMonth(lunarYear);//计算该年闰哪个月
        //设定当年是否有闰月
        if (leapMonth > 0){
            isLeapYear = true;
        }else{
            isLeapYear = false;
        }

        for (i = 1;  i<=12; i++) {
            if(i==leapMonth+1 && isLeapYear){
                temp = getLeapMonthDays(lunarYear);
                isLeapYear = false;
                leapMonthFlag = true;
                i--;
            }else{
                temp = getMonthDays(lunarYear, i);
            }
            offset -= temp;
            if(offset<=0){
                break;
            }
        }

        offset += temp;
        lunarMonth = i;
        lunarDay = offset;

        //"阴历："+lunarYear+"年"+(leapMonthFlag&(lunarMonth==leapMonth)?"闰":"")+
        return MONTHNAME[lunarMonth-1]+CHINESENAME[lunarDay-1];
    }

    /**
     * 计算两个阳历日期相差的天数。
     * @param startDate 开始时间
     * @param endDate 截至时间
     * @return (int)天数
     * @author liu 2017-3-2
     */
    private static int daysBetween(Date startDate, Date endDate) {
        int days = 0;
        //将转换的两个时间对象转换成Calendar对象
        Calendar can1 = Calendar.getInstance();
        can1.setTime(startDate);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(endDate);
        //拿出两个年份
        int year1 = can1.get(Calendar.YEAR);
        int year2 = can2.get(Calendar.YEAR);
        //天数

        Calendar can = null;
        //如果can1 < can2
        //减去小的时间在这一年已经过了的天数
        //加上大的时间已过的天数
        if(can1.before(can2)){
            days -= can1.get(Calendar.DAY_OF_YEAR);
            days += can2.get(Calendar.DAY_OF_YEAR);
            can = can1;
        }else{
            days -= can2.get(Calendar.DAY_OF_YEAR);
            days += can1.get(Calendar.DAY_OF_YEAR);
            can = can2;
        }
        for (int i = 0; i < Math.abs(year2-year1); i++) {
            //获取小的时间当前年的总天数
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
            //再计算下一年。
            can.add(Calendar.YEAR, 1);
        }
        return days;
    }

    /**
     * 计算阴历{@code lunarYeay}年{@code month}月的天数
     * @param lunarYeay 阴历年
     * @param month 阴历月
     * @return (int)该月天数
     * @throws Exception
     * @author liu 2015-1-5
     */
    private static int getMonthDays(int lunarYeay, int month)  {
        if ((month > 31) || (month < 0)) {
//            throw(new Exception("月份有错！"));
            System.out.println("月份有错");
            return -1;
        }
        // 0X0FFFF[0000 {1111 1111 1111} 1111]中间12位代表12个月，1为大月，0为小月
        int bit = 1 << (16-month);
        if(((LUNAR_INFO[lunarYeay - 1900] & 0x0FFFF)&bit)==0){
            return 29;
        }else {
            return 30;
        }
    }


    /**
     * 计算阴历{@code year}年闰月多少天
     * @param year 阴历年
     * @return (int)天数
     * @author liu 2015-1-5
     */
    private static int getLeapMonthDays(int year) {
        if(getLeapMonth(year)!=0){
            if((LUNAR_INFO[year - 1900] & 0xf0000)==0){
                return 29;
            }else {
                return 30;
            }
        }else{
            return 0;
        }
    }


    /**
     * 计算阴历{@code year}年闰月多少天
     * @param year 阴历年
     * @return (int)天数
     * @author liu 2015-1-5
     */
    private static int getLeapMonth(int year) {
        return (int) (LUNAR_INFO[year - 1900] & 0xf);
    }
    /**
     * 计算阴历{@code year}年的总天数
     * @param year 阴历年
     * @return (int)总天数
     * @author liu 2015-1-5
     */
    private static int getYearDays(int year) {
        int sum = 29*12;
        for(int i=0x8000;i>=0x8;i>>=1){
            if((LUNAR_INFO[year-1900]&0xfff0&i)!=0){
                sum++;
            }
        }
        return sum+getLeapMonthDays(year);
    }

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

    /**
     * 获得上一个月的天数
     * @param calendar
     * @return
     */
    public static int getDaysOfPreMonth(Calendar calendar)
    {
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH);
        int countDay = 0;
        if (currMonth!=0&&currMonth!=11){
            //判断当前年是否是闰年
            if (CalendarUtil.isLeapYear(currYear))
            {
                //是否是二月
                countDay=currMonth==1?CalendarUtil.DAYS[currMonth]+1:CalendarUtil.DAYS[currMonth];
            }else{
                countDay = CalendarUtil.DAYS[currMonth];
            }
        }else if (currMonth==0){
            currMonth = 11;
            countDay = CalendarUtil.DAYS[currMonth];
        }else if (currMonth==11)
        {
            currMonth = 1;
            countDay = CalendarUtil.DAYS[currMonth];
        }
        return countDay;
    }

    /**
     * 获得下一个月的天数
     * @param calendar
     * @return
     */
    public static int getDaysOfNextMonth(Calendar calendar)
    {
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH);
        int countDay = 0;
        if (currMonth!=11){
            //判断当前年是否是闰年
            if (CalendarUtil.isLeapYear(currYear))
            {
                //是否是二月
                countDay=currMonth==1?CalendarUtil.DAYS[currMonth]+1:CalendarUtil.DAYS[currMonth];
            }else{
                countDay = CalendarUtil.DAYS[currMonth];
            }
        }else if (currMonth==11){
            currMonth = 0;
            countDay = CalendarUtil.DAYS[currMonth];
        }
        return countDay;
    }

    /**
     * 获得某月第一天是周几
     *
     * 1 表示周末
     * 2 表示周一
     * @param calendar
     * @return
     */
    public  static  int getDateOfMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1,0,0);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 判断是否需要扩充
     * 当某一月的第一天是周六，并且该月不是二月
     * 或者第一天是周五，并且该月有31天
     * @param calendar
     * @return
     */
    public static boolean isAdapter(Calendar calendar)
    {
        int daysOfMonth = CalendarUtil.DAYS[calendar.get(Calendar.MONTH)];
        int dateOfMonth = CalendarUtil.getDateOfMonth(calendar);
        if ((daysOfMonth>=30&&dateOfMonth==7)||(daysOfMonth==31&&dateOfMonth==6))
        {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 将timestamp转成Date类型
     */
    @SuppressWarnings("all")
    public static  Date timeStamp2Date(Timestamp timestamp)
    {
        return new Date(timestamp.getYear(),timestamp.getMonth(),timestamp.getDay(),timestamp.getHours(),timestamp.getMinutes(),timestamp.getSeconds());
    }
}
