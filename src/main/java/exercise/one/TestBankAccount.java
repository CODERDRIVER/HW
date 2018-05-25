package exercise.one;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/24 19:45
 */
public class TestBankAccount {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.addAccount("lxy",100.0);
        bankAccount.addAccount("sq",127.0);
        bankAccount.addAccount("wgn",998.0);
        bankAccount.addAccount("swr",345.0);
        System.out.println(BankAccount.getSize());
        System.out.println(bankAccount.map.size());
        bankAccount.deposit("lxy",23);
        System.out.println(bankAccount.map.get("lxy"));
    }
}
