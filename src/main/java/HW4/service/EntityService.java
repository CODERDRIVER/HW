package HW4.service;

import javax.swing.text.html.parser.Entity;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/4/25 00:29
 */
public interface EntityService {

    /**
     * 查询的所有的Entity
     */
    public List<List> findAllEntity();

    /**
     *
     */
    public List<List> findEntityByDate(Date date);


    /**
     * 增加一个Entity
     */


    /**
     * 删除某个Entity
     */

    /**
     * 对某个Entity进行修改
     */

}
