package HW2.Part1;

import  java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 文件处理工具类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/31 10:46
 */
public class FileUtil {

    //文件存储根目录
    public static final String ROOT = "/Users/mac/Downloads/JavaCode/HomeWork/src/main/java/HW2";

    public static File createFile(String fileName)
    {
//        File parent = new File(ROOT);
//        File children[] = parent.listFiles();
//        //判断是否已经被创建
//        for(File file:children)
//        {
//            if(file.getName().equals(fileName))
//            {
//                System.out.println(fileName+" 已经被创建");
//                return file;
//            }
//        }
        File file = new File(ROOT+"/"+fileName);
        try {
            if(!file.exists())
            {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileName+" 已经被创建");
        return file;
    }

    /**
     *
     * @param destFile 保存到那个文件
     * @param content 保存的内容
     */
    public static void saveFile(File destFile,String content)
    {
        FileOutputStream fos = null;
        try{

           fos =  new FileOutputStream(destFile,true);      //是否是可追加
           FileChannel fileChannel = fos.getChannel();

           //将string 转成ByteBuffer
            ByteBuffer src = Charset.forName("utf-8").encode(content);


            fileChannel.write(src);

        }catch (FileNotFoundException e)
        {
            System.out.println("文件找不到！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos!=null)
            {
                try{
                    fos.close();
                }catch (IOException e)
                {
                    System.out.println("关闭失败");
                }
            }
        }
    }

    /**
     * 加载文件
     * @return
     */
    public static List<List> load()
    {
        /**
         * map存储PIMEntity
         * 0----> pimTodo
         * 1----> pimNote
         * 2---->pimAppointment
         * 3---->pimContact
         */
        List<List> lists = new ArrayList<>();
        List<PIMTodo> pimTodoList = new ArrayList<>();
        List<PIMNote> pimNoteList = new ArrayList<>();
        List<PIMAppointment> pimAppointmentList = new ArrayList<>();
        List<PIMContact> pimContactList = new ArrayList<>();
        File parent = new File(ROOT);
        File[] files = parent.listFiles();
        for(File temp:files)
        {
            if(temp.getName().equals("pimTodo.txt"))
            {
                List<String> strings = FileUtil.read(temp.getAbsolutePath());
                for(String s:strings)
                {
                    PIMTodo pimTodo = new PIMTodo();
                    pimTodo.fromString(s);
                    pimTodoList.add(pimTodo);
                }
            }else if(temp.getName().equals("pimNote.txt"))
            {
                List<String> strings = FileUtil.read(temp.getAbsolutePath());
                for(String s:strings)
                {
                    PIMNote pimNote = new PIMNote();
                    pimNote.fromString(s);
                    pimNoteList.add(pimNote);
                }
            }else if (temp.getName().equals("pimAppointment.txt"))
            {
                List<String> strings = FileUtil.read(temp.getName());
                for(String s:strings)
                {
                    PIMAppointment pimAppointment = new PIMAppointment();
                    pimAppointment.fromString(s);
                    pimAppointmentList.add(pimAppointment);
                }
            }else if (temp.getName().equals("pimContact.txt"))
            {
                List<String> strings = FileUtil.read(temp.getName());
                for(String s:strings)
                {
                    PIMContact pimContact = new PIMContact();
                    pimContact.fromString(s);
                    pimContactList.add(pimContact);
                }
            }
        }
        lists.add(pimTodoList);
        lists.add(pimNoteList);
        lists.add(pimAppointmentList);
        lists.add(pimContactList);
        return lists;
    }

    /**
     * 从文件中读取数据
     * @param fileName
     * @return
     */
    public static  List<String> read(String fileName)
    {
        List<String> list = new ArrayList<>();
        try {
           BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
           int len = -1;
           String line = null;
           while((line=bufferedReader.readLine())!=null)
           {
                list.add(line);
           }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }
}
