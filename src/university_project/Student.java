package university_project;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.List;


public class Student{

    public SimpleIntegerProperty id;
    public SimpleStringProperty name,email,phone;
    public CheckBox checkbox=new CheckBox();

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    
     public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }


    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }
    
    
   public Student(){

   }
    public Student(SimpleIntegerProperty id, SimpleStringProperty name, SimpleStringProperty email,SimpleStringProperty phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone =phone;
    }

}