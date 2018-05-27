package HW4.service;


import HW4.model.PIMTodo;

/**
 * @Description
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/5/13 14:08
 */
public interface PIMTodoService {

    //保存一个PIMTodo
    public void addPIMTodo(PIMTodo pimTodo);

    //根据id删除某条记录
    public boolean deleteTodoById(int id);

    //根据id更新某项记录
    public void updatePIMTodo(int id,String itemContent);

    //根据todo_ID跟新pimEntity的属性
    public void updatePIMEntityByTodoid(int todoId,PIMTodo pimTodo);
}
