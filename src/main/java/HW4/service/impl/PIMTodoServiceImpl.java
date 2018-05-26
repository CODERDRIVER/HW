package HW4.service.impl;

import DataBase.DBUtils;
import DataBase.IDUtil;
import HW2.Part1.DateUtil;
import HW4.model.PIMTodo;
import HW4.service.PIMTodoService;

import java.sql.*;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/13 14:10
 */
public class PIMTodoServiceImpl implements PIMTodoService{

    @Override
    public void addPIMTodo(PIMTodo pimTodo) {

        Connection connection = DBUtils.getConnection();
        String sql = "insert into PIMTODO(`date`,`item`) values (?,?)";
        String temp = "select ID from PIMTODO where date=? and item=?";
        String sql2 = "insert into PIMENTITY(priority,todo_ID) values(?,?)";
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet =null;
        try {
            preparedStatement1= connection.prepareStatement(sql);
            preparedStatement1.setTimestamp(1,DateUtil.dateToTimeStamp(pimTodo.getDate()));
            preparedStatement1.setString(2,pimTodo.getTodoItem());
            preparedStatement1.execute();
            preparedStatement3 = connection.prepareStatement(temp);
            preparedStatement3.setTimestamp(1, DateUtil.dateToTimeStamp(pimTodo.getDate()));
            preparedStatement3.setString(2,pimTodo.getTodoItem());
            resultSet = preparedStatement3.executeQuery();
            int id  = 0;
            while (resultSet.next())
            {
                id = resultSet.getInt(1);
            }
            preparedStatement2= connection.prepareStatement(sql2);
            preparedStatement2.setString(1,pimTodo.getPriority());
            preparedStatement2.setLong(2,id);
            preparedStatement2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAllAdapter(preparedStatement1,preparedStatement2);
        }
    }
}
