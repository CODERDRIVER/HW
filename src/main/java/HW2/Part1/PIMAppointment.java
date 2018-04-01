package HW2.Part1;

import java.util.Date;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/25 15:37
 */
public class PIMAppointment  extends  PIMEntity implements DateInteface {

    private Date date = null;
    private String dateString;
    private String discription; //appointment的具体描述

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public void fromString(String s) {
        String[] strings = s.split(";");
        if(strings[0].contains("/")){
            this.date = formatDate(strings[0]);
        }else{
            this.dateString = formatString(formatDate(strings[0]));
        }
        this.discription = strings[1];
        this.setPriority(strings[2]);
    }

    @Override
    public String toString() {

        return "Appointment "+getPriority()+date.toString()+" "+getDiscription();
    }

    @Override
    public Date formatDate(String string) {
        return DateUtil.fromStringToDate(string);
    }
    @Override
    public String formatString(Date date) {
        return DateUtil.fromDateToString(date);
    }
}
