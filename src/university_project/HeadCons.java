/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university_project;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author sohan
 */
public class HeadCons {

    public SimpleStringProperty name, email, course;
    public SimpleIntegerProperty percentage;

    public HeadCons(String name, String course, String email, Integer percentage) {
        this.name = new SimpleStringProperty(name);
        this.course = new SimpleStringProperty(course);
        this.email = new SimpleStringProperty(email);
        this.percentage = new SimpleIntegerProperty(percentage);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getCourse() {
        return course.get();
    }

    public void setCourse(String course) {
        this.course = new SimpleStringProperty(course);
    }

    public int getPercentage() {
        return percentage.get();
    }

    public void setPercentage(int percentage) {
        this.percentage = new SimpleIntegerProperty(percentage);
    }

}
