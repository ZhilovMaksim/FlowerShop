package sample.flowershop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs{
    Connection dbConnection;
    public Connection getDbConnecion() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void signUpUser(String username, String password) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO "+Const.USER_TABLE+ "("+Const.USERNAME+","+Const.USER_PASSWORD+")"+"VALUES(?,?)";
        PreparedStatement prSt = null;
        try {
            prSt = getDbConnecion().prepareStatement(insert);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        prSt.setString(1,username);
        prSt.setString(2,password);
        prSt.executeUpdate();
    }

    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT * FROM " +Const.USER_TABLE + " WHERE "+ Const.USERNAME + "=? AND "+Const.USER_PASSWORD + "=?";
        PreparedStatement prSt = getDbConnecion().prepareStatement(select);
        prSt.setString(1,user.getUsername());
        prSt.setString(2,user.getPassword());
        resSet = prSt.executeQuery();
        return resSet;
    }
    public void updateUserInfo(int userId, String firstName, String lastName, String city, String address, String phoneNumber) throws SQLException, ClassNotFoundException {
        String update = "UPDATE "+Const.USER_TABLE+" SET "+Const.USER_FIRST_NAME+" = ?, "+Const.USER_LAST_NAME+" = ?, "+
                Const.USER_CITY+" = ?, "+Const.USER_ADDRESS+" = ?, "+Const.USER_PHONE_NUMBER+" = ? WHERE "+Const.USER_ID+" = ?";
        try (PreparedStatement prSt = getDbConnecion().prepareStatement(update)) {
            prSt.setString(1, firstName);
            prSt.setString(2, lastName);
            prSt.setString(3, city);
            prSt.setString(4, address);
            prSt.setString(5, phoneNumber);
            prSt.setInt(6, userId);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public int getUserId(String username, String password) throws SQLException, ClassNotFoundException {
        int userId = -1;
        String query = "SELECT user_id FROM " + Const.USER_TABLE + " WHERE username=? AND password=?";
        try (PreparedStatement prSt = getDbConnecion().prepareStatement(query)) {
            prSt.setString(1, username);
            prSt.setString(2, password);
            try (ResultSet resultSet = prSt.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("user_id");
                }
            }
        }
        return userId;
    }
}
