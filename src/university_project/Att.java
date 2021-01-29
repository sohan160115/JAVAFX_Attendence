/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university_project;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author sohan
 */
public class Att {
    private SimpleIntegerProperty Roll;
    private SimpleStringProperty Name;
    private SimpleStringProperty Email;
    private SimpleStringProperty Phone;
    private SimpleBooleanProperty present;

    public Att(int Roll, String Name, String Email, String Phone, boolean present) {
        this.Roll = new SimpleIntegerProperty(Roll);
        this.Name = new SimpleStringProperty(Name);
        this.Email = new SimpleStringProperty(Email);
        this.Phone = new SimpleStringProperty (Phone);
        this.present = new SimpleBooleanProperty(present);
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
// Don't understand
    public SimpleBooleanProperty isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = new SimpleBooleanProperty(present);
    }
    
}
