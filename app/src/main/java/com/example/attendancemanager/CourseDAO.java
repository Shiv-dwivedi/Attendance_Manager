package com.example.attendancemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

// Define the Course class
class Course {
    private String courseCode;
    private String courseName;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }
}

public class CourseDAO {
    private SQLiteDatabase db;

    public CourseDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertCourse(Course course) {
        ContentValues values = new ContentValues();
        values.put("course_code", course.getCourseCode());
        values.put("course_name", course.getCourseName());
        return db.insert("Course", null, values);
    }

    public int updateCourse(Course course) {
        ContentValues values = new ContentValues();
        values.put("course_name", course.getCourseName());
        return db.update("Course", values, "course_code = ?", new String[]{course.getCourseCode()});
    }

    public int deleteCourse(String courseCode) {
        return db.delete("Course", "course_code = ?", new String[]{courseCode});
    }

    public Course getCourse(String courseCode) {
        Cursor cursor = db.query("Course", null, "course_code = ?", new String[]{courseCode}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Course course = new Course(cursor.getString(cursor.getColumnIndex("course_code")),
                    cursor.getString(cursor.getColumnIndex("course_name")));
            cursor.close();
            return course;
        }
        return null;
    }

    // Additional methods for querying all courses, etc.
}
