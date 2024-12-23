package com.freddie.to_do_list_app;

import com.freddie.to_do_list_app.DBConnectivity.DBConnectivity;
import com.freddie.to_do_list_app.Task.Task;
import com.freddie.to_do_list_app.Task.TaskRepo;
import com.freddie.to_do_list_app.User.User;
import com.freddie.to_do_list_app.User.UserRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField usernametxt, passwordtxt;
    @FXML
    private Button signuptxt;
    @FXML
    private TextArea textFieldTaskname;
    @FXML
    private TextArea textFieldDescription;
    @FXML
    private TextArea textFieldId;
    @FXML
    private TextArea textFieldStatus;
    @FXML
    private Button backButton, loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label signUpMessageLabel;
    @FXML
    private Label taskMessageLabel;


    @FXML
    private void submitUserDetails() {
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();

        if (username.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a username");
            alert.showAndWait();
        } else if (password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a password");
            alert.showAndWait();

        } else {

            User user = new User();
            user.setName(username);
            user.setPassword(password);

            int result = UserRepo.insert(user);
            if (result == 1) {
                signUpMessageLabel.setText("Admin Detail not saved successfully");

            } else {

                signUpMessageLabel.setText("Admin Detail saved successfully");
            }

        }


    }


    @FXML
    private void loadLogin(ActionEvent event) throws SQLException {
        if (!usernametxt.getText().isEmpty() && !passwordtxt.getText().isEmpty()) {
            onHelloButtonClick();
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }

    }


    public void onHelloButtonClick() throws SQLException {
        DBConnectivity connectivity = DBConnectivity.getInstance();

        String username = usernametxt.getText();
        String password = passwordtxt.getText();


        String query = "Select count(1) from user where username = '" + username + "' and password = '" + password + "';";
        PreparedStatement ps = connectivity.getConnection().prepareStatement(query);


        try {
            Statement statement = connectivity.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    loginMessageLabel.setText("Login successful");

                    switchToTasks_view();


                } else {
                    loginMessageLabel.setText("Login failed");
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());

        }


    }

    @FXML
    public void switchToSignup() throws IOException {
        Stage stage = (Stage) signuptxt.getScene().getWindow();
        stage.close();

        Parent root = null;
        root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("Signup.fxml")));
        stage.setScene(new Scene(root));
        stage.show();

    }


    public void switchToTask_view(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/freddie/to_do_list_app/To_do_list_App-view.fxml")));
        primaryStage.setScene(new Scene(root, 564, 313));
        primaryStage.show();
    }

    @FXML
    public void switchToTasks_view() throws IOException {

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        Parent root = null;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Tasks-view.fxml")));
        stage.setScene(new Scene(root));
        stage.show();


    }

    @FXML
    public void submit() throws SQLException {
        String taskId = textFieldId.getText();
        String TaskName = textFieldTaskname.getText();
        String Description = textFieldDescription.getText();
        String Status = textFieldStatus.getText();


        if (taskId.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a task id");
            alert.showAndWait();
            return;
        } else if (TaskName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a task name");
            alert.showAndWait();
            return;
        } else if (Description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a description");
            alert.showAndWait();
            return;
        } else if (Status.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a status(Active/Completed");
            alert.showAndWait();
            return;

        } else {
            int me = Integer.parseInt(taskId);


            Task task = new Task();
            task.setTId(Integer.parseInt(taskId));
            task.setTaskName(TaskName);
            task.setTaskDescription(Description);
            task.setTaskStatus(Status);

            int res = TaskRepo.insertTask(task);
            if (res > 0) {
                taskMessageLabel.setText("Data save successfully");

            } else {
                taskMessageLabel.setText("Failed to save data");
            }
        }

    }

    @FXML
    private void clear() {
        textFieldTaskname.clear();
        textFieldDescription.clear();
        textFieldId.clear();
        textFieldStatus.clear();

    }
}


















