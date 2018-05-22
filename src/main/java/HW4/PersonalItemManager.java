package HW4;

import HW4.model.*;
import HW4.service.impl.EntityServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/8 14:35
 */
public class PersonalItemManager {

    public JFrame jframe;
    final List<List>  lists = new EntityServiceImpl().findAllEntity();
    List<PIMTodo> pimTodos = null;
    List<PIMNote> pimNotes = null;
    List<PIMAppointment> pimAppointments = null;
    List<PIMContact> pimContacts = null;
    public PersonalItemManager(String name)
    {
        this.jframe = new JFrame(name);
        this.jframe.setBounds(300,200,800,400);
        this.jframe.setVisible(true);
        this.pimTodos = (List<PIMTodo>)lists.get(0);
        this.pimNotes = (List<PIMNote>)lists.get(1);
        this.pimAppointments = (List<PIMAppointment>)lists.get(2);
        this.pimContacts = (List<PIMContact>)lists.get(3);

    }

    public void init()
    {
        jframe.setLayout(new GridLayout(4,1));
        showPimtodo();
        showPimNote();
    }

    //显示所有pimTodo的信息
    public void showPimtodo()
    {
        if (pimTodos==null||pimTodos.size()==0)
        {
            return ;
        }
        String[] cols = new String[]{"ID","DATE","ITEM","PRIORITY","OWNER","STATUS"};
        int size = pimTodos.size();
        String[][] rows= new String[size][cols.length];
        for (int i=0;i<size;i++)
        {
            PIMTodo pimTodo = pimTodos.get(i);
            rows[i][0] = pimTodo.getId()+" ";
            rows[i][1] = pimTodo.getDate().toString()+" ";
            rows[i][2] = pimTodo.getTodoItem()+" ";
            rows[i][3] = pimTodo.getPriority()+" ";
            rows[i][4] = pimTodo.getOwer()+" ";
            rows[i][5] = pimTodo.getStatus()+" ";
        }

        JTable jTable = new JTable(rows,cols);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jframe.add(jScrollPane);
    }
    //显示所有的pimNote的信息
    public void showPimNote()
    {
        if (pimNotes==null||pimNotes.size()==0)
        {
            return ;
        }
        String[] cols = new String[]{"ID","TEXT","PRIORITY","OWNER","STATUS"};
        String[][] rows= new String[pimNotes.size()][cols.length];
        for (int i=0;i<pimNotes.size();i++)
        {
            PIMNote pimNote = pimNotes.get(i);
            rows[i][0] = pimNote.getId()+" ";
            rows[i][1] = pimNote.getNote()+" ";
            rows[i][2] = pimNote.getOwer()+" ";
            rows[i][3] = pimNote.getStatus()+" ";
        }
        JTable jTable = new JTable(rows,cols);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jframe.add(jScrollPane);
    }

    //显示所有的pimAppointment的信息
    public void showPimAppointment()
    {
        if (pimAppointments==null||pimAppointments.size()==0)
        {
            return ;
        }
        String cols[] = new String[]{"ID","DATE","DISCRIPTION","OWNER","STATUS","PRIORITY"};
        String[][] rows = new String[pimAppointments.size()][cols.length];
        for (int i=0;i<pimAppointments.size();i++)
        {
            PIMAppointment pimAppointment = pimAppointments.get(i);
            rows[i][0] = pimAppointment.getId()+" ";
            rows[i][1] = pimAppointment.getDate().toString()+" ";
            rows[i][2] = pimAppointment.getDiscription()+" ";
            rows[i][3] = pimAppointment.getOwer()+" ";
            rows[i][4] = pimAppointment.getStatus()+" ";
            rows[i][5] = pimAppointment.getPriority()+" ";
        }
        JTable jTable = new JTable(rows,cols);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jframe.add(jScrollPane);
    }
    //显示所有的pimAppointment的信息
    public void showPimContact()
    {
        if (pimContacts==null||pimContacts.size()==0)
        {
            return ;
        }
        String cols[] = new String[]{"ID","FIRST_NAME","LAST_NAME","EMAIL_ADDRESS","OWNER","STATUS","PRIORITY"};
        String[][] rows = new String[pimContacts.size()][cols.length];

        for (int i=0;i<pimContacts.size();i++)
        {
            PIMContact pimContact = pimContacts.get(i);
            rows[i][0] = pimContact.getId()+" ";
            Set<PersonDetail> set = pimContact.getSet();
            PersonDetail[] personDetails = (PersonDetail[]) set.toArray();
            rows[i][1] = personDetails[0].getFirstName()+" ";
            rows[i][2] = personDetails[0].getLastName()+" ";
            rows[i][3]= personDetails[0].getEmailAddress()+" ";
            rows[i][4] = pimContact.getOwer()+" ";
            rows[i][5] = pimContact.getStatus()+" ";
            rows[i][6] = pimContact.getPriority()+" ";
        }
    }


}
