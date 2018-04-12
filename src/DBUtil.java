import java.sql.*;
import java.util.ArrayList;

public class DBUtil {

    private static Connection connection;

    public static void setConnection() throws ClassNotFoundException, SQLException {             // just standard stuff going on here
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "123456");
        System.out.println("OK");
    }

    public static ArrayList[] getData(int count) throws SQLException {
        int rows = 0;
        int i = 0;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM movies where id < " + count + "", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet movies = statement.executeQuery();
        if (movies.last()) {              //
            rows = movies.getRow();     //get total records
            movies.beforeFirst();       //
        }
        ArrayList[] results = new ArrayList[rows];
        while (movies.next()) {
            results[i] = new ArrayList();
            results[i].add(movies.getInt("id"));            //
            results[i].add(movies.getString("title"));      //
            results[i].add(movies.getString("year"));       //add values into array of ArrayList
            results[i].add(movies.getInt("genre"));         //
            results[i].add(movies.getString("duration"));   //
            i++;
        }
        return results;
    }
    public static ArrayList[] getData() throws SQLException {
        int rows = 0;
        int i = 0;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM movies where id < ", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet movies = statement.executeQuery();
        if (movies.last()) {              //
            rows = movies.getRow();     //get total records
            movies.beforeFirst();       //
        }
        ArrayList[] results = new ArrayList[rows];
        while (movies.next()) {
            results[i] = new ArrayList();
            results[i].add(movies.getInt("id"));            //
            results[i].add(movies.getString("title"));      //
            results[i].add(movies.getString("year"));       //add values into array of ArrayList
            results[i].add(movies.getInt("genre"));         //
            results[i].add(movies.getString("duration"));   //
            i++;
        }
        return results;
    }
}