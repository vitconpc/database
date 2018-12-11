package vn.com.example.demorealm.model.callback;

import vn.com.example.demorealm.model.data.Student;

public interface StudentsCallback {
    void onClickStudentItem(int position);
    void onClickStudentItem(Student student);
}
