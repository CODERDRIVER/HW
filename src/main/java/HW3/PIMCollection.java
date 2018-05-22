package HW3;

import HW2.Part1.*;

import java.util.*;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/4/4 11:30
 */
public class PIMCollection extends HashSet implements Collection {

    private PIMEntity[] pimEntities;

    public PIMCollection()
    {

    }
    public Collection getNotes(){

        Set<PIMNote> pimNotes = new HashSet<>();
        Iterator iterator = this.iterator();
        while(iterator.hasNext())
        {
            Object o = iterator.next();
            if (o.getClass()==PIMNote.class)
            {
                pimNotes.add((PIMNote) o);
            }
        }
        return pimNotes;
    }

    public Collection getTodos()
    {
        Set<PIMTodo> pimTodos = new HashSet<>();
        Iterator iterator = this.iterator();
        while(iterator.hasNext())
        {
            Object o = iterator.next();
            if (o.getClass()==PIMTodo.class)
            {
                pimTodos.add((PIMTodo) o);
            }
        }
        return pimTodos;
    }


    public Collection getAppointments(){
        Set<PIMAppointment> pimAppointments = new HashSet<>();
        Iterator iterator = this.iterator();
        while(iterator.hasNext())
        {
            Object o = iterator.next();
            if (o.getClass()==PIMAppointment.class)
            {
                pimAppointments.add((PIMAppointment) o);
            }
        }
        return pimAppointments;
    }


    public Collection getContact()
    {
        Set<PIMContact> pimContacts = new HashSet<>();
        Iterator iterator = this.iterator();
        while(iterator.hasNext())
        {
            Object o = iterator.next();
            if (o.getClass()==PIMContact.class)
            {
                pimContacts.add((PIMContact) o);
            }
        }
        return pimContacts;
    }

    public Collection getItemsForDate(Date d){

        Set<PIMEntity> set = new HashSet<>();
        Iterator iterator = this.iterator();
        while(iterator.hasNext())
        {
            Object o = iterator.next();
            //与时间相关的item只有PIMTodo和PIMAppointment两个
            if (o.getClass()==PIMTodo.class)
            {
                PIMTodo pimTodo = (PIMTodo)o;
                if (PIMCollectionUtil.isDateEqual(pimTodo.getDate(),d))
                {
                    set.add(pimTodo);
                }
            }else if (o.getClass()==PIMAppointment.class)
            {
                PIMAppointment pimAppointment = (PIMAppointment)o;
                if (PIMCollectionUtil.isDateEqual(pimAppointment.getDate(),d))
                {
                    set.add(pimAppointment);
                }
            }
        }
        return set;
    }
}
