/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university_project;

import java.beans.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static university_project.FXMLDocumentController.alertbox;

/**
 * FXML Controller class
 *
 * @author sohan
 */
public class Dashboard_HeadController implements Initializable {

    @FXML
    private Button btnLogout;
    @FXML
    private Button headDashboardBtn;
    @FXML
    private Label headUname;
    @FXML
    private Label headEmail;

    Connection con;
    Statement st;
    PreparedStatement statement;
    ResultSet result;
    @FXML
    private TableView<HeadCons> tableView;
    @FXML
    private TableColumn<HeadCons, String> colTeacherName;
    @FXML
    private TableColumn<HeadCons, String> colCourseCode;
    @FXML
    private TableColumn<HeadCons, String> colTeacherEmail;
    @FXML
    private TableColumn<HeadCons, Integer> colPercentage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
        getUnameEmail();
        tableHead();
    }

    public void Connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/attendence", "root", "");
        } catch (SQLException ex) {
            alertbox("Not connected to the database");
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) {

        try {

            String sq = "UPDATE heads SET Islogin=? where Islogin=?";
            statement = con.prepareStatement(sq);
            statement.setBoolean(1, false);
            statement.setBoolean(2, true);

            int rows = statement.executeUpdate();
        } catch (SQLException ex) {
            alertbox("Not connected to the database");
        }

        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
        stageChange("FXMLDocument.fxml");
    }

    public void stageChange(String msg) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlloader = new FXMLLoader();
            Pane root = fxmlloader.load(getClass().getResource(msg).openStream());
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void getUnameEmail() {
        boolean Islogin = true;
        try {

            String sql = "SELECT * from heads where Islogin=?";

            statement = con.prepareStatement(sql);
            statement.setBoolean(1, Islogin);

            result = statement.executeQuery();
            if (result.next()) {

                System.out.println(result.getString("name"));
                headUname.setText(result.getString("name"));
                headEmail.setText(result.getString("email"));
            }

        } catch (SQLException ex) {
            alertbox("Not connected to the database");

        }
    }

    public void tableHead() {

        colTeacherName.setCellValueFactory(new PropertyValueFactory<HeadCons, String>("name"));
        colCourseCode.setCellValueFactory(new PropertyValueFactory<HeadCons, String>("course"));
        colTeacherEmail.setCellValueFactory(new PropertyValueFactory<HeadCons, String>("email"));
        colPercentage.setCellValueFactory(new PropertyValueFactory<HeadCons, Integer>("percentage"));

        try {
            String sq = "SELECT teachers.name, courses.course_no, teachers.email,course_teacher.scheduled,course_teacher.taken FROM teachers JOIN course_teacher JOIN courses ON teachers.id=course_teacher.teacher_id AND courses.id=course_teacher.course_id";
            statement = con.prepareStatement(sq);
            result = statement.executeQuery();

            while (result.next()) {

                String name = result.getString("name");
                String course_no = result.getString("course_no");
                String email = result.getString("email");
                int sch = result.getInt("scheduled");
                int tkn = result.getInt("taken");
                double per = ((double) tkn / (double) sch) * 100;
                int per1 = (int) per;
                //System.out.println(sch + " " + tkn + " " + per1);
                HeadCons hecon = new HeadCons(name, course_no, email, per1);

                tableView.getItems().add(hecon);

            }

        } catch (SQLException ex) {
            alertbox("Not connected to the databasetabel");
        }
    }

}
