package vn.com.example.demosqlite.view;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.com.example.demosqlite.R;
import vn.com.example.demosqlite.common.Constants;
import vn.com.example.demosqlite.common.Utils;
import vn.com.example.demosqlite.control.DatabaseHelper;
import vn.com.example.demosqlite.control.GetDataAsyntask;
import vn.com.example.demosqlite.custom.adapter.StudentAdapter;
import vn.com.example.demosqlite.model.callback.GetDataCallback;
import vn.com.example.demosqlite.model.callback.StudentsCallback;
import vn.com.example.demosqlite.model.data.Student;

public class MainActivity extends AppCompatActivity implements StudentsCallback, GetDataCallback {

    private RecyclerView rvStudent;
    private StudentAdapter studentAdapter;
    private List<Student> students;
    private DatabaseHelper databaseHelper;
    public static int autocre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        students = new ArrayList<>();
        studentAdapter = new StudentAdapter(this, this, students);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvStudent.setLayoutManager(linearLayoutManager);
        rvStudent.setAdapter(studentAdapter);
        rvStudent.setHasFixedSize(true);
        databaseHelper = new DatabaseHelper(this);
        getData();
    }

    private void getData() {
        new GetDataAsyntask(databaseHelper,this).execute();
    }

    private void initView() {
        rvStudent = findViewById(R.id.rv_student);
    }

    public void addStudent(View view) {
        creatAddDialog();
    }

    private void creatAddDialog() {
        final Dialog dialog = new Dialog(this);
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.custom_add_student_dialog, null);

        final EditText etName = viewDialog.findViewById(R.id.et_name);
        final EditText etAge = viewDialog.findViewById(R.id.et_age);
        final EditText etClassRoom = viewDialog.findViewById(R.id.et_class_room);
        final EditText ettSourse = viewDialog.findViewById(R.id.et_course);
        final EditText etEmail = viewDialog.findViewById(R.id.et_email);
        final EditText etNumberPhone = viewDialog.findViewById(R.id.et_number_phone);
        Button btnClose = viewDialog.findViewById(R.id.btn_close);
        Button btnSave = viewDialog.findViewById(R.id.btn_save);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //todo save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etName.getText().toString())){
                    etName.setError(getString(R.string.not_enter_name));
                    return;
                }
                if (TextUtils.isEmpty(etAge.getText().toString())){
                    etAge.setError(getString(R.string.not_enter_age));
                    return;
                }
                if (TextUtils.isEmpty(etClassRoom.getText().toString())){
                    etClassRoom.setError(getString(R.string.not_enter_class));
                    return;
                }
                if (TextUtils.isEmpty(ettSourse.getText().toString())){
                    ettSourse.setError(getString(R.string.not_enter_sourse));
                    return;
                }
                if (TextUtils.isEmpty(etEmail.getText().toString())){
                    etEmail.setError(getString(R.string.not_enter_email));
                    return;
                }
                if (TextUtils.isEmpty(etNumberPhone.getText().toString())){
                    etNumberPhone.setError(getString(R.string.not_enter_number_phone));
                    return;
                }
                //todo add and save student

                autocre = Utils.getInt(getApplicationContext(),Constants.ID_STUDENT);
                Student student = new Student(etName.getText().toString(), Integer.parseInt(etAge.getText().toString())
                        , ettSourse.getText().toString() + autocre++
                        , etClassRoom.getText().toString(), ettSourse.getText().toString(), etEmail.getText().toString()
                        , etNumberPhone.getText().toString());
                databaseHelper.addStudent(student);
                students.add(student);
                studentAdapter.notifyDataSetChanged();
                Utils.saveInt(getApplicationContext(),Constants.ID_STUDENT,autocre);
                dialog.dismiss();
            }
        });
        dialog.setContentView(viewDialog);
        dialog.show();
    }

    @Override
    public void onClickStudentItem(int position) {
        //DO NOTHING
    }

    @Override
    public void onClickStudentItem(Student student) {
        Intent intent = new Intent(this,DetailStudentActivity.class);
        intent.putExtra(Constants.STUDENT,student);
        startActivity(intent);
    }

    @Override
    public void onGetDataSuccess(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
        studentAdapter.notifyDataSetChanged();
    }
}
