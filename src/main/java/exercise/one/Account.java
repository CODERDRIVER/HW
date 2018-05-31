package exercise.one;

/**
 * @Description 账户类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/24 17:43
 */
public class Account {

    //用户的名称
    private String name;
    //用户的账户余额
    private double balance;

    public Account()
    {

    }
    public Account(double balance) {
        if (balance<0)
        {
            throw new AccountException("balance cannot be negative");
        }else{
            this.balance = balance;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
