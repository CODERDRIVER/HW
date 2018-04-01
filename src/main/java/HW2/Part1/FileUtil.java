package HW2.Part1;

import  java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @Description 文件处理工具类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/31 10:46
 */
public class FileUtiil {

    //文件存储根目录
    public static final String ROOT = "/Users/mac/Downloads/JavaCode/HomeWork/src/main/java/HW2";

    public static File createFile(String fileName)
    {
        File parent = new File(ROOT);
        File children[] = parent.listFiles();
        //判断是否已经被创建
        for(File file:children)
        {
            if(file.getName().equals(fileName))
            {
                System.out.println(fileName+" 已经被创建");
                return file;
            }
        }
        File file = new File(ROOT+"/"+fileName);
        if(!file.exists())
        {
            FileUtiil.createFile(ROOT+"/"+fileName);
        }
        System.out.println(fileName+" 已经被创建");
        return file;
    }

    /**
     *
     * @param destFile 保存到那个文件
     * @param content 保存的内容
     */
    public static void saveFile(File destFile,String content) throws  IOException
    {
        FileOutputStream fos = null;
        try{

           fos =  new FileOutputStream(destFile,true);      //是否是可追加
           FileChannel fileChannel = fos.getChannel();

           //将string 转成ByteBuffer
            ByteBuffer src = Charset.forName("utf-8").encode(content);

            while(fileChannel.write(src)!=-1)
            {
            }


        }catch (FileNotFoundException e)
        {
            System.out.println("文件找不到！");
        }
    }
}
