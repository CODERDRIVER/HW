package HW1.CRS;

import java.util.*;

/**
 * @Description CRS程序的入口
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/21 23:44
 */
public class CRS {

    public static void main(String[] args) {
        test(args);
//        if(args.length<2)
//        {
//            System.out.println("The Student must select one course");
//        }else if(args.length>3){
//            System.out.println("The system only has two courses");
//        }else{
//            String stuId = args[0];
//            if(args.length==2){
//                Course course = new Course(args[1]);
//                new Student(stuId,course);
//            }else{
//                List<Course> set = new ArrayList<>();
//                set.add(new Course(args[1]));
//                set.add(new Course(args[2]));
//                new Student(stuId,set);
//            }
//        }

    }

    public static void test(String[] args)
    {
        List<Course> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if(args.length>=2)
        {
            String stuId = args[0];
            for(int i=1;i<args.length;i++)
            {
                list.add(new Course(args[i]));
            }
            Student student = new Student(stuId,list);

            sb.append(stuId+" select ");
            for(int i=0;i<list.size();i++)
            {
                Course course = list.get(0);
                sb.append(course.getCourseName()+" with books ");
                List<Book> list1 = course.getMap().get(course.getCourseName());
                for (int j=0;j<list1.size();j++)
                {
                    Book book = list1.get(j);
                    if(j+1<list1.size())
                    {
                        sb.append(book.getBookName()+", and ");
                    }else{
                        sb.append(book.getBookName());
                    }
                }
                if(i+1<list.size())
                {
                    sb.append(" ; And ");
                }
            }
        }
        System.out.println(sb.toString());
    }
}
