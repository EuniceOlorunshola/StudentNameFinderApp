package com.example.studentnamefinder;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentDB.db";
    public static final String TABLE_STUDENTS = "students";
    public static final String COLUMN_ID = "StudentID";
    public static final String COLUMN_NAME = "StudentName";
    public static final String COLUMN_EMAIL="StudentEmail";



    public MyDBHandler(Context context, SQLiteDatabase.CursorFactory factory)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_STUDENTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT, " + COLUMN_EMAIL+" TEXT"+")";
        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);

    }

    public void addHandler(Student student) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getID());
        values.put(COLUMN_NAME, student.getStudentName());
        values.put(COLUMN_EMAIL,student.getStudentEmail());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }

    public Student findHandler(String studentname) {
        String query = "Select * FROM " + TABLE_STUDENTS + " WHERE " +
                COLUMN_NAME + " = '" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setID(Integer.parseInt(cursor.getString(0)));
            student.setStudentName(cursor.getString(1));
            student.setStudentEmail(cursor.getString(2));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        return student;
    }

    public String loadHandler() {
        StringBuilder result = new StringBuilder();
        String query = "Select*FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result.append(result_0).append(" ").append(result_1).append(System.getProperty("line.separator"));
        }
        cursor.close();
        db.close();
        return result.toString();
    }

    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select*FROM " + TABLE_STUDENTS + " WHERE " + COLUMN_ID + " = '" + ID + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            student.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_STUDENTS, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(student.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(int ID, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_EMAIL,email);
        return db.update(TABLE_STUDENTS, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}
