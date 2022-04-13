package dev.marmo.data;

import dev.marmo.entities.UserAccount;
import dev.marmo.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserAccountDAOPostgresImpl implements UserAccountDAO {



    @Override
    public UserAccount createUserAccount(UserAccount account) {
        //insert into user_account values (default,'Bob', 'Ross', 'bobross22', 'letsgopaint', 50.0);

        Connection conn = ConnectionUtil.createConnection();
        String sql = "insert into user_account values (default, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getFirstName()); //for first question mark
            ps.setString(2, account.getLastName()); //for second question mark
            ps.setString(3, account.getUserName()); //for third question mark
            ps.setString(4, account.getPassword()); //for fourth question mark
            ps.setDouble(5, account.getBalance()); //for fifth question mark

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys(); //ResultSet is a virtual table
            rs.next(); //move the first record of the result set
            int generatedId = rs.getInt("user_id");
            account.setUserId(generatedId);
            return account;



        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public UserAccount getAccountById(int id) {

        Connection conn = ConnectionUtil.createConnection();
        String sql = "select * from user_account where user_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            UserAccount account = new UserAccount();
            account.setUserId(rs.getInt("user_id"));
            account.setFirstName(rs.getString("first_name"));
            account.setLastName(rs.getString("last_name"));
            account.setUserName(rs.getString("user_name"));
            account.setPassword(rs.getString("pass_word"));
            account.setBalance(rs.getDouble("balance"));
            return account;




        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public List<UserAccount> getAllAccounts() {
        Connection conn = ConnectionUtil.createConnection();
        String sql = "select * from user_account";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<UserAccount> accounts = new ArrayList();
            while(rs.next()){
                UserAccount account = new UserAccount();
                account.setUserId(rs.getInt("user_id"));
                account.setFirstName(rs.getString("first_name"));
                account.setLastName(rs.getString("last_name"));
                account.setUserName(rs.getString("user_name"));
                account.setPassword(rs.getString("pass_word"));
                account.setBalance(rs.getDouble("balance"));
                accounts.add(account);
            }

            return accounts;




        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserAccount updateAccount(UserAccount account) {

        //update user_account set user_name = 'bobross123' where user_id = 5;

        try {
            Connection conn =  ConnectionUtil.createConnection();
            String sql = "update user_account set first_name = ?, last_name = ? where user_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account.getFirstName());
            ps.setString(2, account.getLastName());
            ps.setInt(3,account.getUserId());
            ps.executeUpdate();

            return account;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAccountByID(int id) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "Delete from user_account where user_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean getLogin(String userName) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from user_account where user_name =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.next();
            String result = rs.getString("user_name");
            return userName.equals(result);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserAccount createLogin (UserAccount account){
        try {
            Connection connection = ConnectionUtil.createConnection();
            String sql = "update user_account set user_name = ?, pass_word = ? where user_id = ?;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getUserName());
            ps.setString(2, account.getPassword());
            ps.setInt(3, account.getUserId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return account;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int getLogin(String userName, String password) {
        try {
            Connection connection = ConnectionUtil.createConnection();
            String sql = "select * from user_account where user_name=? and pass_word=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.next();
            int userID = rs.getInt("user_id");
            String passwordToCompare = rs.getString("pass_word");
            if(passwordToCompare.equals(password))
                return userID;



        } catch (SQLException e) {
            e.printStackTrace();


        }
        return -1;

    }



}
