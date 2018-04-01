package HW2;

import java.util.Date;

/**
 * @Description To Do Item
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/25 15:37
 */
public class PIMTodo  extends  PIMEntity implements DateInteface{

    private Date date = null;

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

    @Override
    public void fromString(String s) {
        String[] strings = s.split(";");
        this.date = formatDate(strings[0]);
        this.todoItem = strings[1];
        this.setPriority(strings[2]);
    }

    @Override
    public String toString() {
        return "TODO "+getPriority()+" "+date.toString()+" "+todoItem;
    }

    @Override
    public Date formatDate(String string) {
        return DateUtil.fromStringToDate(string);
    }
}
