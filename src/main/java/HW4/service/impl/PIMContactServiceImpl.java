package HW4.service.impl;

import DataBase.DBUtils;

import HW4.model.PIMContact;
import HW4.model.PIMNote;
import HW4.model.PersonDetail;
import HW4.service.PIMContactService;
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
 * @Date: 2018/5/27 16:40
 */
public class PIMContactServiceImpl implements PIMContactService{


    @Override
    public void addPIMContact(PIMContact pimContact) {
        Connection connection = DBUtils.getConnection();
        String sql = "insert into PIMCONTACT(`FIRST_NAME`,`LAST_NAME`,`EMAIL_ADDRESS`) values (?,?,?)";
        String temp = "select ID from PIMCONTACT where FIRST_NAME=? AND LAST_NAME=?";
        String sql2 = "insert into PIMENTITY(priority,contact_ID,owner) values(?,?,?)";
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet =null;
        try {
            preparedStatement1= connection.prepareStatement(sql);
            PersonDetail[] personDetails = (PersonDetail[])pimContact.getSet().toArray();
            preparedStatement1.setString(1, personDetails[0].getFirstName());
            preparedStatement1.setString(2,personDetails[1].getLastName());
            preparedStatement1.setString(3,personDetails[2].getEmailAddress());
            preparedStatement1.execute();
            preparedStatement3 = connection.prepareStatement(temp);
            preparedStatement3.setString(1, personDetails[0].getFirstName());
            preparedStatement3.setString(2,personDetails[1].getLastName());

            resultSet = preparedStatement3.executeQuery();
            int id  = 0;
            while (resultSet.next())
            {
                id = resultSet.getInt(1);
            }
            preparedStatement2= connection.prepareStatement(sql2);
            preparedStatement2.setString(1,pimContact.getPriority());
            preparedStatement2.setLong(2,id);
            preparedStatement2.setString(3,pimContact.getOwer());
            preparedStatement2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAllAdapter(preparedStatement1,preparedStatement2);
        }
    }
}
