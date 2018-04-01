package HW1.CRS;

import java.util.*;

/**
 * @Description Course类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/21 23:46
 */
public class Course {

    private String courseName;  //课程名称
    private Map<String, List<Book>> map = new HashMap<>();

    public Course() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("Thinking in Java"));
        list.add(new Book("Java 8"));
        map.put("Java", list);
        List<Book> list1 = new ArrayList<>();
        list1.add(new Book("Web Engineering"));
        map.put("WebEngineering",list1);
    }

    public Course(String courseName) {
        this();
        this.courseName = courseName;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Map<String, List<Book>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<Book>> map) {
        this.map = map;
    }
}

