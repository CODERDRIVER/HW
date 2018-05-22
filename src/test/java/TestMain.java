import DataBase.DBUtils;
import HW2.Part1.DateUtil;

import java.sql.Date;
import java.sql.ResultSet;

import HW4.service.impl.EntityServiceImpl;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/26 13:47
 */
public class TestMain {

    public static void main(String[] args) {
        System.out.println(DateUtil.fromStringToDate("88/23424/43242"));
    }

    @Test
    public void test1()
    {
        Connection connection = DBUtils.getConnection();
        System.out.println(connection);


        PreparedStatement preparedStatement =null;
        ResultSet resultset = null;
        try{
            String sql = "select * from PIMTODO WHERE ID = ?";
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            resultset =preparedStatement.executeQuery();
            while (resultset.next())
            {
                System.out.println(resultset.getInt(1));
                System.out.println(resultset.getTimestamp(2));
                System.out.println(resultset.getString(3));
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

}
