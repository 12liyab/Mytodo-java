package com.freddie.to_do_list_app.User;

import com.freddie.to_do_list_app.DBConnectivity.DBConnectivity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepo {
    private static final DBConnectivity connect = DBConnectivity.getInstance();

    public static int insert(User user){
        String sql = "insert into user(username, password) values(?,?);";
        int result = 0;

        try{
            PreparedStatement ps = connect.getConnection().prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());

            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;

    }
}
