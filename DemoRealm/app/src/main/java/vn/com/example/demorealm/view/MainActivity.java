package vn.com.example.demorealm.view;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

import io.realm.Realm;
import io.realm.RealmResults;
import vn.com.example.demorealm.R;
import vn.com.example.demorealm.common.Constants;
import vn.com.example.demorealm.common.Database;
import vn.com.example.demorealm.custom.adapter.StudentAdapter;
import vn.com.example.demorealm.model.callback.StudentsCallback;
import vn.com.example.demorealm.model.data.Student;

public class MainActivity extends AppCompatActivity implements StudentsCallback {

    private RecyclerView rvStudent;
    private StudentAdapter studentAdapter;
    private List<Student> students;
    private Realm realm;
    public static int autocre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        getData();
    }

    private void getData() {
        RealmResults<Student> results = realm.where(Student.class).findAll();
        students.clear();
        students.addAll(results);
        studentAdapter.notifyDataSetChanged();
    }

    private void initData() {
        students = new ArrayList<>();
        studentAdapter = new StudentAdapter(this, this, students);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvStudent.setLayoutManager(linearLayoutManager);
        rvStudent.setAdapter(studentAdapter);
        rvStudent.setHasFixedSize(true);
        realm = Database.getRealm(this);
    }

    private void initView() {
        rvStudent = findViewById(R.id.rv_student);
    }

    //todo add student
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
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    etName.setError(getString(R.string.not_enter_name));
                    return;
                }
                if (TextUtils.isEmpty(etAge.getText().toString())) {
                    etAge.setError(getString(R.string.not_enter_age));
                    return;
                }
                if (TextUtils.isEmpty(etClassRoom.getText().toString())) {
                    etClassRoom.setError(getString(R.string.not_enter_class));
                    return;
                }
                if (TextUtils.isEmpty(ettSourse.getText().toString())) {
                    ettSourse.setError(getString(R.string.not_enter_sourse));
                    return;
                }
                if (TextUtils.isEmpty(etEmail.getText().toString())) {
                    etEmail.setError(getString(R.string.not_enter_email));
                    return;
                }
                if (TextUtils.isEmpty(etNumberPhone.getText().toString())) {
                    etNumberPhone.setError(getString(R.string.not_enter_number_phone));
                    return;
                }
                SharedPreferences preferences = getSharedPreferences(Constants.SHARE_PREFERENCE, MODE_PRIVATE);

                autocre = preferences.getInt(Constants.ID_STUDENT, 101);
                Student student = new Student(etName.getText().toString(), Integer.parseInt(etAge.getText().toString())
                        , ettSourse.getText().toString() + autocre++
                        , etClassRoom.getText().toString(), ettSourse.getText().toString(), etEmail.getText().toString()
                        , etNumberPhone.getText().toString());
                realm.beginTransaction();
                realm.copyToRealm(student);
                realm.commitTransaction();
                students.add(student);
                studentAdapter.notifyDataSetChanged();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(Constants.ID_STUDENT, autocre);
                editor.apply();
                dialog.dismiss();
            }
        });
        dialog.setContentView(viewDialog);
        dialog.show();
    }

    @Override
    public void onClickStudentItem(int position) {
        //DO ANYTHING
    }

    @Override
    public void onClickStudentItem(Student student) {
        Intent intent = new Intent(this, DetailStudentActivity.class);
        intent.putExtra(Constants.STUDENT, student);
        startActivity(intent);
    }
}
