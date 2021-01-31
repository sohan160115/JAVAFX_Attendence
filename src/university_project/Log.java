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
public class Log {

    public SimpleStringProperty Course_no;
    public SimpleStringProperty Course_title;

    public SimpleIntegerProperty Attendent_Student;
    public SimpleStringProperty Conducted_At;

    public Log(SimpleStringProperty Course_no, SimpleStringProperty Course_title, SimpleIntegerProperty Attendent_Student, SimpleStringProperty Conducted_At) {
        this.Course_no = Course_no;
        this.Course_title = Course_title;
        //this.Date = new SimpleStringProperty(Date);
        this.Attendent_Student = Attendent_Student;
        this.Conducted_At = Conducted_At;
        //this.Conducted_At= new SimpleIntegerProperty(Conducted_At);
    }

    public Log() {

    }

    public SimpleStringProperty getCourse_no() {
        return Course_no;
    }

    public void setCourse_no(SimpleStringProperty Course_no) {
        this.Course_no = Course_no;
    }

    public SimpleStringProperty getCourse_title() {
        return Course_title;
    }

    public void setCourse_title(SimpleStringProperty Course_title) {
        this.Course_title = Course_title;
    }

    public SimpleIntegerProperty getAttendent_Student() {
        return Attendent_Student;
    }

    public void setAttendent_Student(SimpleIntegerProperty Attendent_Student) {
        this.Attendent_Student = Attendent_Student;
    }

    public SimpleStringProperty getConducted_At() {
        return Conducted_At;
    }

    public void setConducted_At(String Conducted_At) {
        this.Conducted_At = new SimpleStringProperty(Conducted_At);
    }

}
