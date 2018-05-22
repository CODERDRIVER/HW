package HW3;

import HW2.Part1.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/22 15:41
 */
public class TestPimCollection {

    public static void main(String[] args) {

        PIMCollection pimCollection = new PIMCollection();
        pimCollection.add(new PIMNote("liuxuyang"));
        pimCollection.add(new PIMTodo(new Date(),"i want to go school"));
        pimCollection.add(new PIMAppointment(new Date(),"nihao"));
        Set set = new HashSet();
        set.add((new PersonDetail("liu","xuyang","1187697635@qq.com")));
        set.add((new PersonDetail("song","qiang","songqian@qq.com")));
        pimCollection.add(set);

        System.out.println(pimCollection.getAppointments().toString());
        System.out.println(pimCollection.getItemsForDate(new Date()).toString());
    }
}
