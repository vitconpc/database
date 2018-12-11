package vn.com.example.demosqlite.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vn.com.example.demosqlite.model.data.Student;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StudentManager";
    private static final String TABLE_STUDENT_NAME = "table_student_name";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String NUMBERPHONE = "numberphone";
    private static final String COURSE = "sourse";
    private static final String AGE = "age";
    private static final String CLASSROOM = "classroom";
    private static final String ID = "id";
    private Context context;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_STUDENT_NAME + " (" +
                ID + " TEXT primary key, " +
                NAME + " TEXT, " +
                EMAIL + " TEXT, " +
                NUMBERPHONE + " TEXT," +
                COURSE + " TEXT," +
                AGE + " integer," +
                CLASSROOM + " TEXT)";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_NAME);
        onCreate(db);
    }

    public List<Student> getAllStudent() {
        List<Student> listStudent = new ArrayList<Student>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT_NAME, new String[]{ID,
                        NAME,EMAIL,NUMBERPHONE,COURSE,AGE,CLASSROOM}, null,
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudentId(cursor.getString(0));
                student.setName(cursor.getString(1));
                student.setEmail(cursor.getString(2));
                student.setNumberphone(cursor.getString(3));
                student.setCourse(cursor.getString(4));
                student.setAge(cursor.getInt(5));
                student.setClassRoom(cursor.getString(6));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStudent;
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(NUMBERPHONE, student.getNumberphone());
        values.put(EMAIL, student.getEmail());
        values.put(AGE, student.getAge());
        values.put(CLASSROOM, student.getClassRoom());
        values.put(ID, student.getStudentId());
        values.put(COURSE, student.getCourse());
        db.insert(TABLE_STUDENT_NAME, null, values);
        db.close();
    }
}
