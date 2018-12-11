package vn.com.example.demosqlite.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import vn.com.example.demosqlite.R;
import vn.com.example.demosqlite.common.Constants;
import vn.com.example.demosqlite.model.data.Student;

public class DetailStudentActivity extends AppCompatActivity {

    private Student student;
    private TextView tvName;
    private TextView tvAge;
    private TextView tvId;
    private TextView tvClass;
    private TextView tvSourse;
    private TextView tvEmail;
    private TextView tvNumberPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_student);
        initView();
        initData();
    }
    private void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        student = intent.getParcelableExtra(Constants.STUDENT);
        setData(student);
    }

    private void setData(Student student) {
        tvAge.setText(student.getAge() + "");
        tvNumberPhone.setText(student.getNumberphone());
        tvId.setText(student.getStudentId());
        tvName.setText(student.getName());
        tvClass.setText(student.getClassRoom());
        tvSourse.setText(student.getCourse());
        tvEmail.setText(student.getEmail());
    }

    private void initView() {
        tvAge = findViewById(R.id.tv_age);
        tvId = findViewById(R.id.tv_id_student);
        tvClass = findViewById(R.id.tv_class_room);
        tvSourse = findViewById(R.id.tv_course);
        tvEmail = findViewById(R.id.tv_email);
        tvName = findViewById(R.id.tv_name);
        tvNumberPhone = findViewById(R.id.tv_phone_number);
    }
}
