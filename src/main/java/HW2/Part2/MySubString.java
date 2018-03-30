package HW2.Part2;

/**
 * @Description Create a class named Substring that will expect the first command line argument to be a string,
 * and the second two command line arguments to be integers,the first will be used as an index and the second as a length
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/30 16:27
 */
public class MySubString {

    public static void main(String[] args) {
        if(args.length!=3)
        {
            System.out.println("the input is invalid");
            return ;
        }
        String string = args[0];
        int start = Integer.parseInt(args[1]);
        int len = Integer.parseInt(args[2]);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=start;i<start+len;i++)
        {
            stringBuilder.append(string.charAt(i));
        }
        System.out.println(stringBuilder.toString());
    }
}
