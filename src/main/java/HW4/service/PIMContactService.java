package HW4.service;


import HW4.model.PIMContact;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/27 16:39
 */
public interface  PIMContactService {

    /**
     * 向pimcontact表添加一行数据
     * @param pimContact
     */
    public void addPIMContact(PIMContact pimContact);
}
