package HW2;

import java.util.Date;
import java.util.Scanner;

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
                    }
                    System.out.println("create complete!");
                    System.out.println("---Enter a command (suported commands are List Create Save Load Quit)---");
                    break;
                case "SAVE":break;
                case "LOAD":break;
                case "QUIT":
                default:
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


}
