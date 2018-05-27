package HW4.service;


import HW4.model.PIMNote;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/27 16:34
 */
public interface PIMNoteService {

    /**
     * 向数据库中添加一条记录
     * @param pimNote
     */
    public void addPIMNote(PIMNote pimNote);
}
