package com.example.attendancemanager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "attendanceManager.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_COURSE = "Course";
    private static final String TABLE_BATCH = "Batch";
    private static final String TABLE_STUDENT = "Student";
    private static final String TABLE_ATTENDANCE = "Attendance";

    // Course Table Columns
    private static final String COLUMN_COURSE_CODE = "course_code";
    private static final String COLUMN_COURSE_NAME = "course_name";

    // Batch Table Columns
    private static final String COLUMN_BATCH_ID = "batch_id";
    private static final String COLUMN_BATCH_NAME = "batch_name";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_TOTAL_CLASS = "total_class";

    // Student Table Columns
    private static final String COLUMN_STUDENT_ID = "student_id";
    private static final String COLUMN_STUDENT_NAME = "student_name";
    private static final String COLUMN_TOTAL_PRESENT = "total_present";
    private static final String COLUMN_TOTAL_ABSENT = "total_absent";
    private static final String COLUMN_LEAVES = "leaves";
    private static final String COLUMN_MARKS = "marks";

    // Attendance Table Columns
    private static final String COLUMN_ATTENDANCE_ID = "attendance_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_ACTIVITY = "activity";

    // Create Table Statements
    private static final String CREATE_TABLE_COURSE = "CREATE TABLE " + TABLE_COURSE + "("
            + COLUMN_COURSE_CODE + " TEXT PRIMARY KEY,"
            + COLUMN_COURSE_NAME + " TEXT" + ")";

    private static final String CREATE_TABLE_BATCH = "CREATE TABLE " + TABLE_BATCH + "("
            + COLUMN_BATCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_BATCH_NAME + " TEXT,"
            + COLUMN_YEAR + " INTEGER,"
            + COLUMN_TOTAL_CLASS + " INTEGER,"
            + COLUMN_COURSE_CODE + " TEXT,"
            + "FOREIGN KEY (" + COLUMN_COURSE_CODE + ") REFERENCES " + TABLE_COURSE + "(" + COLUMN_COURSE_CODE + ")" + ")";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "("
            + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_NAME + " TEXT,"
            + COLUMN_BATCH_ID + " INTEGER,"
            + COLUMN_TOTAL_PRESENT + " INTEGER,"
            + COLUMN_TOTAL_ABSENT + " INTEGER,"
            + COLUMN_LEAVES + " INTEGER,"
            + COLUMN_MARKS + " INTEGER,"
            + COLUMN_COURSE_CODE + " TEXT,"
            + "FOREIGN KEY (" + COLUMN_BATCH_ID + ") REFERENCES " + TABLE_BATCH + "(" + COLUMN_BATCH_ID + "),"
            + "FOREIGN KEY (" + COLUMN_COURSE_CODE + ") REFERENCES " + TABLE_COURSE + "(" + COLUMN_COURSE_CODE + ")" + ")";

    private static final String CREATE_TABLE_ATTENDANCE = "CREATE TABLE " + TABLE_ATTENDANCE + "("
            + COLUMN_ATTENDANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_ID + " INTEGER,"
            + COLUMN_DATE + " TEXT,"
            + COLUMN_ACTIVITY + " TEXT,"
            + COLUMN_COURSE_CODE + " TEXT,"
            + COLUMN_BATCH_ID + " INTEGER,"
            + "FOREIGN KEY (" + COLUMN_STUDENT_ID + ") REFERENCES " + TABLE_STUDENT + "(" + COLUMN_STUDENT_ID + "),"
            + "FOREIGN KEY (" + COLUMN_COURSE_CODE + ") REFERENCES " + TABLE_COURSE + "(" + COLUMN_COURSE_CODE + "),"
            + "FOREIGN KEY (" + COLUMN_BATCH_ID + ") REFERENCES " + TABLE_BATCH + "(" + COLUMN_BATCH_ID + ")" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COURSE);
        db.execSQL(CREATE_TABLE_BATCH);
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_ATTENDANCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BATCH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        onCreate(db);
    }
}
