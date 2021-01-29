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
    private Label totalSection;
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
    private TableView<Att> tableView;
    @FXML
    private TableColumn<Att, Integer> colRoll;
    @FXML
    private TableColumn<Att, String> colName;
    @FXML
    private TableColumn<Att, String> colEmail;
    @FXML
    private TableColumn<Att, String> colPhone;
    @FXML
    private TableColumn<Att, String> colPresent;
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
         colRoll.setCellValueFactory(new PropertyValueFactory<>("Roll"));
         colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
         colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
         colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
         colPresent.setCellValueFactory(new PropertyValueFactory<>("select"));
        
         try{
                          
                           String sq="SELECT students.roll, students.name, students.email, students.phone FROM `students` JOIN course_student JOIN courses ON students.id= course_student.student_id AND course_student.course_id=courses.id AND courses.course_no=?";
                           statement= con.prepareStatement(sq);
                           statement.setString(1,combobox.getSelectionModel().getSelectedItem());
                          result= statement.executeQuery();
                          ArrayList<String> IsAttendent = new ArrayList<String>();
                          
                          while(result.next()){
                              
                     
                              //public CheckBox ckl= new CheckBox();
                               
                              int roll= result.getInt("roll");
                              String name= result.getString("name");
                              String email= result.getString("email");
                              String phone= result.getString("phone");
                              //ckList.add(new CheckBox().setId());
                              
                                         CheckBox c=new CheckBox();
                                           c.setId(""+ roll);
                                        

                                   c.setOnAction(event -> {
                                          //IsAttendent.add(c.getId())
                                          System.out.println(c.getId());
                                          
                                               if(c.isSelected()){
                                                   IsAttendent.add(c.getId());
                                               }
                                               else{
                                                   IsAttendent.remove(c.getId());
                                               }
                                    
                                  });
                                  // colPresent.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Att, String>, ObservableValue<String>>) c);
                                  // colPresent.setCellValueFactory();
                              Att att= new Att(roll,name,email, phone);
                              
                               tableView.getItems().add(att);
                          }
                         
                          
                       }
                       catch(SQLException ex){
                           alertbox("Not connected to the databasetabel");
                       }
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
        series.getData().add(new XYChart.Data<>("CSE 1101", 63));
         series.getData().add(new XYChart.Data<>("CSE 1201", 51));
          series.getData().add(new XYChart.Data<>("CSE 2101", 27));
          barchart.getData().add(series);
          
            XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            series1.setName("Class Scheduled");
            series1.getData().add(new XYChart.Data<>("CSE 1101", 20));
            series1.getData().add(new XYChart.Data<>("CSE 1201", 45));
            series1.getData().add(new XYChart.Data<>("CSE 2101", 23));
            barchart.getData().add(series1);
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
          tableView.getItems().clear();
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
