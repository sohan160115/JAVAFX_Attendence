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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

    /**
     * Initializes the controller class.
     * 
     * 
     * 
     */
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getUnameEmail();
        barchartf();
        
    }    

    @FXML
    private void logoutAction(ActionEvent event) {
                      
                       
                       try{
                           con= DriverManager.getConnection("jdbc:mysql://localhost/attendence","root", "");
                            String sq="UPDATE teachers SET Islogin=? where Islogin=?";
                           statement= con.prepareStatement(sq);
                           statement.setBoolean(1,false);
                           statement.setBoolean(2,true);
                           
                          
                          int rows= statement.executeUpdate();
                       }
                       catch(SQLException ex){
                           alertbox("Not connected to the database");
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
                  con= DriverManager.getConnection("jdbc:mysql://localhost/attendence","root", "");
                  String sql= "SELECT * from teachers where Islogin=?";
                 
                  statement= con.prepareStatement(sql);
                  statement.setBoolean(1,Islogin);
                  
                  result=statement.executeQuery();
                  if(result.next()){
                      
                      System.out.println(result.getString("name"));
                      teacherUname.setText(result.getString("name"));
                      teacherEmail.setText(result.getString("email"));
                  }
                   
                  
                 
             }
             catch(SQLException ex){
                 alertbox("Not connected to the database");
                 
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
    }

    @FXML
    private void AttendenceRecordAction(ActionEvent event) {
        pane3.toFront();
    }

    @FXML
    private void ClassLogAction(ActionEvent event) {
        pane4.toFront();
    }
    
}
