/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university_project;

import java.awt.Checkbox;
import java.beans.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    Boolean ck=true;
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
        
    }   
    
    
     public void Connect(){
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost/attendence","root", "");
        } catch (SQLException ex) {
             alertbox("Not connected to ");
        }
    }
     
     public void fillCombobox(){
         
        try {
            String sq= "SELECT id,course_no from courses";
            statement= con.prepareStatement(sq);
            result= statement.executeQuery(sq);
             while(result.next()){
                            
                              String name= result.getString("course_no");
                              Integer id = result.getInt("id");
                              options.add(name);
                              
                              
                          }
             combobox.setItems(options);
        } catch (SQLException ex) {
           alertbox("Not ");
        }
        
        
        
     }
     
     public void fillComboboxR(){
                 try {
            String sq= "SELECT id,course_no from courses";
            statement= con.prepareStatement(sq);
            result= statement.executeQuery(sq);
             while(result.next()){
                            
                              String name= result.getString("course_no");
                              Integer id = result.getInt("id");
                              optionsR.add(name);
                              
                              
                          }
             comboboxR.setItems(optionsR);
        } catch (SQLException ex) {
           alertbox("No");
        }
     }
    
     
     
     
     public void table(){
         ArrayList<Integer> present_id=new ArrayList<>();
         colRoll.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
         colName.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
         colEmail.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
         colPhone.setCellValueFactory(new PropertyValueFactory<Student,String>("phone"));
         colPresent.setCellValueFactory(new PropertyValueFactory<Student,CheckBox>("checkbox"));
         
         ObservableList<Student> data= FXCollections.observableArrayList();
        
         try{
                          
                           String sq="SELECT students.roll, students.name, students.email, students.phone FROM `students` JOIN course_student JOIN courses ON students.id= course_student.student_id AND course_student.course_id=courses.id AND courses.course_no=?";
                           statement= con.prepareStatement(sq);
                           statement.setString(1,combobox.getSelectionModel().getSelectedItem());
                          result= statement.executeQuery();
                          

                          ObservableList<Att> students= FXCollections.observableArrayList();
                          
                          while(result.next()){
                              Student std=new Student();
                              int id= result.getInt("roll");
                              std.id=new SimpleIntegerProperty(result.getInt("roll"));
                              std.name=new SimpleStringProperty(result.getString("name"));
                              std.email=new SimpleStringProperty(result.getString("email"));
                              std.phone=new SimpleStringProperty(result.getString("phone"));
                              
                              std.getCheckbox().setOnAction(event -> {
                                      if(std.getCheckbox().isSelected()){
                                               present_id.add(id);
                                            }
                                      else{
                                        present_id.remove(id);
                                      }
                                });
                           data.add(std);
                          }
                            tableView.getColumns().clear();
                           tableView.getColumns().addAll(colRoll,colName,colEmail,colPhone,colPresent);
                           tableView.setItems(data);
                          //tableView.setItems(students);
                       }
                       catch(SQLException ex){
                           alertbox("Not connected to the databasetabel");
                       }
         btnsubmit.setOnAction(event -> {
            System.out.println(present_id);
        });
    }
         
     public void tableR(){
         colRollR.setCellValueFactory(new PropertyValueFactory<>("Roll"));
         colNameR.setCellValueFactory(new PropertyValueFactory<>("Name"));
         colEmailR.setCellValueFactory(new PropertyValueFactory<>("Email"));
         colPhoneR.setCellValueFactory(new PropertyValueFactory<>("Phone"));
         colPercentageR.setCellValueFactory(new PropertyValueFactory<>("Percentage"));
          try{
                          
                           String sq="SELECT students.roll, students.name, students.email, students.phone FROM `students` JOIN course_student JOIN courses ON students.id= course_student.student_id AND course_student.course_id=courses.id AND courses.course_no=?";
                           statement= con.prepareStatement(sq);
                            statement.setString(1,comboboxR.getSelectionModel().getSelectedItem());
                          result= statement.executeQuery();
                          while(result.next()){
                              int roll= result.getInt("roll");
                              String name= result.getString("name");
                              String email= result.getString("email");
                              String phone= result.getString("phone");
                              AttR attr= new AttR(roll,name,email, phone,90);
                               TableViewR.getItems().add(attr);
                          }
                         
                          
                       }
                       catch(SQLException ex){
                           alertbox("Not connected to the databasetableR");
                       }
     }
     
    
     
     

    @FXML
    private void logoutAction(ActionEvent event) {
                       try{ 
                            String sq="UPDATE teachers SET Islogin=? where Islogin=?";
                           statement= con.prepareStatement(sq);
                           statement.setBoolean(1,false);
                           statement.setBoolean(2,true);
                          int rows= statement.executeUpdate();
                       }
                       catch(SQLException ex){
                           alertbox("Not connected to the databaselogout");
                       }
              Stage stage = (Stage) btnLogout.getScene().getWindow(); 
              stage.close();
              stageChange("FXMLDocument.fxml");
             
    }
    
    public void stageChange(String msg){
          try{
            Stage stage = new Stage();
        FXMLLoader fxmlloader= new FXMLLoader();
        Pane root = fxmlloader.load(getClass().getResource(msg).openStream());
        stage.setScene(new Scene(root));
        stage.showAndWait();
        }
        
        catch(IOException e){
            e.printStackTrace();
            
        }
    }
    public void getUnameEmail(){
        boolean Islogin = true;
         try{
                  
                  String sql= "SELECT * from teachers where Islogin=?";
                 
                  statement= con.prepareStatement(sql);
                 
                  statement.setBoolean(1, Islogin);
                  result=statement.executeQuery();
                  if(result.next()){
                      
                   
                      teacherUname.setText(result.getString("name"));
                      teacherEmail.setText(result.getString("email"));
                  }
                   
                  
                 
             }
             catch(SQLException ex){
                 alertbox("Not connected to the databaseUname");
                 
             }
    }
    public void totalStudentsf(){
          try{
                  String sql= "SELECT COUNT(*) as studentCount from students";
                 
                  statement= con.prepareStatement(sql);
                 
                  
                  result=statement.executeQuery();
                  
                  if(result.next()){
                     int nn= result.getInt("studentCount");
                     String s=String.valueOf(nn);
                      totalStudents.setText(s);
                  }
                  
                 
             }
             catch(SQLException ex){
                 alertbox("Not connected to the databasetotalst");
                 
             }
    }
    
    public void ScheduledLogged(){
                  try{
                  String sql= "SELECT course_teacher.scheduled, course_teacher.taken FROM course_teacher JOIN teachers on teachers.id= course_teacher.teacher_id AND teachers.Islogin= true";
                 
                  statement= con.prepareStatement(sql);
                 
                  
                  result=statement.executeQuery();
                  
                  if(result.next()){
                     int nn= result.getInt("scheduled");
                     int nm= result.getInt("taken");
                     String n=String.valueOf(nn);
                     String m= String.valueOf(nm);
                      Scheduled.setText(n);
                      logged.setText(m);
                  }
                  
                 
             }
             catch(SQLException ex){
                 alertbox("Not connected");
                 
             }
    }
    public void barchartf(){
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Class Logged");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Class Scheduled");
        
            try{
                  String sql= "SELECT courses.course_no, course_teacher.scheduled, course_teacher.taken FROM courses JOIN course_teacher JOIN teachers ON course_teacher.course_id= courses.id and course_teacher.teacher_id=teachers.id WHERE teachers.Islogin= true";
                 
                  statement= con.prepareStatement(sql);
                 
                  
                  result=statement.executeQuery();
                  
                  while(result.next()){
                     int nn= result.getInt("scheduled");
                     int nm= result.getInt("taken");
                     String pp= result.getString("course_no");
                     series1.getData().add(new XYChart.Data<>(pp, nn));
                     series.getData().add(new XYChart.Data<>(pp, nm));
               
                  }
                  
                  barchart.getData().add(series);
                   barchart.getData().add(series1);
                  
                 
             }
             catch(SQLException ex){
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
       
    }

    @FXML
    private void AttendenceRecordAction(ActionEvent event) {
        pane3.toFront();
    }

    @FXML
    private void ClassLogAction(ActionEvent event) {
        pane4.toFront();
    }

    @FXML
    private void setOnAction(ActionEvent event) {
        if(ck){
          //tableView.getItems().clear();
           tableView.getColumns().clear();
           table();
        }
       
       
        
    }

    @FXML
    private void setOnActionR(ActionEvent event) {
        if(ck){
          TableViewR.getItems().clear();
           tableR();
        }
    }
    
}
