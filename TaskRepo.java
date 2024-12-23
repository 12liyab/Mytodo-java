package com.freddie.to_do_list_app.Task;

import com.freddie.to_do_list_app.DBConnectivity.DBConnectivity;

import java.sql.PreparedStatement;
import java.sql.Connection ;
import java.sql.SQLException;


public class TaskRepo {

    private static final DBConnectivity connectivity = DBConnectivity.getInstance();

    public static int insertTask(Task task) {
        String sql = "insert into user(taskId, taskName, description, status) values(?,?,?,?);";
        int result  = 0;
        try {
            String query = "insert into task( taskId, taskName,taskDescription,taskStatus) value(?,?,?,?);";
            PreparedStatement ps = connectivity.getConnection().prepareStatement(query);
            ps.setInt(1, task.getTId());
            ps.setString(2, task.getTaskName());
            ps.setString(3, task.getTaskDescription());
            ps.setString(4, task.getTaskStatus());


            result = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;


    }
}