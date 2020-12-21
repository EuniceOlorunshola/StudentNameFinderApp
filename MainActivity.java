package com.example.studentnamefinder;


import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText stuname,enroll_no,email;
    Button add,view,viewall,Show1,delete,update;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        stuname= findViewById(R.id.name);
        enroll_no= findViewById(R.id.id_no);
        email= findViewById(R.id.email);
        add= findViewById(R.id.addbtn);
        view= findViewById(R.id.viewbtn);
        viewall= findViewById(R.id.viewallbtn);
        delete= findViewById(R.id.deletebtn);
        Show1= findViewById(R.id.showbtn);
        update= findViewById(R.id.updatebtn);

        db=openOrCreateDatabase("Student_name", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(id_no INTEGER,name VARCHAR,email VARCHAR);");


        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(enroll_no.getText().toString().trim().length()==0||
                        stuname.getText().toString().trim().length()==0||
                        email.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('"+enroll_no.getText()+"','"+stuname.getText()+
                        "','"+email.getText()+"');");
                showMessage("Success", "Student Record added successfully");
                clearText();
            }
        });
        delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(stuname.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Student Name");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE name='"+stuname.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("DELETE FROM student WHERE name='"+stuname.getText()+"'");
                    showMessage("Success", "Student Record Deleted");
                }
                else
                {
                    showMessage("Error", "Invalid Student Name");
                }
                clearText();
            }
        });
        update.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(stuname.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Student Name");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE name='"+stuname.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("UPDATE student SET idno='"+enroll_no.getText()+"','"+email.getText()+
                            "' WHERE name='"+stuname.getText()+"'");
                    showMessage("Success", "Record Modified");
                }
                else
                {
                    showMessage("Error", "Invalid Student Name");
                }
                clearText();
            }
        });
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(stuname.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Student Name");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE name='"+stuname.getText()+"'", null);
                if(c.moveToFirst())
                {
                    enroll_no.setText(c.getString(1));
                    email.setText(c.getString(2));
                }
                else
                {
                    showMessage("Error", "Invalid Student Name");
                    clearText();
                }
            }
        });
        viewall.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Cursor c=db.rawQuery("SELECT * FROM student", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("ID: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("Email: "+c.getString(2)+"\n\n");
                }
                showMessage("Student Details", buffer.toString());
            }
        });
        Show1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showMessage("Student Name Finder Application", "Developed By Eunice Olorunshola");
            }
        });

    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        enroll_no.setText("");
        stuname.setText("");
        email.setText("");
       stuname.requestFocus();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_main, menu);
        return true;
    }

}
