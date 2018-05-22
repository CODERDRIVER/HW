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
    public PIMCollection(PIMEntity[] pimEntities)
    {
        this.pimEntities = pimEntities;
    }

    public Collection getNotes(){
        Set<PIMNote> pimNotes = new HashSet<>();
        for(int i=0;i<pimEntities.length;i++)
        {
            if(pimEntities[i].getClass()== PIMNote.class)
            {
                pimNotes.add((PIMNote) pimEntities[i]);
            }
        }
        return pimNotes;
    }

    public Collection getTodos()
    {
        Set<PIMTodo> pimTodos = new HashSet<>();
        for(int i=0;i<pimEntities.length;i++)
        {
            if(pimEntities[i].getClass()== PIMNote.class)
            {
                pimTodos.add((PIMTodo) pimEntities[i]);
            }
        }
        return pimTodos;
    }


    public Collection getAppointments(){
        Set<PIMAppointment> pimAppointments = new HashSet<>();
        for(int i=0;i<pimEntities.length;i++)
        {
            if(pimEntities[i].getClass()== PIMNote.class)
            {
                pimAppointments.add((PIMAppointment) pimEntities[i]);
            }
        }
        return pimAppointments;
    }


    public Collection getContact()
    {
        Set<PIMContact> pimContacts = new HashSet<>();
        for(int i=0;i<pimEntities.length;i++)
        {
            if(pimEntities[i].getClass()== PIMNote.class)
            {
                pimContacts.add((PIMContact) pimEntities[i]);
            }
        }
        return pimContacts;
    }

    public Collection getItemsForDate(Date d){

        Set<PIMEntity> set = new HashSet<>();
        for(int i=0;i<pimEntities.length;i++)
        {
            if(pimEntities[i].getClass()==PIMTodo.class)
            {
                PIMTodo pimTodo = (PIMTodo)pimEntities[i];
                if(pimTodo.getDate().equals(d))
                {
                    set.add(pimTodo);
                }
            }else if(pimEntities[i].getClass()==PIMAppointment.class)
            {
                PIMAppointment pimAppointment = (PIMAppointment)pimEntities[i];
                if(pimAppointment.getDate().equals(d))
                {
                    set.add(pimAppointment);
                }
            }
        }
        return set;
    }
}
