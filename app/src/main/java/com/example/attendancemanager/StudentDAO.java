package com.example.attendancemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

// Define Student class first
class Student {
    private int studentId;
    private String studentName;
    private int batchId;
    private int totalPresent;
    private int totalAbsent;
    private int leaves;
    private int marks;
    private String courseCode;

    public Student(int studentId, String studentName, int batchId, int totalPresent, int totalAbsent, int leaves, String marks, int courseCode) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.batchId = batchId;
        this.totalPresent = totalPresent;
        this.totalAbsent = totalAbsent;
        this.leaves = leaves;
        this.marks = Integer.parseInt(marks);
        this.courseCode = String.valueOf(courseCode);
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getBatchId() {
        return batchId;
    }

    public int getTotalPresent() {
        return totalPresent;
    }

    public int getTotalAbsent() {
        return totalAbsent;
    }

    public int getLeaves() {
        return leaves;
    }

    public int getMarks() {
        return marks;
    }

    public String getCourseCode() {
        return courseCode;
    }
}

// Now define the StudentDAO class
public class StudentDAO {
    private SQLiteDatabase db;

    public StudentDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put("student_name", student.getStudentName());
        values.put("batch_id", student.getBatchId());
        values.put("total_present", student.getTotalPresent());
        values.put("total_absent", student.getTotalAbsent());
        values.put("leaves", student.getLeaves());
        values.put("marks", student.getMarks());
        values.put("course_code", student.getCourseCode());
        return db.insert("Student", null, values);
    }

    public int updateStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put("student_name", student.getStudentName());
        values.put("batch_id", student.getBatchId());
        values.put("total_present", student.getTotalPresent());
        values.put("total_absent", student.getTotalAbsent());
        values.put("leaves", student.getLeaves());
        values.put("marks", student.getMarks());
        values.put("course_code", student.getCourseCode());
        return db.update("Student", values, "student_id = ?", new String[]{String.valueOf(student.getStudentId())});
    }

    public int deleteStudent(int studentId) {
        return db.delete("Student", "student_id = ?", new String[]{String.valueOf(studentId)});
    }

    public Student getStudent(int studentId) {
        Cursor cursor = db.query("Student", null, "student_id = ?", new String[]{String.valueOf(studentId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Student student = new Student(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getInt(7)
            );
            cursor.close();
            return student;
        }
        return null;
    }

    // Additional methods for querying all students, etc.
}
