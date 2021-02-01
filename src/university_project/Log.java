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

    public Log(String Course_no, String Course_title, int Attendent_Student, String Conducted_At) {
        this.Course_no = new SimpleStringProperty(Course_no);
        this.Course_title = new SimpleStringProperty(Course_title);

        this.Attendent_Student = new SimpleIntegerProperty(Attendent_Student);
        this.Conducted_At = new SimpleStringProperty(Conducted_At);

    }

    public Log() {

    }

    public String getCourse_no() {
        return Course_no.get();
    }

    public void setCourse_no(String Course_no) {
        this.Course_no = new SimpleStringProperty(Course_no);
    }

    public String getCourse_title() {
        return Course_title.get();
    }

    public void setCourse_title(String Course_title) {
        this.Course_title = new SimpleStringProperty(Course_title);
    }

    public int getAttendent_Student() {
        return Attendent_Student.get();
    }

    public void setAttendent_Student(int Attendent_Student) {
        this.Attendent_Student = new SimpleIntegerProperty(Attendent_Student);
    }

    public String getConducted_At() {
        return Conducted_At.get();
    }

    public void setConducted_At(String Conducted_At) {
        this.Conducted_At = new SimpleStringProperty(Conducted_At);
    }

}
