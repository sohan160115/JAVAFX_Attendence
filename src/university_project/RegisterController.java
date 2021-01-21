/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university_project;

import java.beans.Statement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author sohan
 */
public class RegisterController implements Initializable {
    
    @FXML
    private ComboBox<String> combobox;
    ObservableList<String> list = FXCollections.observableArrayList("Teacher", "Head");

    @FXML
    private TextField tuser;
    @FXML
    private PasswordField tpass;
    @FXML
    private TextField temail;
    @FXML
    private Button btnRegister;
    
    Connection con;
    Statement st;
    PreparedStatement statement;
    ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         combobox.setItems(list);
    }    

    @FXML
    private void RegisterAction(ActionEvent event) {
        
         if(combobox.getValue()=="Head"){
            dbHead();
        }
        else{
           dbTeacher();
        }
    }
    
     static void alertbox(String msg){
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.initStyle(StageStyle.UTILITY);
                       alert.setTitle("Popup Message");
                       alert.setHeaderText(null);
                       alert.setContentText(msg);
                       alert.showAndWait();
    }
    
     public void dbTeacher(){
        
        String name= tuser.getText();
        String password= tpass.getText();
        String email= temail.getText();
        
         if(name.equals("") && password.equals("")){
               
                alertbox("Username or password blank");
         }
         
         else{
             
             try{
                  con= DriverManager.getConnection("jdbc:mysql://localhost/attendence","root", "");
                  String sql= "INSERT into teachers(name,email,password)" + "VALUES(?,?,?)";
                  statement= con.prepareStatement(sql);
                  statement.setString(1,name);
                  statement.setString(2,email);
                  statement.setString(3,password);
                  int rows=statement.executeUpdate();
                  
                  if(rows>0){
                      alertbox("Your information is succesfully recorded in Database");
                  }
                  
          
             }
             catch(SQLException ex){
                 
                 alertbox("Not connected to the database");
                 
             }
             
         }
        
    }
     
      public void dbHead(){
        
        String name= tuser.getText();
        String password= tpass.getText();
        String email= temail.getText();
        
         if(name.equals("") && password.equals("")){
               System.out.println("Username or password blank");
               
         }
         
         else{
             
             try{
                  con= DriverManager.getConnection("jdbc:mysql://localhost/attendence","root", "");
                  String sql= "INSERT into heads(name,email,password)" + "VALUES(?,?,?)";
                  statement= con.prepareStatement(sql);
                  statement.setString(1,name);
                  statement.setString(2,email);
                  statement.setString(3,password);
                  int rows=statement.executeUpdate();
                  
                  if(rows>0){
                       System.out.println("One row Added");
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.initStyle(StageStyle.UTILITY);
                       alert.setTitle("success");
                       alert.setHeaderText(null);
                       alert.setContentText("Infromatin Message");
                       alert.showAndWait();
                       
                  }
                  
          
             }
             catch(SQLException ex){
                 System.out.println("Not connected to the database");
                 
             }
             
         }
        
    }
    
}