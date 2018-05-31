package exercise.one;

import java.util.*;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/24 19:12
 */
public class BankAccount extends  Account{

    private static  int size = 0;   //存储账户的大小

    public Map<String,Double> map = new HashMap<>();

    public BankAccount()
    {

    }

    public BankAccount(double balance)  {
        super(balance);
    }

    //用来存每个账户所对应的操作记录
    public  Map<String,Queue<String>> transactions = new HashMap<>();
    /**
     * 存款
     * @param balance
     */
    public void deposit(String name,double balance)
    {
        if (map.containsKey(name))
        {
            map.put(name,map.get(name)+balance);
            if (transactions.containsKey(name))
            {
                //该账户已经存储了
                if (transactions.get(name).size()>7)
                {
                    transactions.get(name).poll();
                }

                transactions.get(name).add(name+"deposit "+balance+"元");

            }else{
                //还没有该账户的记录
                Queue<String> queue = new LinkedList<>();
                queue.add(name+"deposit "+balance+"元");
                transactions.put(name,queue);
            }
        }else{
            throw new AccountException(name+"该用户不存在");
        }
    }

    /**
     * 取款
     * @param balance
     * @return
     */
    public boolean withdrawal(String name,double balance)
    {
        //判断该用户是否存在
        if (map.containsKey(name))
        {
            //存在
            //判断该用户的balance金额
            if (map.get(name)>=balance)
            {
                //够
                //取款
                map.put(name,map.get(name)-balance);

                //添加记录
                if (transactions.containsKey(name))
                {
                    if (transactions.get(name).size()>7)
                    {
                        transactions.get(name).poll();
                    }
                    transactions.get(name).add(name+" withdrawl "+balance+"元");
                }else{
                    //还没有该账户的记录
                    Queue<String> queue = new LinkedList<>();
                    queue.add(name+"withdrawl "+balance+"元");
                    transactions.put(name,queue);
                }
            }else{
                //账户余额不足
                throw new AccountException("账户余额不足");
            }
            return true;
        }else{
            //不存在
            throw  new AccountException(name+"该用户不存在");
        }
    }

    /**
     * 更改用户的名称
     * @param newName
     * @return
     */
    public String changeName(String oldName,String newName)
    {
        if (map.containsKey(oldName))
        {
            double b = map.get(oldName);
            map.remove(oldName);
            map.put(newName,b);
            return newName;
        }else{
            throw  new AccountException("该用户不存在");
        }
    }

    public void addAccount(String name,double balance)
    {
        size++;
        map.put(name,balance);
    }

    public  Map<String, Double> getMap() {
        return map;
    }

    public void setMap(Map<String, Double> map) {
        this.map = map;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        BankAccount.size = size;
    }

    public Map<String, Queue<String>> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<String, Queue<String>> transactions) {
        this.transactions = transactions;
    }
}
