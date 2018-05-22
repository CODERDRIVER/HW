package DataBase;

import javax.annotation.Resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

/**
 * @Description 数据库工具类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/4/17 17:27
 */
public class DBUtils {

    public static String driver;
    public  static String url;
    public static String username;
    public static String password;
    public final static Connection connection = getConnection();
    public static Connection getConnection()
    {
        Properties properties = new Properties();
        Connection connection = null;
        //加载配置文件
        try{
            properties.load(new InputStreamReader(new FileInputStream(new File("/Users/mac/Downloads/JavaCode/HomeWork/src/main/java/DataBase/db.properties"))));
            driver = (String)properties.get("mysql.driver");
            url = (String)properties.get("mysql.url");
            username = (String)properties.get("mysql.username");
            password = (String)properties.get("mysql.password");
            Class.forName(driver);
           connection = DriverManager.getConnection(url,username,password);
        }catch (Exception e)
        {
            //进行异常处理
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection(Connection connection)
    {
        if (connection!=null){
            try{
                connection.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭resultSet，preparedStatement,释放资源
     * @param resultSet
     * @param preparedStatement
     */
    public static void closeAll(ResultSet resultSet, PreparedStatement preparedStatement)
    {
        if (resultSet!=null)
        {
            try{
                resultSet.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (preparedStatement!=null)
        {
            try{
                preparedStatement.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void closeAllAdapter(AutoCloseable ...autoCloseables)
    {
        for (AutoCloseable autoCloseable:autoCloseables)
        {
            if (autoCloseable!=null)
            {
                try{
                    autoCloseable.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    //test
    public static void main(String[] args) {
        Connection connection = getConnection();
        try {
            System.out.println(connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
