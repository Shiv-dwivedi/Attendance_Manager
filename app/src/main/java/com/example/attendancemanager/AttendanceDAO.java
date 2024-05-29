package com.example.attendancemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AttendanceDAO {
    private SQLiteDatabase db;

    public AttendanceDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertAttendance(Attendance attendance) {
        ContentValues values = new ContentValues();
        values.put("student_id", attendance.getStudentId());
        values.put("date", attendance.getDate());
        values.put("activity", attendance.getActivity());
        values.put("course_code", attendance.getCourseCode());
        values.put("batch_id", attendance.getBatchId());
        return db.insert("Attendance", null, values);
    }

    public int updateAttendance(Attendance attendance) {
        ContentValues values = new ContentValues();
        values.put("student_id", attendance.getStudentId());
        values.put("date", attendance.getDate());
        values.put("activity", attendance.getActivity());
        values.put("course_code", attendance.getCourseCode());
        values.put("batch_id", attendance.getBatchId());
        return db.update("Attendance", values, "attendance_id = ?", new String[]{String.valueOf(attendance.getAttendanceId())});
    }

    public int deleteAttendance(int attendanceId) {
        return db.delete("Attendance", "attendance_id = ?", new String[]{String.valueOf(attendanceId)});
    }

    public Attendance getAttendance(int attendanceId) {
        Cursor cursor = db.query("Attendance", null, "attendance_id = ?", new String[]{String.valueOf(attendanceId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Attendance attendance = new Attendance(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
            );
            cursor.close();
            return attendance;
        }
        return null;
    }

    // Additional methods for querying all attendance records, etc.

    // Define Attendance class inside the same file
    public static class Attendance {
        private int attendanceId;
        private int studentId;
        private String date;
        private String activity;
        private String courseCode;
        private int batchId;

        public Attendance(int attendanceId, int studentId, String date, String activity, String courseCode, int batchId) {
            this.attendanceId = attendanceId;
            this.studentId = studentId;
            this.date = date;
            this.activity = activity;
            this.courseCode = courseCode;
            this.batchId = batchId;
        }

        public int getAttendanceId() {
            return attendanceId;
        }

        public int getStudentId() {
            return studentId;
        }

        public String getDate() {
            return date;
        }

        public String getActivity() {
            return activity;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public int getBatchId() {
            return batchId;
        }
    }
}
