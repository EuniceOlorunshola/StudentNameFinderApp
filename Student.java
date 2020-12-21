package com.example.studentnamefinder;

public class Student {
    // fields
    private int _id;
    private String _studentname;
    private String _studentemail;


    // constructors
    public Student() {
    }

    public Student(int id, String studentname, String studentemail) {
        this._id = id;
        this._studentname = studentname;
        this._studentemail = studentemail;

    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setStudentName(String studentname) {
        this._studentname = studentname;
    }

    public String getStudentName() {
        return this._studentname;
    }

    public void setStudentEmail(String studentemail) {
        this._studentemail = studentemail;
    }

    public String getStudentEmail() {
        return this._studentemail;
    }


}


