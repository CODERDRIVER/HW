package exercise.one;

/**
 * @Description 异常抛出不受检异常
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/28 17:38
 */
public class AccountException extends RuntimeException{

    String message  = null;
    public AccountException()
    {
    }

    public AccountException(String message)
    {
        super(message);
        this.message = message;
    }

    public void printException()
    {
        System.out.println(message);
    }
}

