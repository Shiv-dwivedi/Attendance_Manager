package com.example.attendancemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BatchDAO {
    private SQLiteDatabase db;

    public BatchDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertBatch(Batch batch) {
        ContentValues values = new ContentValues();
        values.put("batch_name", batch.getBatchName());
        values.put("year", batch.getYear());
        values.put("total_class", batch.getTotalClass());
        values.put("course_code", batch.getCourseCode());
        return db.insert("Batch", null, values);
    }

    public int updateBatch(Batch batch) {
        ContentValues values = new ContentValues();
        values.put("batch_name", batch.getBatchName());
        values.put("year", batch.getYear());
        values.put("total_class", batch.getTotalClass());
        return db.update("Batch", values, "batch_id = ?", new String[]{String.valueOf(batch.getBatchId())});
    }

    public int deleteBatch(int batchId) {
        return db.delete("Batch", "batch_id = ?", new String[]{String.valueOf(batchId)});
    }

    public Batch getBatch(int batchId) {
        Cursor cursor = db.query("Batch", null, "batch_id = ?", new String[]{String.valueOf(batchId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Batch batch = new Batch(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4)
            );
            cursor.close();
            return batch;
        }
        return null;
    }

    // Additional methods for querying all batches, etc.

    // Define Batch class inside the same file
    public static class Batch {
        private int batchId;
        private String batchName;
        private int year;
        private int totalClass;
        private String courseCode;

        public Batch(int batchId, String batchName, int year, int totalClass, String courseCode) {
            this.batchId = batchId;
            this.batchName = batchName;
            this.year = year;
            this.totalClass = totalClass;
            this.courseCode = courseCode;
        }

        public int getBatchId() {
            return batchId;
        }

        public String getBatchName() {
            return batchName;
        }

        public int getYear() {
            return year;
        }

        public int getTotalClass() {
            return totalClass;
        }

        public String getCourseCode() {
            return courseCode;
        }
    }
}
