package vn.com.example.demosqlite.model.callback;

import java.util.List;

import vn.com.example.demosqlite.model.data.Student;

public interface GetDataCallback {
    void onGetDataSuccess(List<Student> students);
}
