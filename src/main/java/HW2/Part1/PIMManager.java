package HW2.Part1;

import java.util.*;
import  java.io.*;

/**
 * @Description 主程序
 * 1. list ： print a list of all PIM items
 * 2. Create: add a new item
 * 3. Save: save the entire list of items
 *      (HW2: simple version, just print out;
 *      complex version, to a file, should be finished after I/O topic,
 *      to database, can (optional) be finished after JDBC topic,
 *      to a remote server, can (optional) be after Networking topic. )
 * 4. Load: read a list of items from a file
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/25 15:37
 */
public class PIMManager{

    private static  int itemCount = 0;
    public static void main(String[] args) {
        System.out.println("Welcome to PIM.");
        System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");

        PIMEntity[] pimEntities = new PIMEntity[100];   //初始化一个PIMEntity数组，用来存储Entity
        Scanner scanner = new Scanner(System.in);
        PIMManager pimManager = new PIMManager();
        while(true)
        {
            String command = scanner.nextLine().toUpperCase();  //为了让输入不区分大小写
            switch (command){
                case "LIST":
                    pimManager.list(pimEntities,itemCount);
                    break;
                case "CREATE":
                    System.out.println("Enter an item type ( todo, note, contact or appointment )");
                    String type = scanner.nextLine().toLowerCase();
                    StringBuilder stringBuilder = new StringBuilder();
                    switch (type){
                        case "todo":
                            System.out.println("Enter date for todo item: (dd/mm/yyyy)");
                            String date = scanner.nextLine();
                            while(!DateUtil.validateDate(date))
                            {
                                System.out.println("Enter date for todo item: (dd/mm/yyyy)");
                                date = scanner.nextLine();
                            }
                            stringBuilder.append(date+";");
                            System.out.println("Enter todo text:");
                            stringBuilder.append(scanner.nextLine()+";");
                            System.out.println("Enter todo priority:");
                            stringBuilder.append(scanner.nextLine());
                            PIMTodo pimTodo = new PIMTodo();
                            pimTodo.fromString(stringBuilder.toString());
                            pimEntities[itemCount++] = pimTodo;
                            break;
                        case "note":
                            System.out.println("Enter note text:");
                            stringBuilder.append(scanner.nextLine()+";");
                            System.out.println("Enter todo priority");
                            stringBuilder.append(scanner.nextLine());
                            PIMNote pimNote = new PIMNote();
                            pimNote.fromString(stringBuilder.toString());
                            pimEntities[itemCount++] = pimNote;
                            break;
                        case "contact":
                            System.out.println("enter some contacts with first name,last name, emailAddress 一个联系人占一行，中间用空格隔开，0表示输入结束");
                            while (!scanner.nextLine().equals("0")){
                                stringBuilder.append(scanner.nextLine()+";");
                            }
                            System.out.println("Entre contact priority");
                            stringBuilder.append(scanner.nextLine());
                            PIMContact pimContact = new PIMContact();
                            pimContact.fromString(stringBuilder.toString());
                            pimEntities[itemCount++] = pimContact;
                            break;
                        case "appointment":
                            System.out.println("Enter date for appointment: (dd/mm/yyyy)");
                            stringBuilder.append(scanner.nextLine()+";");
                            System.out.println("Enter appointment discription ");
                            stringBuilder.append(scanner.nextLine()+";");
                            System.out.println("Enter appointment priority");
                            stringBuilder.append(scanner.nextLine());
                            PIMAppointment pimAppointment = new PIMAppointment();
                            pimAppointment.fromString(stringBuilder.toString());
                            pimEntities[itemCount++] = pimAppointment;
                            break;
                        default:
                            System.out.println("type error");
                            break;
                    }
                    if(type.equals("todo")||type.equals("note")||type.equals("contact")||type.equals("appointment"))
                    {
                        System.out.println("create complete!");
                    }
                    System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");
                    break;
                case "SAVE":
                    save(pimEntities);
                    System.out.println("保存OK");

                    /**
                     * 重新初始化
                     */
                    pimEntities = new PIMEntity[100];
                    itemCount = 0;
                    System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");
                    break;
                case "LOAD":
                    List<List> lists = load();
                    List pimTodoItem = lists.get(0);
                    printList(pimTodoItem);
                    List pimNoteItem = lists.get(1);
                    printList(pimNoteItem);
                    List pimAppointment = lists.get(2);
                    printList(pimAppointment);
                    List pimContactItem = lists.get(3);
                    printList(pimContactItem);
                    System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");
                    break;
                case "QUIT":
                    System.out.println("你已注销");
                    return ;
                default:
                    System.out.println("输入命令不正确");
                    System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");
                    break;
            }

        }

    }
    public void list(PIMEntity[] pimEntities,int count)
    {
        if(count==0)
        {
            System.out.println("There are "+ count+" items.");
            System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");
        }else{
            System.out.println("There are "+ count+" items.");
            for(int i=0;i<count;i++)
            {
                System.out.println("item "+(i+1)+":"+pimEntities[i].toString());
            }
            System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");
        }
    }

    //save 方法  保存所有的Entity Item
    public static  void save(PIMEntity[] pimEntities)
    {
        for (int i=0;i<PIMManager.itemCount;i++)
        {
            if(pimEntities[i].getClass().equals(PIMTodo.class))
            {
                //是TODO
                PIMTodo pimTodo = (PIMTodo)pimEntities[i];
                /**
                 * 日期 date
                 * todoItem
                 * priority
                 */
                String s = "";
                s +=pimTodo.getDate()+";";
                s += pimTodo.getTodoItem()+";";
                s +=pimTodo.getPriority()+"\n";
                //创建文件
                File file = FileUtil.createFile("pimTodo.txt");
                //写入文件
                FileUtil.saveFile(file,s);

            }else if(pimEntities[i].getClass().equals(PIMNote.class))
            {
                //Note
                PIMNote pimNote = (PIMNote) pimEntities[i];
                /**
                 * text
                 * priority
                 */
                String s = "";
                s += pimNote.getNote()+";";
                s += pimNote.getPriority()+"\n";
                //创建文件
                File file = FileUtil.createFile("pimNote.txt");
                //写入文件
                FileUtil.saveFile(file,s);

            }else if(pimEntities[i].getClass().equals(PIMAppointment.class))
            {
                //appointment
                //是TODO
                PIMAppointment pimAppointment = (PIMAppointment) pimEntities[i];
                /**
                 * 日期 date
                 * discription
                 * priority
                 */
                String s = "";
                s += pimAppointment.getDate()+";";
                s += pimAppointment.getDiscription()+";";
                s += pimAppointment.getPriority()+"\n";

                //创建文件
                File file = FileUtil.createFile("pimAppointment.txt");
                //写入文件
                FileUtil.saveFile(file,s);
            }else{
                //contact
                //是TODO
                PIMContact pimContact = (PIMContact) pimEntities[i];
                /**
                 * first name,last name, emailAddress
                 * priority
                 */
                String s = "";
                Set<PersonDetail> set = pimContact.getSet();
                Iterator<PersonDetail> iterator = set.iterator();
                while(iterator.hasNext())
                {
                    PersonDetail personDetail = iterator.next();
                    s += personDetail.getFirstName()+" "+personDetail.getLastName()+" "+personDetail.getEmailAddress()+" ";
                    s +=";";
                }
                s+=pimContact.getPriority();
                //创建文件
                File file = FileUtil.createFile("pimContact.txt");
                //写入文件
                FileUtil.saveFile(file,s);
            }
        }
        return ;
    }

    //从文件中加载item
    public static List<List> load()
    {
        List<List> lists= FileUtil.load();

        return lists;
    }

    public static void printList(List list)
    {
        if(list.size()!=0)
        {
            list.forEach(temp->
                    System.out.println(temp.toString()));
        }
    }

}
