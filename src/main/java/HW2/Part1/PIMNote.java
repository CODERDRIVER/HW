package HW2.Part1;

/**
 * @Description Note Item
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/25 15:37
 */
public class PIMNote extends PIMEntity{

    private  String note;   //note的内容

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public void fromString(String s) {
        String[] strings = s.split(";");
        this.note = strings[0];
        this.setPriority(strings[1]);
    }

    @Override
    public String toString() {
        return "Note "+getPriority()+note;
    }
}
