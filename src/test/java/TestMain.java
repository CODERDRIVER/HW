import DataBase.DBUtils;
import HW2.Part1.DateUtil;

import java.sql.Date;
import java.sql.ResultSet;

import HW4.service.impl.EntityServiceImpl;
import org.junit.Test;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/26 13:47
 */
public class TestMain {


    @Test
    public void test1()
    {
        Connection connection = DBUtils.getConnection();
        System.out.println(connection);


        PreparedStatement preparedStatement =null;
        ResultSet resultset = null;
        try{
            String sql = "select date from PIMTODO";
            preparedStatement= connection.prepareStatement(sql);
            resultset =preparedStatement.executeQuery();
            while (resultset.next())
            {
                System.out.println(resultset.getTimestamp(1, Calendar.getInstance(TimeZone.getTimeZone("GMT+8"))));
//                System.out.println(resultset.getTimestamp(1));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void test2()
    {
        EntityServiceImpl entityServiceImpl = new EntityServiceImpl();
        entityServiceImpl.findAllEntity();
    }

    @Test
    public void test3()
    {
        EntityServiceImpl entityService = new EntityServiceImpl();
        List<List> list = entityService.findAllEntity();
        System.out.println(list.size());
    }

    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        System.out.println(DateUtil.fromStringToDate("/13/1997"));
    }

}
