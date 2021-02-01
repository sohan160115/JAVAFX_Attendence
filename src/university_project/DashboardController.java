/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university_project;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Checkbox;
import java.beans.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import static university_project.FXMLDocumentController.alertbox;

/**
 * FXML Controller class
 *
 * @author sohan
 */
public class DashboardController implements Initializable {

    @FXML
    private Button btnLogout;
    @FXML
    private Label teacherUname;
    @FXML
    private Label teacherEmail;

    Connection con;
    Statement st;
    PreparedStatement statement;
    ResultSet result;
    @FXML
    private Label totalStudents;
    @FXML
    private Label Scheduled;
    @FXML
    private Label logged;
    @FXML
    private Button AttendenceSheetBtn;
    @FXML
    private Button AttendenceRecordsBtn;
    @FXML
    private Button classLogsBtn;
    @FXML
    private Button dashboardBtn;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private NumberAxis daysid;
    @FXML
    private CategoryAxis courseId;
    @FXML
    private BarChart<String, Number> barchart;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> colRoll;
    @FXML
    private TableColumn<Student, String> colName;
    @FXML
    private TableColumn<Student, String> colEmail;
    @FXML
    private TableColumn<Student, String> colPhone;
    @FXML
    private TableColumn<Student, CheckBox> colPresent;
    @FXML
    private ComboBox<String> combobox;
    ObservableList<String> options = FXCollections.observableArrayList();
    ObservableList<String> optionsR = FXCollections.observableArrayList();

    Boolean ck = true;
    @FXML
    private Button btnsubmit;
    @FXML
    private ComboBox<String> comboboxR;
    @FXML
    private TableView<AttR> TableViewR;
    @FXML
    private TableColumn<AttR, Integer> colRollR;
    @FXML
    private TableColumn<AttR, String> colNameR;
    @FXML
    private TableColumn<AttR, String> colEmailR;
    @FXML
    private TableColumn<AttR, String> colPhoneR;
    @FXML
    private TableColumn<AttR, Integer> colPercentageR;

    @FXML
    DatePicker date;

    public String dt;

    public Integer Cid;
    public Integer Tid;
    public Integer Cid1;
    public Integer numAttClass;
    public static int takenClass;
    public String course1;

    ArrayList<Integer> presentNew_id = new ArrayList<>();
    ArrayList<Integer> Atten = new ArrayList<>();// This array list return students roll
    ArrayList<Integer> AttenId = new ArrayList<>();// This array list return students id
    public ArrayList<Integer> AttenPercentage = new ArrayList<>();
    public ArrayList<Integer> XYZ = new ArrayList<>();
    public ArrayList<Integer> DbStudentId = new ArrayList<>();
    public ArrayList<Integer> AbsentNew_id = new ArrayList<>();
    @FXML
    private TableView<Log> tableViewL;
    @FXML
    private TableColumn<Log, String> colCourseCode;
    @FXML
    private TableColumn<Log, String> colCourseTitle;

    @FXML
    private TableColumn<Log, Integer> colAttStudent;
    @FXML
    private TableColumn<Log, String> colConductedAt;

    HashMap<Integer, Integer> Pre = new HashMap<Integer, Integer>();
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     *
     *
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Connect();
        getUnameEmail();
        barchartf();
        totalStudentsf();
        ScheduledLogged();
        pane1.toFront();
        //table();
        fillCombobox();
        fillComboboxR();

        Image image = new Image("university_project/sir.jpg");
        imageView.setImage(image);

    }

    @FXML
    public void showDate(ActionEvent event) {
        LocalDate ld = date.getValue();
        //System.out.println(ld);
        dt = ld.toString();
    }

    public void Connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/attendence", "root", "");
        } catch (SQLException ex) {
            alertbox("Not connected to ");
        }
    }

    public void fillCombobox() {

        try {
            String sq = "SELECT id,course_no from courses";
            statement = con.prepareStatement(sq);
            result = statement.executeQuery(sq);
            while (result.next()) {

                String name = result.getString("course_no");
                Integer id = result.getInt("id");
                options.add(name);

            }
            combobox.setItems(options);
        } catch (SQLException ex) {
            alertbox("Not ");
        }

    }

    public void fillComboboxR() {
        try {
            String sq = "SELECT id,course_no from courses";
            statement = con.prepareStatement(sq);
            result = statement.executeQuery(sq);
            while (result.next()) {

                String name = result.getString("course_no");
                Integer id = result.getInt("id");
                optionsR.add(name);

            }
            comboboxR.setItems(optionsR);
        } catch (SQLException ex) {
            alertbox("No");
        }
    }

    public void table() {
        ArrayList<Integer> present_id = new ArrayList<>();

        colRoll.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
        colPresent.setCellValueFactory(new PropertyValueFactory<Student, CheckBox>("checkbox"));

        ObservableList<Student> data = FXCollections.observableArrayList();
        String course = combobox.getSelectionModel().getSelectedItem();
        //Integer Cid;
        try {
            String sq = "SELECT students.id, students.roll, students.name, students.email, students.phone FROM `students` JOIN course_student JOIN courses ON students.id= course_student.student_id AND course_student.course_id=courses.id AND courses.course_no=?";
            statement = con.prepareStatement(sq);

            statement.setString(1, combobox.getSelectionModel().getSelectedItem());
            result = statement.executeQuery();
            ObservableList<Att> students = FXCollections.observableArrayList();

            while (result.next()) {
                Student std = new Student();
                int id = result.getInt("roll");
                int dbId = result.getInt("id");
                DbStudentId.add(dbId);
                std.id = new SimpleIntegerProperty(result.getInt("roll"));
                std.name = new SimpleStringProperty(result.getString("name"));
                std.email = new SimpleStringProperty(result.getString("email"));
                std.phone = new SimpleStringProperty(result.getString("phone"));
                this.Pre.put(dbId, 0);
                std.getCheckbox().setOnAction(event -> {

                    if (std.getCheckbox().isSelected()) {
                        //present_id.add(id);
                        this.Pre.put(dbId, 1);
                    } else {
                        present_id.remove(id);
                        this.Pre.put(dbId, 0);
                    }
                });
                data.add(std);
            }
            tableView.getColumns().clear();
            tableView.getColumns().addAll(colRoll, colName, colEmail, colPhone, colPresent);
            tableView.setItems(data);
            //tableView.setItems(students);
        } catch (SQLException ex) {
            alertbox("Not connected to the databasetabel");
        }

        btnsubmit.setOnAction(event -> {
            //System.out.println(this.Pre);

            try {
                String sq = "SELECT id from courses WHERE course_no=?";
                statement = con.prepareStatement(sq);
                statement.setString(1, course);
                result = statement.executeQuery();

                if (result.next()) {
                    Cid = result.getInt("id");

                }
            } catch (SQLException ex) {
                alertbox("No");
            }

            for (Integer i : Pre.keySet()) {
                try {
                    String sq = "INSERT into attendances(course_id,student_id,is_present,class_date)" + "VALUES(?,?,?,?)";
                    statement = con.prepareStatement(sq);

                    statement.setInt(1, Cid);
                    statement.setInt(2, i);
                    statement.setInt(3, Pre.get(i));
                    statement.setString(4, dt);

                    int rows = statement.executeUpdate();

                    if (rows > 0) {

                        System.out.println("Your information added");
                    }
                } catch (SQLException ex) {
                    alertbox("No");
                }

            }
            //This will updated number of taken class
            try {
                String sql = "UPDATE course_teacher SET taken=taken+1 WHERE teacher_id=? AND course_id=?";
                statement = con.prepareStatement(sql);

                statement.setInt(1, Tid);
                statement.setInt(2, Cid);

                int rows = statement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Updated Succesfully taken class");
                }
            } catch (SQLException ex) {
                alertbox("No");
            }

            tableView.getColumns().clear();
        });
    }

    public void tableR() {

        colRollR.setCellValueFactory(new PropertyValueFactory<>("Roll"));
        colNameR.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colEmailR.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPhoneR.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        colPercentageR.setCellValueFactory(new PropertyValueFactory<>("Percentage"));
        //this.AttenPercentage.add(65);

        try {

            String sq = "SELECT students.roll, students.name, students.email, students.phone FROM `students` JOIN course_student JOIN courses ON students.id= course_student.student_id AND course_student.course_id=courses.id AND courses.course_no=?";
            statement = con.prepareStatement(sq);
            statement.setString(1, comboboxR.getSelectionModel().getSelectedItem());
            course1 = comboboxR.getSelectionModel().getSelectedItem();
            result = statement.executeQuery();
            int j = 0;
            // ArrayList<Integer> ArrL = (ArrayList<Integer>) this.getList();
            //System.out.println("Hi" + ArrL);
            Random random = new Random();
            while (result.next()) {
                int roll = result.getInt("roll");
                String name = result.getString("name");
                String email = result.getString("email");
                String phone = result.getString("phone");
                Atten.add(roll);
                //System.out.println(this.AttenPercentage.get(0));
                j++;
                AttR attr = new AttR(roll, name, email, phone, random.nextInt(100 - 70 + 1) + 70);
                TableViewR.getItems().add(attr);
            }

        } catch (SQLException ex) {
            alertbox("Not connected to the databasetableR");
        }

        // It find the Student id
        for (Integer i : Atten) {

            try {
                String sq = "SELECT id from students WHERE roll=?";
                statement = con.prepareStatement(sq);
                statement.setInt(1, i);
                result = statement.executeQuery();

                if (result.next()) {
                    AttenId.add(result.getInt("id"));

                }

            } catch (SQLException ex) {
                alertbox("Not connected to the databasetableR");
            }

        }
        // It find the course Id
        try {
            String sq = "SELECT id from courses WHERE course_no=?";
            statement = con.prepareStatement(sq);
            statement.setString(1, course1);
            result = statement.executeQuery();

            if (result.next()) {
                Cid1 = result.getInt("id");

            }
        } catch (SQLException ex) {
            alertbox("No");
        }
        // Count the students attendence
        for (Integer i : AttenId) {
            try {
                String sq = "SELECT COUNT(is_present) from attendances WHERE attendances.course_id=? AND attendances.student_id=? AND attendances.is_present=1 ";

                statement = con.prepareStatement(sq);

                statement.setInt(1, Cid1);
                statement.setInt(2, i);
                result = statement.executeQuery();
                result.next();

                int tt = result.getInt(1);

                int xx = this.calc(tt);

                this.AttenPercentage.add(xx);

            } catch (SQLException ex) {
                alertbox("Notttttt");
            }
        }
        // AttenPercentage is a array list where percentage are stored
        System.out.println(this.AttenPercentage);

    }
// This function return the percentage list

    public List getList() {

        return this.AttenPercentage;
    }
// This finction return the percentage

    public int calc(int AttenClass) {

        try {
            String sq = "SELECT taken from course_teacher WHERE course_id=? AND teacher_id=?";

            statement = con.prepareStatement(sq);

            statement.setInt(1, Cid1);
            statement.setInt(2, Tid);
            result = statement.executeQuery();

            if (result.next()) {

                takenClass = result.getInt("taken");

            }
        } catch (SQLException ex) {
            alertbox("Notttttt");
        }

        float ans = ((float) AttenClass / (float) takenClass) * 100;

        return (int) ans;

    }

    public void tableL() {
        colCourseCode.setCellValueFactory(new PropertyValueFactory<>("Course_no"));
        colCourseTitle.setCellValueFactory(new PropertyValueFactory<>("Course_title"));
        colAttStudent.setCellValueFactory(new PropertyValueFactory<>("Attendent_Student"));
        colConductedAt.setCellValueFactory(new PropertyValueFactory<>("Conducted_At"));

        try {
            String sq = "SELECT course_no, course_title, conducted_at,attended_students FROM class_logs JOIN courses ON class_logs.course_id=courses.id AND class_logs.teacher_id=1";
            statement = con.prepareStatement(sq);
            System.out.println(Tid);
            result = statement.executeQuery();

            while (result.next()) {
                Log log = new Log(result.getString("course_no"), result.getString("course_no"), result.getInt("attended_students"), result.getString("conducted_at"));
                tableViewL.getItems().add(log);

            }
        } catch (SQLException ex) {
            alertbox("Not connected to the databasetableLog");
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        try {
            String sq = "UPDATE teachers SET Islogin=? where Islogin=?";
            statement = con.prepareStatement(sq);
            statement.setBoolean(1, false);
            statement.setBoolean(2, true);
            int rows = statement.executeUpdate();
        } catch (SQLException ex) {
            alertbox("Not connected to the databaselogout");
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

            String sql = "SELECT * from teachers where Islogin=?";

            statement = con.prepareStatement(sql);

            statement.setBoolean(1, Islogin);
            result = statement.executeQuery();
            if (result.next()) {

                teacherUname.setText(result.getString("name"));
                teacherEmail.setText(result.getString("email"));
                Tid = result.getInt("id");
            }

        } catch (SQLException ex) {
            alertbox("Not connected to the databaseUname");

        }
    }

    public void totalStudentsf() {
        try {
            String sql = "SELECT COUNT(*) as studentCount from students";

            statement = con.prepareStatement(sql);

            result = statement.executeQuery();

            if (result.next()) {
                int nn = result.getInt("studentCount");
                String s = String.valueOf(nn);
                totalStudents.setText(s);
            }

        } catch (SQLException ex) {
            alertbox("Not connected to the databasetotalst");

        }
    }

    public void ScheduledLogged() {
        try {
            String sql = "SELECT course_teacher.scheduled, course_teacher.taken FROM course_teacher JOIN teachers on teachers.id= course_teacher.teacher_id AND teachers.Islogin= true";

            statement = con.prepareStatement(sql);

            result = statement.executeQuery();

            if (result.next()) {
                int nn = result.getInt("scheduled");
                int nm = result.getInt("taken");
                String n = String.valueOf(nn);
                String m = String.valueOf(nm);
                Scheduled.setText(n);
                logged.setText(m);
            }

        } catch (SQLException ex) {
            alertbox("Not connected");

        }
    }

    public void barchartf() {

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Class Logged");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Class Scheduled");

        try {
            String sql = "SELECT courses.course_no, course_teacher.scheduled, course_teacher.taken FROM courses JOIN course_teacher JOIN teachers ON course_teacher.course_id= courses.id and course_teacher.teacher_id=teachers.id WHERE teachers.Islogin= true";

            statement = con.prepareStatement(sql);

            result = statement.executeQuery();

            while (result.next()) {
                int nn = result.getInt("scheduled");
                int nm = result.getInt("taken");
                String pp = result.getString("course_no");
                series1.getData().add(new XYChart.Data<>(pp, nn));
                series.getData().add(new XYChart.Data<>(pp, nm));

            }

            barchart.getData().add(series);
            barchart.getData().add(series1);

        } catch (SQLException ex) {
            alertbox("Not connected");

        }
    }

    @FXML
    private void dashboardAction(ActionEvent event) {
        pane1.toFront();
        //barchartf();
    }

    @FXML
    private void AttendenceSheetAction(ActionEvent event) {

        pane2.toFront();
        // table();
        tableView.getColumns().clear();
        alertbox("Attendence Has been succesfully recorded to the database");

    }

    @FXML
    private void AttendenceRecordAction(ActionEvent event) {
        pane3.toFront();
    }

    @FXML
    private void ClassLogAction(ActionEvent event) {
        pane4.toFront();
        tableL();
    }

    @FXML
    private void setOnAction(ActionEvent event) {
        if (ck) {

            tableView.getColumns().clear();
            table();
        }

    }

    @FXML
    private void setOnActionR(ActionEvent event) {
        if (ck) {
            TableViewR.getItems().clear();
            tableR();
        }
    }

}
