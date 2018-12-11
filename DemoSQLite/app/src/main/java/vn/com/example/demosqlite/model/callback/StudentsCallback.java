package vn.com.example.demosqlite.model.callback;


import vn.com.example.demosqlite.model.data.Student;

public interface StudentsCallback {
    void onClickStudentItem(int position);
    void onClickStudentItem(Student student);
}
