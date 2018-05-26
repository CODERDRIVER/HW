package HW4.service.impl;

import DataBase.DBUtils;
import HW2.Part1.DateUtil;
import HW4.CalendarUtil;
import HW4.model.*;
import HW4.service.EntityService;

import javax.swing.text.html.parser.Entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/4/25 00:30
 */
public class EntityServiceImpl implements EntityService {



    private Connection connection = DBUtils.getConnection();

    public EntityServiceImpl()
    {
        if (connection==null)
        {
            connection = DBUtils.getConnection();
        }
    }

    /**
     * 返回一个list的list集合
     * 其中下标0表示PIMTodo
     * 下标1表示PIMNote
     * 下标2表示PIMAppointment
     * 下标3表示PIMContact
     * @return
     */
    @Override
    public List<List> findAllEntity() {
        String sql = "select * from PIMENTITY";
        PreparedStatement preparedStatement = null;
        List<List> list = new ArrayList<>();
        List<PIMTodo> pimTodoList = new ArrayList<>();
        List<PIMNote> pimNoteList = new ArrayList<>();
        List<PIMAppointment> pimAppointmentList = new ArrayList<>();
        List<PIMContact> pimContactList = new ArrayList<>();
        ResultSet resultSet = null;
        try{
           preparedStatement =  connection.prepareStatement(sql);
           resultSet = preparedStatement.executeQuery();
           while (resultSet.next())
           {
               if (resultSet.getInt(3)!=0)
               {
                   //说明是todo
                   String sql1 = "select * from PIMTODO WHERE ID = ?";
                   PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                   preparedStatement1.setInt(1,resultSet.getInt(3));
                   ResultSet resultSet1 = preparedStatement1.executeQuery();
//                   String[] colNums = new String[]{"ID","timestamp","item"};
//                   List<List<Object>> lists = new ArrayList<>();
                   while (resultSet1.next())
                   {
                       PIMTodo pimTodo = new PIMTodo();
                       pimTodo.setId(resultSet1.getInt(1));
                       pimTodo.setPriority(resultSet.getString(2));
                       pimTodo.setDate(CalendarUtil.timeStamp2Date(resultSet1.getTimestamp(2, Calendar.getInstance(TimeZone.getTimeZone("GMT+8")))));
                       pimTodo.setTodoItem(resultSet1.getString(3));
                       pimTodo.setOwer(resultSet.getString(7));
                       pimTodo.setStatus(resultSet.getInt(8));
                       pimTodoList.add(pimTodo);
                   }
                   //关闭连接
                   DBUtils.closeAll(resultSet1,preparedStatement1);
               }else if (resultSet.getInt(4)!=0)
               {
                   //是pimnote
                   String sql1 = "select * from PIMNOTE WHERE id = ?";
                   PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                   preparedStatement1.setInt(1,resultSet.getInt(4));
                   ResultSet resultSet1 = preparedStatement1.executeQuery();
                   while (resultSet1.next())
                   {
                       PIMNote pimNote = new PIMNote();
                       pimNote.setPriority(resultSet.getString(2));
                       pimNote.setId(resultSet1.getInt(1));
                       pimNote.setNote(resultSet1.getString(2));
                       pimNote.setOwer(resultSet.getString(7));
                       pimNote.setStatus(resultSet.getInt(8));
                       pimNoteList.add(pimNote);
                   }

                   //关闭连接
                   DBUtils.closeAll(resultSet1,preparedStatement1);

               }else if (resultSet.getInt(5)!=0)
               {
                    //是pimAppoinment
                   String sql1 = "select * from PIMAPPOINTMENT WHERE ID = ?";
                   PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                   preparedStatement1.setInt(1,resultSet.getInt(5));
                   ResultSet resultSet1 = preparedStatement1.executeQuery();
                   while (resultSet1.next())
                   {
                       PIMAppointment pimAppointment = new PIMAppointment();
                       pimAppointment.setPriority(resultSet.getString(2));
                       pimAppointment.setId(resultSet1.getInt(1));
                       pimAppointment.setDate(CalendarUtil.timeStamp2Date(resultSet1.getTimestamp(2,Calendar.getInstance(TimeZone.getTimeZone("GMT+8")))));
                       pimAppointment.setDiscription(resultSet1.getString(3));
                       pimAppointment.setOwer(resultSet.getString(7));
                       pimAppointment.setStatus(resultSet.getInt(8));
                       pimAppointmentList.add(pimAppointment);
                   }
                   //关闭连接
                   DBUtils.closeAll(resultSet1,preparedStatement1);

               }else if (resultSet.getInt(6)!=0)
               {
                    //是pimContact
                   String sql1 = "SELECT * FROM PIMCONTACT WHERE ID = ?";
                   PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                   preparedStatement1.setInt(1,resultSet.getInt(6));
                   ResultSet resultSet1 = preparedStatement1.executeQuery();
                   while (resultSet1.next())
                   {
                       PIMContact pimContact = new PIMContact();
                       pimContact.setId(resultSet1.getInt(1));
                       pimContact.setPriority(resultSet.getString(2));
                       PersonDetail personDetail = new PersonDetail();
                       personDetail.setFirstName(resultSet1.getString(2));
                       personDetail.setLastName(resultSet1.getString(3));
                       personDetail.setEmailAddress(resultSet1.getString(4));
                       pimContact.getSet().add(personDetail);
                       pimContact.setOwer(resultSet.getString(7));
                       pimContact.setStatus(resultSet.getInt(8));
                       pimContactList.add(pimContact);
                   }
                   DBUtils.closeAll(resultSet1,preparedStatement1);
               }
           }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(resultSet,preparedStatement);
        }
        list.add(pimTodoList);
        list.add(pimNoteList);
        list.add(pimAppointmentList);
        list.add(pimContactList);
        return list;
    }



    @Override
    public List<List> findEntityByDate(Date date) {
        List<List> lists = findAllEntity();
        List<List> lists1 = new ArrayList<>();
        //0 todo
        //2 appointment
        List<PIMTodo> pimTodos = lists.get(0);
        List<PIMAppointment> pimAppointments = lists.get(2);
        List<PIMTodo> pimTodos1 = null;
        List<PIMAppointment> pimAppointments1  = null;
        if (pimTodos!=null)
        {
            pimTodos1 = new ArrayList<>();
            for (int i=0;i<pimTodos.size();i++)
            {
                if (DateUtil.fromDateToString(pimTodos.get(i).getDate()).equals(DateUtil.fromDateToString(date)))
                {
                    pimTodos1.add(pimTodos.get(i));
                }
            }
        }
        if (pimAppointments!=null)
        {
            pimAppointments1 = new ArrayList<>();
            for (PIMAppointment pimAppointment:pimAppointments)
            {
                if (DateUtil.fromDateToString(pimAppointment.getDate()).equals(DateUtil.fromDateToString(date)))
                {
                    pimAppointments1.add(pimAppointment);
                }
            }
        }
        if (pimTodos1!=null)
        {
            lists1.add(pimTodos1);
        }
        if (pimAppointments1!=null)
        {
            lists1.add(pimAppointments1);
        }
        return lists1;
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {

        List<List> list =  new EntityServiceImpl().findEntityByDate(DateUtil.fromStringToDate("02/06/1997"));
        List<Object> objects = list.get(0);
        for (Object o:objects)
        {
            PIMTodo pimTodo = (PIMTodo)o;
            System.out.println(pimTodo.getDate());

        }

    }
}
