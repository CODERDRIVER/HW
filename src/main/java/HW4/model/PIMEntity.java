package HW4.model;

/**
 * @Description  abstract base class
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/25 15:31
 */
public abstract class PIMEntity {
    int id; //数据库主键
    String Priority; // every kind of item has a priority

    String owner;    //拥有者

    int  status; //indicates whether the item is private or public 1表示私有，0表示public

    // default constructor sets priority to "normal"
    PIMEntity() {
        Priority = "normal";
    }

    // priority can be established via this constructor.
    PIMEntity(String priority) {
        Priority =  priority;
    }

    // accessor method for getting the priority string
    public String getPriority() {
        return Priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // method that changes the priority string
    public void setPriority(String p) {
        Priority = p;
    }

    public String getOwer() {
        return owner;
    }

    public void setOwer(String owner) {
        this.owner = owner;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Each PIMEntity needs to be able to set all state information
    // (fields) from a single text string.
    abstract public void fromString(String s);

    // This is actually already defined by the super class
    // Object, but redefined here as abstract to make sure
    // that derived classes actually implement it
    abstract public String toString();
}