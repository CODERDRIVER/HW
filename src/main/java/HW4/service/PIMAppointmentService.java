package HW4.service;

import HW4.model.PIMAppointment;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/27 16:30
 */
public interface PIMAppointmentService {

    //往数据库中添加一行数据
    public void addPIMAppointment(PIMAppointment pimAppointment);
}
