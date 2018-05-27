package HW4.service.impl;

import DataBase.DBUtils;
import HW2.Part1.DateUtil;
import HW4.model.PIMAppointment;
import HW4.service.PIMAppointmentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/27 16:31
 */
public class PIMAppointmentServiceImpl implements PIMAppointmentService{

    @Override
    public void addPIMAppointment(PIMAppointment pimAppointment) {
        Connection connection = DBUtils.getConnection();
        String sql = "insert into PIMAPPOINTMENT(`date`,`discription`) values (?,?)";
        String temp = "select ID from PIMAPPOINTMENT where date=? and discription=?";
        String sql2 = "insert into PIMENTITY(priority,appointment_ID,owner) values(?,?,?)";
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet =null;
        try {
            preparedStatement1= connection.prepareStatement(sql);
            preparedStatement1.setTimestamp(1, DateUtil.dateToTimeStamp(pimAppointment.getDate()));
            preparedStatement1.setString(2,pimAppointment.getDiscription());
            preparedStatement1.execute();
            preparedStatement3 = connection.prepareStatement(temp);
            preparedStatement3.setTimestamp(1, DateUtil.dateToTimeStamp(pimAppointment.getDate()));
            preparedStatement3.setString(2,pimAppointment.getDiscription());

            resultSet = preparedStatement3.executeQuery();
            int id  = 0;
            while (resultSet.next())
            {
                id = resultSet.getInt(1);
            }
            preparedStatement2= connection.prepareStatement(sql2);
            preparedStatement2.setString(1,pimAppointment.getPriority());
            preparedStatement2.setLong(2,id);
            preparedStatement2.setString(3,pimAppointment.getOwer());
            preparedStatement2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAllAdapter(preparedStatement1,preparedStatement2);
        }
    }
}
