package HW1;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: ${DATE} ${TIME}
 */
public class SumOfArgs {

    public static void main(String[] args) {
        int sum = 0;
        for(int i=0;i<args.length;i++)
        {
            if(args[i].matches("\\d"))
            {
                sum = sum+Integer.parseInt(args[i]);
            }
        }
        System.out.println(sum);
    }
}
