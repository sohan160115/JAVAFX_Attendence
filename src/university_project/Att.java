/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university_project;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author sohan
 */
public class Att {
    SimpleIntegerProperty Roll;
    SimpleStringProperty Name;
    SimpleStringProperty Email;
    SimpleStringProperty Phone;
    public CheckBox select;
    CheckBox checkBox;
   
    

    public Att(int Roll, String Name, String Email, String Phone,CheckBox select) {
        this.Roll = new SimpleIntegerProperty(Roll);
        this.Name = new SimpleStringProperty(Name);
        this.Email = new SimpleStringProperty(Email);
        this.Phone = new SimpleStringProperty (Phone);
        this.select = new CheckBox();
    }

    Att() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public int getRoll() {
        return Roll.get();
    }

    public void setRoll(int Roll) {
        this.Roll = new SimpleIntegerProperty(Roll);
    }

    public String getName() {
        return Name.get();
    }

    public void setName(String Name) {
        this.Name = new SimpleStringProperty(Name);
    }

    public String getEmail() {
        return Email.get();
    }

    public void setEmail(String Email) {
        this.Email = new SimpleStringProperty(Email);
    }

    public String getPhone() {
        return Phone.get();
    }

    public void setPhone(String Phone) {
        this.Phone = new SimpleStringProperty(Phone);
    }
    
    public CheckBox getSelect(){
        return select;
    }
    
    public void setSelect(CheckBox select){
        
        this.select=select;
        
    }


    
    
}
