package HW4.service.impl;

import DataBase.DBUtils;
import HW2.Part1.DateUtil;
import HW4.model.PIMNote;
import HW4.service.PIMNoteService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/27 16:36
 */
public class PIMNoteServiceImpl implements PIMNoteService {
    @Override
    public void addPIMNote(PIMNote pimNote) {
        Connection connection = DBUtils.getConnection();
        String sql = "insert into PIMNOTE(`text`) values (?)";
        String temp = "select ID from PIMNOTE where text=?";
        String sql2 = "insert into PIMENTITY(priority,note_ID,owner) values(?,?,?)";
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet =null;
        try {
            preparedStatement1= connection.prepareStatement(sql);
            preparedStatement1.setString(1, pimNote.getNote());
            preparedStatement1.execute();
            preparedStatement3 = connection.prepareStatement(temp);
            preparedStatement3.setString(1, pimNote.getNote());

            resultSet = preparedStatement3.executeQuery();
            int id  = 0;
            while (resultSet.next())
            {
                id = resultSet.getInt(1);
            }
            preparedStatement2= connection.prepareStatement(sql2);
            preparedStatement2.setString(1,pimNote.getPriority());
            preparedStatement2.setLong(2,id);
            preparedStatement2.setString(3,pimNote.getOwer());
            preparedStatement2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAllAdapter(preparedStatement1,preparedStatement2);
        }
    }

}
