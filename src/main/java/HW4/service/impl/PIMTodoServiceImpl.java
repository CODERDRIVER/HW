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
        String sql2 = "insert into PIMENTITY(priority,todo_ID,owner) values(?,?,?)";
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
            preparedStatement2.setString(3,pimTodo.getOwer());
            preparedStatement2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAllAdapter(preparedStatement1,preparedStatement2);
        }
    }

    /**
     * 根据id删除pimtodo中的一行数据
     * @param id
     * @return
     */
    @Override
    public boolean deleteTodoById(int id) {
        Connection connection = DBUtils.getConnection();
        String sql2 = "delete from PIMENTITY WHERE todo_ID = ?";
        String sql = "delete from PIMTODO where ID=?";
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        boolean status = false;
        try{
            //首先删除PIMENTITY中todo_ID是id的行
            preparedStatement2  =connection.prepareStatement(sql2);
            preparedStatement2.setInt(1,id);
            boolean flag = preparedStatement2.execute();

            //删除PIMTodo中id为id的行
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            status =  preparedStatement.execute();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAllAdapter(preparedStatement,connection);
        }
        return status;
    }

    /**
     * 更新pimtodo的item内容
     * @param id
     * @param itenContent
     */
    @Override
    public void updatePIMTodo(int id, String  itenContent) {
        Connection connection = DBUtils.getConnection();
        String sql = "update PIMTODO SET item = ? WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try{
            //预编译sql
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,itenContent);
            preparedStatement.setInt(2,id);
            //执行
            preparedStatement.execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBUtils.closeAllAdapter(preparedStatement,connection);
        }
    }


    @Override
    public void updatePIMEntityByTodoid(int todoId, PIMTodo pimTodo) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update PIMENTITY SET owner=?,status=?,priority=? where todo_ID=?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            //设值
            preparedStatement.setString(1,pimTodo.getOwer());
            preparedStatement.setInt(2,pimTodo.getStatus());
            preparedStatement.setString(3,pimTodo.getPriority());
            preparedStatement.setInt(4,todoId);
            //执行查询
            preparedStatement.execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            DBUtils.closeAllAdapter(preparedStatement,connection);
        }
    }
}
