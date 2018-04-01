package HW2.Part1;

import java.util.Date;

/**
 * @Description To Do Item
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/25 15:37
 */
public class PIMTodo  extends  PIMEntity implements DateInteface {

    private Date date = null;

    private  String dateString;     //字符串日期

    private  String todoItem;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(String todoItem) {
        this.todoItem = todoItem;
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
            this.dateString = formatString(new Date(strings[0]));
        }
        this.todoItem = strings[1];
        this.setPriority(strings[2]);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TODO "+getPriority()+" ");
        if(date!=null)
        {
            stringBuilder.append(date.toString());
        }
        stringBuilder.append(" "+todoItem);
        return stringBuilder.toString();
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
