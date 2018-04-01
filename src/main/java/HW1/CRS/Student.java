package HW1.CRS;

import java.util.*;

/**
 * @Description Student类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/21 23:45
 */
public class Student {


    private String stuId;   //学号

    private List<Course> list;
    public Student()
    {

    }
//    public Student(String stuId,Course course)
//    {
//        list = new ArrayList<>();
//        this.stuId = stuId;
//        list.add(course);
//        if(course.getCourseName().equals("Java"))
//        {
//            System.out.println(this.stuId+" select "+course.getCourseName()+" with books Thinking in Java, Java 8");
//        }else if(course.getCourseName().equals("WebEngineering")){
//            System.out.println(this.stuId+" select "+course.getCourseName()+" with books WebEngineering with Web Engineering");
//        }
//    }
    //根据stuId创建对象
    public Student(String stuId) {
        this.stuId = stuId;
    }


    //根据stuId和该学生选的课程创建
    public Student(String stuId, List<Course> course) {

        this.stuId = stuId;
        this.list = course;
//        Iterator<Course> iterator = course.iterator();
//        int i=0;
//        while(iterator.hasNext())
//        {
//            this.courses[i++] = iterator.next();
//        }
//        if(courses[0].getCourseName().equals("Java")){
//            System.out.print(this.stuId+" select "+courses[0].getCourseName()+" with books Thinking in Java, Java 8 ;");
//            System.out.print(" and "+courses[1].getCourseName()+" with books WebEngineering with Web Engineering.");
//        }else{
//            System.out.print(this.stuId+" select "+courses[0].getCourseName()+" with books WebEngineering with Web Engineering. ;");
//            System.out.print(" and "+courses[1].getCourseName()+" with books Thinking in Java, Java 8 ;");
//        }

    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

}
