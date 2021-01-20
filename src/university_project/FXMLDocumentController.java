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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author sohan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ComboBox<String> combobox;
    ObservableList<String> list = FXCollections.observableArrayList("Teacher", "Head");
    
    @FXML
    private Label label;
    @FXML
    private TextField tuser;
    @FXML
    private TextField tpass;
    
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegister;
    
    Connection con;
    Statement st;
    PreparedStatement statement;
    ResultSet result;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combobox.setItems(list);
    }    

    @FXML
    private void comboboxChange(ActionEvent event) {
    }

    @FXML
    private void LoginAction(ActionEvent event) {
        
        if(combobox.getValue()=="Head"){
            dbHead();
        }
        else{
            dbTeacher();
        }
    }
    
    public void dbTeacher(){
        
        String name= tuser.getText();
        String password= tpass.getText();
        
         if(name.equals("") && password.equals("")){
               System.out.println("Username or password blank");
         }
         
         else{
             
             try{
                  con= DriverManager.getConnection("jdbc:mysql://localhost/attendence","root", "");
                  String sql= "SELECT * from teachers where name=? and password=?";
                  statement= con.prepareStatement(sql);
                  statement.setString(1,name);
                  statement.setString(2,password);
                  result=statement.executeQuery();
                  
                  if(result.next()){
                       System.out.println("Login Succes");
                  }
                  
                  else{
                      System.out.println("Login Failed");
                  }
             }
             catch(SQLException ex){
                 System.out.println("Not connected to the database");
                 
             }
             
         }
        
    }
    
    public void dbHead(){
         String name= tuser.getText();
        String password= tpass.getText();
        
         if(name.equals("") && password.equals("")){
               System.out.println("Username or password blank");
         }
         
         else{
             
             try{
                  con= DriverManager.getConnection("jdbc:mysql://localhost/attendence","root", "");
                  String sql= "SELECT * from heads where name=? and password=?";
                  statement= con.prepareStatement(sql);
                  statement.setString(1,name);
                  statement.setString(2,password);
                  result=statement.executeQuery();
                  
                  if(result.next()){
                       System.out.println("Login Succes");
                  }
                  
                  else{
                      System.out.println("Login Failed");
                  }
             }
             catch(SQLException ex){
                 System.out.println("Not connected to the database");
                 
             }
             
         }
    }

    @FXML
    private void Register(ActionEvent event) {
         try{
            Stage stage = new Stage();
        FXMLLoader fxmlloader= new FXMLLoader();
        Pane root = fxmlloader.load(getClass().getResource("Register.fxml").openStream());
        stage.setScene(new Scene(root));
        stage.showAndWait();
        }
        
        catch(IOException e){
            e.printStackTrace();
            
        }
        
    }
    
}
